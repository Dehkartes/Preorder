package preorder.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Stock-Client", url = "http://localhost:8000/product/stock/")
public interface StockClient {
	@PostMapping("isenough")
	boolean isenough(@RequestParam("id") int id, @RequestParam("amount") int amount);
}
