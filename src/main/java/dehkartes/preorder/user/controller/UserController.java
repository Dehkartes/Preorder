package dehkartes.preorder.user.controller;

import dehkartes.preorder.user.dto.UserDTO;
import dehkartes.preorder.user.service.UserService;
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

	@PostMapping("/verified")
	public boolean verifyUser(@RequestParam Map<String, Object> payload) throws Exception {
		return userService.verifyUser(payload);
	}
	@PostMapping("/find")
	public UserDTO findUser(String id) throws Exception {
		return userService.findUser(id);
	}
}
