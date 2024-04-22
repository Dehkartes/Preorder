package dehkartes.preorder.wishList.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dehkartes.preorder.wishList.entity.WishList;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor()
@Builder
@Getter
public class WishListDTO {
	private static ObjectMapper objectMapper;
	int id;
	String memberId;
	HashMap<String, Integer> itemList;

	public WishList toEntity() throws JsonProcessingException {
		return WishList.builder()
				.id(this.id)
				.memberId(this.memberId)
				.ItemList(objectMapper.writeValueAsString(this.itemList))
				.build();
	}
}
