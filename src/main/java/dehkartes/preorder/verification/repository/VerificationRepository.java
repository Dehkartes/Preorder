package dehkartes.preorder.verification.repository;

import dehkartes.preorder.verification.dto.VerificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationDTO, String> {
}
