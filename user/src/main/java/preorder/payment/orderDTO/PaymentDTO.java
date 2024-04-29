package preorder.payment.orderDTO;

import preorder.payment.entity.Payment;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDTO {
	int id;
	int wishListId;
	LocalDate date;
	String orderStatus;
	int progress;

	public PaymentDTO(Payment order) {
		this.id = order.getId();
		this.wishListId = order.getWishListId();
		this.date = order.getDate();
		this.orderStatus = order.getStatus();
	}
}
