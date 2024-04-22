package dehkartes.preorder;

import dehkartes.preorder.member.entity.Member;
import dehkartes.preorder.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class PreorderApplicationTests {
	private MemberRepository memberRepository;

	@Test
	void TestJpa() throws Exception {
		Member member = new Member();
		member.setId("test");
		this.memberRepository.save(member);
	}
	@Value("${Encrypt.AES256}")
	String vvv;
	@Test
	void TestValue() {

		System.out.println(vvv);
	}
}
