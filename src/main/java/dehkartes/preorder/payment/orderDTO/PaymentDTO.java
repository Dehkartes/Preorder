package dehkartes.preorder.payment.orderDTO;

import dehkartes.preorder.payment.entity.Payment;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

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