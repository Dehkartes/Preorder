package preorder.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "Stock-Client", url = "http://localhost:8000/product/stock/")
public interface StockClient {
	@PostMapping("isenough")
	boolean isenough(int id, int amount);
}
