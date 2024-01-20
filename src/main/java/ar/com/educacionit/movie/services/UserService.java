package ar.com.educacionit.movie.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.educacionit.movie.domain.User;
import ar.com.educacionit.movie.repository.UserRepository;

@Service
public class UserService {
	
	//@Autowired
	//private UserMovieRepository userRepository;
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> obtenerListado() {
		return this.userRepository.findAll();
	}
}
