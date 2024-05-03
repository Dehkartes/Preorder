package preorder.user.repository;

import preorder.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Query("update User x set x.verified = true where x.id = :id")
	@Modifying
	void updateUserVerifiedToTrueByID(@Param("id") String id);

	User findByEmail(String email);
}
