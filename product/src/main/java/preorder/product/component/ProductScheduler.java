package preorder.product.component;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import preorder.product.service.ProductService;

import java.util.HashSet;
import java.util.Set;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ProductScheduler {
	private final ProductService productService;
	private final RedisTemplate <Integer, Object> redisTemplate;
	private final RedisConnectionFactory redisConnectionFactory;

	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
	public void task() {
		Set<Integer> keys = new HashSet<>();
		try (RedisConnection connection = redisConnectionFactory.getConnection()) {
			RedisKeyCommands keyCommands = connection.keyCommands();

			Cursor<byte[]> cursor = keyCommands.scan(ScanOptions.scanOptions().match("*").count(10).build());
			while (cursor.hasNext()) {
				keys.add(Integer.parseInt(new String(cursor.next())));
			}
		} catch (Exception e) {
			System.err.println("Error scanning keys: " + e.getMessage());
		}

		keys.forEach(key -> productService.updateStock(key, (Integer) redisTemplate.opsForValue().get(key)));
	}
}
