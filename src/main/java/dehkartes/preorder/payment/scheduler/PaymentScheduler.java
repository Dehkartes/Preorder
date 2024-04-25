package dehkartes.preorder.payment.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class PaymentScheduler {
//	@Scheduled(fixedRate = 1000)
//	public void task1() {
//		System.out.println(1111111);
//	}

	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
	public void task2() {

		System.out.println("Task 2 executed at midnight: " + new Date());
	}
}
