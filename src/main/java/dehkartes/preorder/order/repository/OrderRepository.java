package dehkartes.preorder.order.repository;

import dehkartes.preorder.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("update Order x set x.orderStatus = '주문취소' where x.id = :id")
	@Modifying
	void updateStatusToCancel(@Param("id") int id);

	@Query("update Order x set x.orderStatus = '반품중' where x.id = :id")
	@Modifying
	void updateStatusToProcessReturn(@Param("id") int id);

	@Query("update Order x set x.orderStatus = '반품완료' where x.id = :id")
	@Modifying
	void updateStatusToFinishReturn(@Param("id") int id);

	@Query("update Order x set x.orderStatus = '배송중' where x.id = :id")
	@Modifying
	void updateStatusToProcessDeliver(@Param("id") int id);

	@Query("update Order x set x.orderStatus = '배송완료' where x.id = :id")
	@Modifying
	void updateStatusToFinishDeliver(@Param("id") int id);
}
