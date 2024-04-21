package dehkartes.preorder;

import dehkartes.preorder.user.dto.UserDTO;
import dehkartes.preorder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class PreorderApplicationTests {
	private UserRepository userRepository;

	@Test
	void TestJpa() throws Exception {
		UserDTO user = new UserDTO();
		user.setId("test");
		this.userRepository.save(user);
	}
}
