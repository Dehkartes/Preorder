package preorder.product.service;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import preorder.product.entity.Product;
import preorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final RedisTemplate<String, String> redisTemplate;
	private final RedisConnectionFactory redisConnectionFactory;

	public void createProduct(Product payload) {
		productRepository.save(payload);
	}

	public List<Product> getProductList() {
		return productRepository.findAll();
	}

	public Product getProductById(int id) { return productRepository.findById(id).get(); }
	
	public void updateStock(int id, int amount) {
		Product product = productRepository.findById(id).get();
		product.updateStock(amount);
	}

	public int lookAsideProductStock(int id) {
		if (Boolean.FALSE.equals(redisTemplate.hasKey(String.valueOf(id)))) {
			redisTemplate.opsForValue().set(String.valueOf(id), String.valueOf(getProductById(id).getStock()));
		}

		return Integer.parseInt((String) redisTemplate.opsForValue().get(String.valueOf(id)));
	}

	public void writeBackStock() {
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

		keys.forEach(key -> updateStock(key, Integer.parseInt(redisTemplate.opsForValue().get(String.valueOf(key)))));
	}

	public boolean isEnoughStock(int id, int amount) {
		int stock = lookAsideProductStock(id);
		return stock - amount > 0;
	}
	public void decreaseStock(int id, int amount) {
		int stock = lookAsideProductStock(id);
		int x = stock - amount;
		if (x < 0)
			throw new RuntimeException("Out of stock");
		else {
			redisTemplate.opsForValue().set(String.valueOf(id), String.valueOf(x));
		}
	}
}
