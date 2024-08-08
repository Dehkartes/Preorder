package preorder.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import preorder.member.dto.LoginRequest;
import preorder.member.dto.SignUpDTO;
import preorder.member.service.MemberService;
import preorder.security.component.JWTUtil;
import preorder.security.entity.Member;

import java.util.Collection;
import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt-login")
public class JwtLoginController {

	private final MemberService userService;
	private final JWTUtil jwtUtil;

	@GetMapping(value = {"", "/"})
	public String home(Model model) {

		model.addAttribute("loginType", "jwt-login");
		model.addAttribute("pageName", "스프링 시큐리티 JWT 로그인");

		String loginId = SecurityContextHolder.getContext().getAuthentication().getName();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();

		Member loginMember = userService.getLoginMemberById(loginId);

		if (loginMember != null) {
			model.addAttribute("name", loginMember.getName());
		}

		return "home";
	}

	@GetMapping("/join")
	public String joinPage(Model model) {

		model.addAttribute("loginType", "jwt-login");
		model.addAttribute("pageName", "스프링 시큐리티 JWT 로그인");

		// 회원가입을 위해서 model 통해서 joinRequest 전달
		model.addAttribute("joinRequest", new SignUpDTO());
		return "join";
	}

	@PostMapping("/join")
	public String join(@Valid @ModelAttribute SignUpDTO signUpDTO) throws Exception {
		// ID 중복 여부 확인
		if (userService.getExistId(signUpDTO.getLoginId())) {
			return "ID가 존재합니다.";
		}

		// 비밀번호 = 비밀번호 체크 여부 확인
		if (!signUpDTO.getPassword().equals(signUpDTO.getPasswordCheck())) {
			return "비밀번호가 일치하지 않습니다.";
		}

		// 에러가 존재하지 않을 시 signUpDTO 통해서 회원가입 완료
		userService.securityJoin(signUpDTO);

		// 회원가입 시 홈 화면으로 이동
		return "redirect:/jwt-login";
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest){
		Member member = userService.login(loginRequest);
		if(member == null){
			return "ID 또는 비밀번호가 일치하지 않습니다!";
		}

		return jwtUtil.createJwt(member.getId(), member.getRole(), 1000 * 60 * 60L);
	}

	@GetMapping("/info")
	public String memberInfo(Authentication auth, Model model) {

		Member loginMember = userService.getLoginMemberById(auth.getName());

		return "ID : " + loginMember.getId() + "\n이름 : " + loginMember.getName() + "\nrole : " + loginMember.getRole();
	}

	@GetMapping("/admin")
	public String adminPage(Model model) {

		return "인가 성공!";
	}
}