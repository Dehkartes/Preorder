package preorder.wishList.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preorder.wishList.dto.WishListDTO;

import java.util.HashMap;

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
	String itemList;

	public WishListDTO toDTO() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Integer> map = mapper.readValue(itemList, HashMap.class);
		return WishListDTO.builder()
				.id(id)
				.memberId(memberId)
				.itemList(map)
				.build();
	}
}
