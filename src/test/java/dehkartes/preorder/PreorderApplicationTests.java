package dehkartes.preorder;

import dehkartes.preorder.user.entity.User;
import dehkartes.preorder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class PreorderApplicationTests {
	private UserRepository userRepository;

	@Test
	void TestJpa() throws Exception {
		User user = new User();
		user.setId("test");
		this.userRepository.save(user);
	}
	@Value("${Encrypt.AES256}")
	String vvv;
	@Test
	void TestValue() {

		System.out.println(vvv);
	}
}
