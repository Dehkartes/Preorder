package preorder.member.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import preorder.member.entity.Member;
import preorder.member.service.MemberService;

import java.util.Map;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

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
	public Member findMember(String id) throws Exception {
		return memberService.findMember(id);
	}

}
