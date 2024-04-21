package dehkartes.preorder.verification.controller;

import dehkartes.preorder.verification.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification")
@RequiredArgsConstructor
public class VerificationController {
	private final VerificationService verificationService;

	@PostMapping()
	public void verification(String id) throws Exception {
		verificationService.addVerification(id);
	}
}
