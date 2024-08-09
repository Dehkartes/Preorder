package preorder.member.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import preorder.member.service.MemberService;
import preorder.security.entity.Member;

import java.util.Map;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@PostMapping()
	public void createMember(@RequestParam Map<String, Object> payload) throws Exception {
		memberService.createMember(payload);
	}

	@GetMapping()
	public Member findMember(String id) throws Exception {
		return memberService.findMember(id);
	}

	@PutMapping()
	public void updateMember(@RequestParam Map<String, Object> payload) throws Exception {
		memberService.updateMember(payload);
	}

	@DeleteMapping()
	public void deleteMember(@RequestParam("id")String id) throws Exception {
		memberService.deleteMember(id);
	}

	@Transactional
	@PostMapping("verified")
	public void verifyMember(@RequestBody String id) {
		memberService.verifyMember(id);
	}

	@GetMapping("validate")
	public boolean validateMember(@RequestParam("id") String id) throws Exception {
		Member member = memberService.findMember(id);
		return member != null && member.isVerified();
	}
}
