package preorder.email.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import preorder.email.service.EmailService;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService mailService;

	@PostMapping("/sendAuth")
	public String mailCheck(@RequestBody String email) {
		return String.valueOf(mailService.sendMail(email));
	}
}
