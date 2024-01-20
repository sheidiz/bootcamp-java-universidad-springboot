package ar.com.educacionit.movie.security.service;

import org.springframework.stereotype.Component;

import ar.com.educacionit.movie.dto.ReqResUser;
import feign.RequestLine;

@Component
public interface FeignExternalUserService{
	
	@RequestLine("GET")
	public ReqResUser getUserById();

}
