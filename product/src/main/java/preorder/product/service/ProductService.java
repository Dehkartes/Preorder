package preorder.product.service;

import org.springframework.data.redis.core.RedisTemplate;
import preorder.product.entity.Product;
import preorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final RedisTemplate<Integer, Object> redisTemplate;

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
		if (Boolean.FALSE.equals(redisTemplate.hasKey(id))) {
			redisTemplate.opsForValue().set(id, getProductById(id).getStock());
		}

		return (Integer) redisTemplate.opsForValue().get(id);
	}
}
