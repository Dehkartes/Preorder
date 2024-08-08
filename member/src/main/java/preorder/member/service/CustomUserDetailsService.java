package preorder.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import preorder.member.repository.MemberRepository;
import preorder.security.component.CustomUserDetails;
import preorder.security.entity.Member;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Member member = userRepository.findByEmail(email);
		if(member != null) {
			return new CustomUserDetails(member);
		}
		return null;
	}
}
