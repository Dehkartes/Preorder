package preorder.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import preorder.payment.client.MemberClient;
import preorder.payment.client.ProductClient;
import preorder.payment.entity.Payment;
import preorder.payment.orderDTO.PaymentDTO;
import preorder.payment.repository.PaymentRepository;
import preorder.wishList.dto.WishListDTO;
import preorder.wishList.service.WishListService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class PaymentService {
	private final PaymentRepository orderRepository;
	private final WishListService wishListService;
	private final ProductClient productClient;
	private final MemberClient memberClient;

	private final RedissonClient redisson;

	private static int count = 0;
	public void createOrder(int wishlistId) {
		orderRepository.save(
				Payment.builder()
						.date(LocalDate.now())
						.status("주문완료")
						.wishListId(wishlistId)
						.build()
		);
	}
	public PaymentDTO getOrder(int orderId) {
		return new PaymentDTO(Objects.requireNonNull(orderRepository.findById(orderId).orElse(null)));
	}

	public void setCancel(int orderId) {
		orderRepository.updateStatusToCancel(orderId);
	}

	public void setProcessReturn(int orderId) {
		orderRepository.updateStatusToProcessReturn(orderId);
	}

	public void setFinishReturn(int orderId) {
		orderRepository.updateStatusToFinishReturn(orderId);
	}

	public void updateStatus() {
		getPaymentListForUpdate().parallelStream().forEach(Payment::updateStatus);
	}

	public List<Payment> getPaymentListForUpdate() {
		return orderRepository.getPaymentForUpdate();
	}

	public Payment getPayment(int id) {
		return orderRepository.findById(id).orElse(null);
	}

	//가결제
	public int provisionalPaymentLagacy(Payment payment) throws JsonProcessingException {
		System.out.println("test");
		RLock lock = redisson.getLock("payment");
		WishListDTO wishListDTO = wishListService.getWishListById(payment.getWishListId());
		try	{
			boolean isLocked = lock.tryLock(100, 100, TimeUnit.SECONDS); //임계영역 시작 -> redisson 분산락 적용
			if (isLocked) {
				WebClient webClient = WebClient.builder().build();
				wishListDTO.getItemList().forEach((id, amount) -> {
					MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();
					formdata.add("id", id);
					formdata.add("amount", String.valueOf(amount));
					//제품 재고 검사
					Boolean response = webClient.post()
							.uri("http://localhost:8000/product/stock/isenough")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.body(BodyInserters.fromFormData(formdata))
							.retrieve()
							.bodyToMono(Boolean.class)
							.block();
					if(!response)
						throw new RuntimeException("out of stock");
				});
				wishListDTO.getItemList().forEach((id, amount) -> {
					MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();
					formdata.add("id", id);
					formdata.add("amount", String.valueOf(amount));
					//제품 재고 감소
					String response = webClient.post()
							.uri("http://localhost:8000/product/stock/decrease")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.body(BodyInserters.fromFormData(formdata))
							.retrieve()
							.bodyToMono(String.class)
							.block();
				});
				count++;
				System.out.println(count);
				return count;
			}
			else {
				throw new RuntimeException("connection time out");
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			if(lock != null && lock.isLocked()) {
				lock.unlock();
			} //임계영역 끝
		}
	}

	//가결제 OpenFeign
	public int provisionalPayment(Payment payment) throws JsonProcessingException {
		System.out.println("test");
		RLock lock = redisson.getLock("payment");
		WishListDTO wishListDTO = wishListService.getWishListById(payment.getWishListId());
		try	{
			boolean isLocked = lock.tryLock(100, 100, TimeUnit.SECONDS); //임계영역 시작 -> redisson 분산락 적용
			if (isLocked) {
				//사용자 검사
				if(!memberClient.validateMember(wishListDTO.getMemberId())) {
					throw new RuntimeException("Invalid member");
				}

				// 장바구니 순회 및 재고 검사
				wishListDTO.getItemList().forEach((id, amount) -> {
					if(!productClient.isenough(Integer.parseInt(id), amount))
						throw new RuntimeException("Out of stock");
				});
				WebClient webClient = WebClient.builder().build();
				wishListDTO.getItemList().forEach((id, amount) -> {
					MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();
					formdata.add("id", id);
					formdata.add("amount", String.valueOf(amount));
					//제품 재고 감소
					String response = webClient.post()
							.uri("http://localhost:8000/product/stock/decrease")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.body(BodyInserters.fromFormData(formdata))
							.retrieve()
							.bodyToMono(String.class)
							.block();
				});
				count++;
				System.out.println(count);
				return count;
			}
			else {
				throw new RuntimeException("connection time out");
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			if(lock != null && lock.isLocked()) {
				lock.unlock();
			} //임계영역 끝
		}
	}

	//사용자 가결제 취소, 본결제 실패 시 클라이언트에서 호출
	public void rollbackPayment(Payment payment) {
		// 제품 재고 추가
		// Product 호출
	}

	//본결제
	public void finalizePayment(Payment payment) {
		//실패시(try catch) rollback Payment 호출
	}

	public boolean testFeign() {
		return memberClient.validateMember("dehkartes");
	}
}
