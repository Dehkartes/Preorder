package preorder.member.service;

import preorder.member.dto.LoginRequest;
import preorder.member.dto.RegisterRequest;
import preorder.member.entity.Member;
import preorder.member.repository.MemberRepository;
import preorder.util.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public void createMember(Map<String, Object> payload) throws Exception {
		Member member = Member.builder()
				.id(String.valueOf(payload.get("id")))
				.name(AES256.aesCBCEncode(String.valueOf(payload.get("name"))))
				.phone(String.valueOf(payload.get("phone")))
				.password(AES256.aesCBCEncode(String.valueOf(payload.get("password"))))
				.address(AES256.aesCBCEncode(String.valueOf(payload.get("address"))))
				.email(AES256.aesCBCEncode(String.valueOf(payload.get("email"))))
				.build();
		memberRepository.save(member);
	}

	public void verifyMember(String id) {
		memberRepository.updateUserVerifiedToTrueByID(id);
	}

	public void updateMember(Map<String, Object> payload) throws Exception {
		Member member = memberRepository.findById(String.valueOf(payload.get("id")))
				.orElseThrow();
		member.setAddress(AES256.aesCBCEncode(String.valueOf(payload.get("address"))));
		member.setPhone(String.valueOf(payload.get("phone")));
		member.setPassword(AES256.aesCBCEncode(String.valueOf(payload.get("password"))));

		memberRepository.save(member);
	}

//	엔티티 DTO 구분하기, Convert사용하기
	public Member findMember(String id) throws Exception {
		Member member = memberRepository.findById(id)
				.orElseThrow();
		member.setName(AES256.aesCBCDecode(member.getName()));
		member.setPassword(AES256.aesCBCDecode(member.getPassword()));
		member.setAddress(AES256.aesCBCDecode(member.getAddress()));
		member.setEmail(AES256.aesCBCDecode(member.getEmail()));
		return member;
	}

	public boolean getExistId(String id) {
		return memberRepository.existsById(id);
	}

	public Member login(LoginRequest loginRequest) {
		Member findMember = memberRepository.findById(loginRequest.getLoginId()).orElse(null);
		if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), findMember.getPassword())) {
			return null;
		}

		return findMember;
	}

	public Member getLoginMemberById(String memberId){
		if(memberId == null) return null;

		Optional<Member> findMember = memberRepository.findById(memberId);
		return findMember.orElse(null);
	}

	// BCryptPasswordEncoder 를 통해서 비밀번호 암호화 작업 추가한 회원가입 로직
	public void securityJoin(RegisterRequest joinRequest) throws Exception {
		if(memberRepository.existsById(joinRequest.getLoginId())){
			return;
		}

		joinRequest.setPassword(bCryptPasswordEncoder.encode(joinRequest.getPassword()));

		memberRepository.save(joinRequest.toEntity());
	}
}