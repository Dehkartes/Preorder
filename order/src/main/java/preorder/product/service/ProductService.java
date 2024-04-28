package preorder.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import preorder.product.entity.Product;
import preorder.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public void createProduct(Product payload) {
		productRepository.save(payload);
	}

	public List<Product> getProductList() {
		return productRepository.findAll();
	}

	public Product getProductById(int id) { return productRepository.findById(id).get(); }
}
