package preorder.product.entity;

import jakarta.persistence.*;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int stock;
	int price;
	String detail;
	boolean hide;
}