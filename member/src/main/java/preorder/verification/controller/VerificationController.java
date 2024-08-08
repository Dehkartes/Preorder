package preorder.verification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import preorder.verification.service.VerificationService;

@RestController
@RequestMapping("verification")
@RequiredArgsConstructor
public class VerificationController {
	private final VerificationService verificationService;

	@PostMapping()
	public void verification(String id) throws Exception {
		verificationService.addVerification(id);
	}

	@PostMapping("tryVerification")
	public void tryVerification(String id, String userInput) {
		verificationService.tryVerification(id, userInput);
	}
}
