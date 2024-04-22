package dehkartes.preorder.member.repository;

import dehkartes.preorder.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	@Query("update Member x set x.verified = true where x.id = :id")
	@Modifying
	void updateUserVerifiedToTrueByID(@Param("id") String id);

	Member findByName(String name);
}
