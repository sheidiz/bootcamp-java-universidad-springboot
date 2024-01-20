package ar.com.educacionit.movie.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ar.com.educacionit.movie.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserSecurity implements UserDetails {

	private final User user;//es mio, de mi db
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.user.getRoles()
				.stream()
				.map(x -> x.getRole() )//["ROLE_ADMIN","ROLE_USER"]			
				.map(SimpleGrantedAuthority::new)
				.toList()
				;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
