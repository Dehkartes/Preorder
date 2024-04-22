package dehkartes.preorder.member.service;

import dehkartes.preorder.member.entity.Member;
import dehkartes.preorder.member.repository.MemberRepository;
import dehkartes.preorder.util.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

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
}