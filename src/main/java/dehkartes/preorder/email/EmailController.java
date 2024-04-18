package dehkartes.preorder.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService mailService;

	@PostMapping("/check")
	public String mailCheck(String email) {
		int authNumber = mailService.sendMail(email);

		String authCode = "" + authNumber;

		return authCode;
	}
}
