package preorder.product.component;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import preorder.product.service.ProductService;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ProductScheduler {
	private final ProductService productService;

	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
	public void writeBackStock() {
		productService.writeBackStock();
	}
}
