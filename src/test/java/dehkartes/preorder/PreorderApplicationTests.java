package dehkartes.preorder;

import dehkartes.preorder.user.dto.UserDTO;
import dehkartes.preorder.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PreorderApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void TestJpa() throws Exception {
		UserDTO user = new UserDTO();
		user.setId("test");
		this.userRepository.save(user);
	}
}
