package dehkartes.preorder.verification.repository;

import dehkartes.preorder.verification.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VerificationRepository extends JpaRepository<Verification, String> {
	@Query("select x.email from User x where x.id = :id")
	String findEmailById(@Param("id") String id);

	@Query("select x.authCode from Verification x where x.id = :id")
	String findAuthCodebyId(@Param("id") String id);
}