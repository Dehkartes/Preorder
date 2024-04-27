package dehkartes.preorder.wishList.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dehkartes.preorder.wishList.dto.WishListDTO;
import dehkartes.preorder.wishList.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wishList")
@RequiredArgsConstructor
public class WishListController {
	private final WishListService wishListService;

	@PostMapping("all")
	public List<Integer> getAllWishList(String memberId) {
		return wishListService.getWishListIdByMenberId(memberId);
	}

	@PostMapping("get")
	public WishListDTO getWishList(int id) throws JsonProcessingException {
		return wishListService.getWishListById(id);
	}

	@PostMapping("update")
	public void updateWishList(@RequestBody WishListDTO payload) throws JsonProcessingException {
		wishListService.updateWishList(payload);
	}
}