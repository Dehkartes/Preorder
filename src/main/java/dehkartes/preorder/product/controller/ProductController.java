package dehkartes.preorder.product.controller;

import dehkartes.preorder.product.dto.ProductDTO;
import dehkartes.preorder.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	@PostMapping("/create")
	public void createUser(@RequestParam ProductDTO payload) throws Exception {
		productService.createProduct(payload);
	}

	@PostMapping("/find")
	public List<ProductDTO> getProductList() throws Exception {
		return productService.getProductList();
	}
}
