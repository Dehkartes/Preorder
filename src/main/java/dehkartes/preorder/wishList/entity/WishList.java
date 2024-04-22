package dehkartes.preorder.wishList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishList {
	@Id
	int id;
	@Column(name = "Member.ID")
	String memberId;
	String ItemList;
}
