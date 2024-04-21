package dehkartes.preorder.verification.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VerificationDTO {
	@Id
	String id;
	String authCode;
}
