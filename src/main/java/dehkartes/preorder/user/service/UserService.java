package dehkartes.preorder.user.service;

import dehkartes.preorder.user.entity.User;
import dehkartes.preorder.user.repository.UserRepository;
import dehkartes.preorder.util.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public void createUser(Map<String, Object> payload) throws Exception {
		User user = User.builder()
				.id(String.valueOf(payload.get("id")))
				.name(AES256.aesCBCEncode(String.valueOf(payload.get("name"))))
				.phone(String.valueOf(payload.get("phone")))
				.password(AES256.aesCBCEncode(String.valueOf(payload.get("password"))))
				.address(AES256.aesCBCEncode(String.valueOf(payload.get("address"))))
				.email(AES256.aesCBCEncode(String.valueOf(payload.get("email"))))
				.build();
		userRepository.save(user);
	}

	public void verifyUser(String id) {
		userRepository.updateUserVerifiedToTrueByID(id);
	}

	public void updateUser(Map<String, Object> payload) throws Exception {
		User user = userRepository.findById(String.valueOf(payload.get("id")))
				.orElseThrow();
		user.setAddress(AES256.aesCBCEncode(String.valueOf(payload.get("address"))));
		user.setPhone(String.valueOf(payload.get("phone")));
		user.setPassword(AES256.aesCBCEncode(String.valueOf(payload.get("password"))));

		userRepository.save(user);
	}

//	엔티티 DTO 구분하기, Convert사용하기
	public User findUser(String id) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow();
		user.setName(AES256.aesCBCDecode(user.getName()));
		user.setPassword(AES256.aesCBCDecode(user.getPassword()));
		user.setAddress(AES256.aesCBCDecode(user.getAddress()));
		user.setEmail(AES256.aesCBCDecode(user.getEmail()));
		return user;
	}
}