package preorder.user.service;

import preorder.user.dto.LoginRequest;
import preorder.user.dto.SignUpDTO;
import preorder.security.entity.User;
import preorder.user.repository.UserRepository;
import preorder.util.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public void createMember(Map<String, Object> payload) throws Exception {
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

	public void verifyMember(String id) {
		userRepository.updateUserVerifiedToTrueByID(id);
	}

	public void updateMember(Map<String, Object> payload) throws Exception {
		User user = userRepository.findById(String.valueOf(payload.get("id")))
				.orElseThrow();
		user.setAddress(AES256.aesCBCEncode(String.valueOf(payload.get("address"))));
		user.setPhone(String.valueOf(payload.get("phone")));
		user.setPassword(AES256.aesCBCEncode(String.valueOf(payload.get("password"))));

		userRepository.save(user);
	}

//	엔티티 DTO 구분하기, Convert사용하기
	public User findMember(String id) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow();
		user.setName(AES256.aesCBCDecode(user.getName()));
		user.setPassword(AES256.aesCBCDecode(user.getPassword()));
		user.setAddress(AES256.aesCBCDecode(user.getAddress()));
		user.setEmail(AES256.aesCBCDecode(user.getEmail()));
		return user;
	}

	public boolean getExistId(String id) {
		return userRepository.existsById(id);
	}

	public User login(LoginRequest loginRequest) {
		User findUser = userRepository.findById(loginRequest.getLoginId()).orElse(null);
		if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), findUser.getPassword())) {
			return null;
		}

		return findUser;
	}

	public User getLoginMemberById(String memberId){
		if(memberId == null) return null;

		Optional<User> findMember = userRepository.findById(memberId);
		return findMember.orElse(null);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	// BCryptPasswordEncoder 를 통해서 비밀번호 암호화 작업 추가한 회원가입 로직
	public void securityJoin(SignUpDTO joinRequest) throws Exception {
		if(userRepository.existsById(joinRequest.getLoginId())){
			return;
		}

		joinRequest.setPassword(bCryptPasswordEncoder.encode(joinRequest.getPassword()));

		userRepository.save(joinRequest.toEntity());
	}
}