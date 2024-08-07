package preorder.verification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import preorder.util.AES256;
import preorder.verification.entity.Verification;
import preorder.verification.repository.VerificationRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VerificationService {
	private final VerificationRepository verificationRepository;

	public void addVerification(String id) throws Exception {
		WebClient webClient = WebClient.builder().build();
		String response = webClient.post()
				.uri("http://localhost:8080/email/sendAuth")
				.bodyValue(AES256.aesCBCDecode(verificationRepository.findEmailById(id)))
				.retrieve()
				.bodyToMono(String.class)
				.block();

		verificationRepository.save(
				Verification.builder()
						.id(id)
						.authCode(response)
						.build()
		);
	}

	public void tryVerification(String id, String userInput) {
		if(Objects.equals(verificationRepository.findAuthCodebyId(id), userInput)) {
			WebClient webClient = WebClient.builder().build();
			String response = webClient.post()
					.uri("http://localhost:8080/user/verified")
					.bodyValue(id)
					.retrieve()
					.bodyToMono(String.class)
					.block();

			verificationRepository.delete(
					Verification.builder()
							.id(id)
							.build()
			);
		}
	}
}
