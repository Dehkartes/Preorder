package preorder.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import preorder.payment.entity.Payment;
import preorder.payment.service.PaymentService;

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

	@GetMapping("schedulerTest")
	public void schedulerTest() {
		paymentService.updateStatus();
	}

	@GetMapping("{id}")
	public Payment getPayment(@PathVariable int id) {
		return paymentService.getPayment(id);
	}

	@PostMapping("provisional")
	public String provisional(int id) throws JsonProcessingException {
		return String.valueOf(paymentService.provisionalPayment(paymentService.getPayment(id)));
	}
}