package dehkartes.preorder.member.service;

import dehkartes.preorder.member.component.CustomUserDetails;
import dehkartes.preorder.member.entity.Member;
import dehkartes.preorder.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member user = userRepository.findByName(username);
		if(user != null) {
			return new CustomUserDetails(user);
		}
		return null;
	}
}
