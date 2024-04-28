package preorder.payment.service;

import dehkartes.preorder.payment.entity.Payment;
import dehkartes.preorder.payment.orderDTO.PaymentDTO;
import dehkartes.preorder.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

	}

	public List<Payment> getPaymentListForUpdate() {
		return orderRepository.getPaymentForUpdate();
	}

	public Payment getPayment(int id) {
		return orderRepository.findById(id).orElse(null);
	}
}
