package preorder.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "WishList.ID")
	int wishListId;
	LocalDate date;
	String status; //주문완료, 배송중, 배송완료, 주문취소, 반품중, 반품완료

	public void updateStatus() {
		LocalDate today = LocalDate.now();
		int deltaDate = (int)ChronoUnit.DAYS.between(today, this.date);
		switch (status) {
			case "주문완료":
				if(deltaDate <= 1) {
					this.status = "배송중";
					this.date = LocalDate.now();
				}
			case "배송중":
				if (deltaDate <= 1) {
					this.status = "배송완료";
					this.date = LocalDate.now();
				}
			case "반품중":
				if(deltaDate <= 1)
					this.status = "반품완료";
		}
	}
}
