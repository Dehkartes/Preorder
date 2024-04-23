package dehkartes.preorder.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "WishList.ID")
	int wishListId;
	Date date;

}
