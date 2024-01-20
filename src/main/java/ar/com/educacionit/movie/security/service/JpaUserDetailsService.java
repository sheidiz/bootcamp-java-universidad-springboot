package ar.com.educacionit.movie.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.educacionit.movie.domain.User;
import ar.com.educacionit.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username); 
		
		if(user == null) {
			throw new UsernameNotFoundException(username + " Not found");
		}
				
		return new UserSecurity(user);
	}

}
