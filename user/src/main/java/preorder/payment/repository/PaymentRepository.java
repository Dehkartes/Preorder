package preorder.payment.repository;

import dehkartes.preorder.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	@Query("select x from Payment x where x.status = '주문완료' or  x.status = '배송중' or x.status = '반품중'")
	List<Payment> getPaymentForUpdate();

	@Query(value = "update Payment x set x.status = '주문취소' where x.id = :id", nativeQuery = true)
	@Modifying
	void updateStatusToCancel(@Param("id") int id);

	@Query(value = "update Payment x set x.status = '반품중' where x.id = :id", nativeQuery = true)
	@Modifying
	void updateStatusToProcessReturn(@Param("id") int id);

	@Query(value = "update Payment x set x.status = '반품완료' where x.id = :id", nativeQuery = true)
	@Modifying
	void updateStatusToFinishReturn(@Param("id") int id);

	@Query(value = "update Payment x set x.status = '배송중' where x.id = :id", nativeQuery = true)
	@Modifying
	void updateStatusToProcessDeliver(@Param("id") int id);

	@Query(value = "update Payment x set x.status = '배송완료' where x.id = :id", nativeQuery = true)
	@Modifying
	void updateStatusToFinishDeliver(@Param("id") int id);
}
