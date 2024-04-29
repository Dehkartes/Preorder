package preorder.email.controller;

import preorder.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService mailService;

	@PostMapping("/sendAuth")
	public String mailCheck(@RequestBody String email) {
		int number = mailService.sendMail(email);

		String num = "" + number;

		return num;
	}
}
