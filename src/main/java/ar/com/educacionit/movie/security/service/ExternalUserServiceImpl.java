package ar.com.educacionit.movie.security.service;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ar.com.educacionit.movie.dto.ReqResUser;

@Component
public class ExternalUserServiceImpl implements ExternalUserService {

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public ReqResUser getUserById(Long id) {
		
		// create headers
		HttpHeaders headers = new HttpHeaders();

		// set `Content-Type` and `Accept` headers
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// example of custom header
		headers.set("X-Request-Source", "Desktop");

		// build the request
		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<ReqResUser> userBody = restTemplate.exchange("https://reqres.in/api/users/"+id, HttpMethod.GET,
				request, ReqResUser.class);
		// https://www.baeldung.com/spring-resttemplate-logging

		ReqResUser user = userBody.getBody();
		
		return user;
	}

}
