package ar.com.educacionit.movie.security.service;

import ar.com.educacionit.movie.dto.ReqResUser;

public interface ExternalUserService {
	public ReqResUser getUserById(Long id);
}
