package dehkartes.preorder.order.service;

import dehkartes.preorder.order.orderDTO.OrderDTO;
import dehkartes.preorder.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;
	public OrderDTO getOrder(int orderId) {
		orderRepository
	}
}
