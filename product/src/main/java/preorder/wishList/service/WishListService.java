package preorder.wishList.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dehkartes.preorder.wishList.dto.WishListDTO;
import dehkartes.preorder.wishList.entity.WishList;
import dehkartes.preorder.wishList.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
	private final ObjectMapper objectMapper;
	private final WishListRepository wishListRepository;
	public List<Integer> getWishListIdByMenberId(String memberId) {
		return wishListRepository.findIdByMemberId(memberId).stream().toList();
	}

	public WishListDTO getWishListById(int wishListId) throws JsonProcessingException {
		WishList wishList = wishListRepository.findById(wishListId).orElse(null);

		TypeReference<HashMap<String, Integer>> typeReference = new TypeReference<>() {};
		return WishListDTO.builder()
				.id(wishList.getId())
				.memberId(wishList.getMemberId())
				.itemList(objectMapper.readValue(wishList.getItemList(), typeReference))
				.build();
	}

	public void updateWishList(WishListDTO payload) throws JsonProcessingException {
		wishListRepository.save(payload.toEntity());
	}
}
