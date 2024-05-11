package preorder.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import preorder.payment.entity.Payment;
import preorder.payment.orderDTO.PaymentDTO;
import preorder.payment.repository.PaymentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PaymentService {
	private final PaymentRepository orderRepository;
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
	public void provisionalPayment(Payment payment) {
		//임계영역 시작 -> redisson 분산락 적용
		//제품 재고 검사
		//제품 재고 감소
		//임계영역 끝
	}

	//사용자 가결제 취소, 본결제 실패 시 클라이언트에서 호출
	public void rollbackPayment(Payment payment) {
		// 제품 재고 추가
		// Product 호출
	}

	//본결제
	public void finalizePayment(Payment payment) {
		//실패시(try catch) rollbackPayment 호출
	}
}
