package preorder.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Member-Client", url = "http://localhost:8000/member")
public interface MemberClient {
	@GetMapping("/validate")
	boolean validateMember(@RequestParam("id") String id);
}
