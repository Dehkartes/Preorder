package dehkartes.preorder.product.controller;

import dehkartes.preorder.product.dto.ProductDTO;
import dehkartes.preorder.product.service.ProductService;
import dehkartes.preorder.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@PostMapping("/create")
	public void createUser(@RequestParam ProductDTO payload) throws Exception {
		productService.createProduct(payload);
	}

	@PostMapping("/find")
	public List<ProductDTO> getProductList() throws Exception {
		return productService.getProductList();
	}
}
