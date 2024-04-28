package preorder.wishList.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dehkartes.preorder.wishList.entity.WishList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishListDTO {
	private static ObjectMapper objectMapper = new ObjectMapper();
	int id;
	String memberId;
	HashMap<String, Integer> itemList;

	public WishList toEntity() throws JsonProcessingException {
		return WishList.builder()
				.id(this.id)
				.memberId(this.memberId)
				.itemList(objectMapper.writeValueAsString(this.itemList))
				.build();
	}
}
