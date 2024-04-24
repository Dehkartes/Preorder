package dehkartes.preorder.order.entity;

import dehkartes.preorder.order.component.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "WishList.ID")
	int wishListId;
	Date date;
	OrderStatus orderStatus;
}
