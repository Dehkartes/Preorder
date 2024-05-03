package preorder.user.controller;

import preorder.security.entity.User;
import preorder.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class UserController {
	private final UserService memberService;

	@PostMapping("create")
	public void createMember(@RequestParam Map<String, Object> payload) throws Exception {
		memberService.createMember(payload);
	}

	@PostMapping("update")
	public void updateMember(@RequestParam Map<String, Object> payload) throws Exception {
		memberService.updateMember(payload);
	}

	@Transactional
	@PostMapping("verified")
	public void verifyMember(@RequestBody String id) throws Exception {
		memberService.verifyMember(id);
	}
	@PostMapping("find")
	public User findMember(String id) throws Exception {
		return memberService.findMember(id);
	}

	@GetMapping("test")
	public String test() {
		return "test";
	}

}
