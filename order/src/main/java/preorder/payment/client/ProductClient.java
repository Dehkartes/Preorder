package preorder.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Stock-Client", url = "http://localhost:8000/product")
public interface ProductClient {
	@PostMapping("/stock/isenough")
	boolean isenough(@RequestParam("id") int id, @RequestParam("amount") int amount);
}
