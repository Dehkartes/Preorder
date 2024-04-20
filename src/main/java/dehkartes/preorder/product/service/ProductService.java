package dehkartes.preorder.product.service;

import dehkartes.preorder.product.dto.ProductDTO;
import dehkartes.preorder.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public void createProduct(ProductDTO payload) {
		productRepository.save(payload);
	}

	public List<ProductDTO> getProductList() {
		return productRepository.findAll();
	}
}
