package preorder.payment.controller;

import preorder.payment.entity.Payment;
import preorder.payment.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	@PostMapping("create")
	public void createOrder(Integer wishListId) {
		paymentService.createOrder(wishListId);
	}

	@Transactional
	@PostMapping("setCancel")
	public void setCancel(Integer wishListId) {
		paymentService.setCancel(wishListId);
	}

	@GetMapping("/aa")
	public List<Payment> getPaymentListForUpdate() {
		return paymentService.getPaymentListForUpdate();
	}

	@GetMapping("/{id}")
	public Payment getPayment(@PathVariable int id) {
		return paymentService.getPayment(id);
	}
}