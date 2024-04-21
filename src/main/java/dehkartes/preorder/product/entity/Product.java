package dehkartes.preorder.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	String id;
	String stock;
	boolean verified;
	String name;
	String phone;
	String address;
	String email;
}