package dehkartes.preorder.order.service;

import dehkartes.preorder.order.component.OrderStatus;
import dehkartes.preorder.order.entity.Order;
import dehkartes.preorder.order.orderDTO.OrderDTO;
import dehkartes.preorder.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;
	public void createOrder(int wishlistId) {
		orderRepository.save(
				Order.builder()
						.date(new Date())
						.orderStatus(OrderStatus.주문완료)
						.wishListId(wishlistId)
						.build()
		);
	}
	public OrderDTO getOrder(int orderId) {
		return new OrderDTO(Objects.requireNonNull(orderRepository.findById(orderId).orElse(null)));
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
}
