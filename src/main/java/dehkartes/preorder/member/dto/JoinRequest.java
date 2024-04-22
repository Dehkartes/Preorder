package dehkartes.preorder.member.dto;

import dehkartes.preorder.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

	@NotBlank(message = "ID를 입력하세요.")
	private String loginId;

	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
	private String passwordCheck;

	@NotBlank(message = "이름을 입력하세요.")
	private String name;

	public Member toEntity(){
		return Member.builder()
				.id(this.loginId)
				.password(this.password)
				.name(this.name)
				.build();
	}
}