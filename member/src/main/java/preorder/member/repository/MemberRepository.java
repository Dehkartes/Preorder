package preorder.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import preorder.security.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	@Query("update Member x set x.verified = true where x.id = :id")
	@Modifying
	void updateUserVerifiedToTrueByID(@Param("id") String id);

	Member findByEmail(String email);
}
