package dehkartes.preorder.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	String id;
	String password;
	boolean verified;
	String name;
	String phone;
	String address;
	String email;
}
