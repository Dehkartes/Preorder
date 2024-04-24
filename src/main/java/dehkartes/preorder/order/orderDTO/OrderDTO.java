package dehkartes.preorder.order.orderDTO;

import dehkartes.preorder.order.component.OrderStatus;
import dehkartes.preorder.order.entity.Order;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
	int id;
	int wishListId;
	Date date;
	OrderStatus orderStatus;

	public OrderDTO(Order order) {
		this.id = order.getId();
		this.wishListId = order.getWishListId();
		this.date = order.getDate();
		this.orderStatus = order.getOrderStatus();
	}
}
