package preorder.product.controller;

import preorder.product.entity.Product;
import preorder.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	@PostMapping("create")
	public void createProduct(@RequestBody Product payload) throws Exception {
		productService.createProduct(payload);
	}

	@GetMapping()
	public Product getProductById(@RequestParam("id") int id) {
		return productService.getProductById(id);
	}

	@GetMapping("stock")
	public int getStockById(@RequestParam("id") int id) {
		return productService.lookAsideProductStock(id);
	}

	@GetMapping("stock/writeback")
	public void writeBack() {
		productService.writeBackStock();
	}

	@PostMapping("findAll")
	public List<Product> getProductList() throws Exception {
		return productService.getProductList();
	}
}