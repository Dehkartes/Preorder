package preorder.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Member")
@DynamicUpdate
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
	String role;
}

