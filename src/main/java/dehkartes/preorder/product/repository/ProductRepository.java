package dehkartes.preorder.product.repository;

import dehkartes.preorder.user.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<UserDTO, String> {
}
