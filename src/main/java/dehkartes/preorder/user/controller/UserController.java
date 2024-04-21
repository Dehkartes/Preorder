package dehkartes.preorder.user.controller;

import dehkartes.preorder.user.entity.User;
import dehkartes.preorder.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/create")
	public void createUser(@RequestParam Map<String, Object> payload) throws Exception {
		userService.createUser(payload);
	}

	@PostMapping("/update")
	public void updateUser(@RequestParam Map<String, Object> payload) throws Exception {
		userService.updateUser(payload);
	}

	@Transactional
	@PostMapping("verified")
	public void verifyUser(@RequestBody String id) throws Exception {
		userService.verifyUser(id);
	}
	@PostMapping("/find")
	public User findUser(String id) throws Exception {
		return userService.findUser(id);
	}

}
