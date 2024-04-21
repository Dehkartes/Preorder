package dehkartes.preorder.product.service;

import dehkartes.preorder.product.dto.ProductDTO;
import dehkartes.preorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public void createProduct(ProductDTO payload) {
		productRepository.save(payload);
	}

	public List<ProductDTO> getProductList() {
		return productRepository.findAll();
	}
}
