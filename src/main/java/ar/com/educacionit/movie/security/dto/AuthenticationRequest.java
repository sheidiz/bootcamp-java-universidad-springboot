package ar.com.educacionit.movie.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {
	//validations!
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}

