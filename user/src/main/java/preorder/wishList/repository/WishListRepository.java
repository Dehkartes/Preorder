package preorder.wishList.repository;

import preorder.wishList.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
	@Query("select x.id from WishList x where x.memberId = :memberId")
	List<Integer> findIdByMemberId(String memberId);
}
