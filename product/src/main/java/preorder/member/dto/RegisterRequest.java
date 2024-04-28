package preorder.member.dto;

import dehkartes.preorder.member.entity.Member;
import dehkartes.preorder.util.AES256;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
	@NotBlank(message = "ID를 입력하세요.")
	private String loginId;
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
	private String passwordCheck;
	@NotBlank(message = "이름을 입력하세요.")
	private String name;
	@Email @NotBlank(message = "이메일 입력 필요")
	private String email;
	@NotBlank(message = "주소 입력 필요")
	private String address;
	private String phone;

	public Member toEntity() throws Exception {
		return Member.builder()
				.id(this.loginId)
				.password(this.password)
				.name(AES256.aesCBCEncode(this.name))
				.email(AES256.aesCBCEncode(this.email))
				.address(AES256.aesCBCEncode(this.address))
				.phone(this.phone)
				.build();
	}
}