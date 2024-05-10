package preorder.payment.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import preorder.payment.service.PaymentService;

import java.util.Date;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class PaymentScheduler {
	private final PaymentService paymentService;

	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
	public void task2() {
		paymentService.updateStatus();
	}
}
