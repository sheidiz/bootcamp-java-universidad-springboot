package ar.com.educacionit.movie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.educacionit.movie.domain.Movie;
import ar.com.educacionit.movie.repository.MovieRepository;

@Service
public class MovieService {
	
	private final MovieRepository userRepository;
	
	public MovieService(MovieRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<Movie> obtenerListado() {
		return this.userRepository.findAll();
	}
	
	//@Transactional(value = TxType.REQUIRED)
	public Movie obtenerPorId(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}
	
	public void eliminarPorId(Long id) {
		this.userRepository.deleteById(id);
	}

	public Optional<Movie> buscarPorTitulo(String originalTitle) {
		//spring jpa + query method
		return this.userRepository.findByOriginalTitle(originalTitle);
	}

	public void crearMovie(Movie newMovie) {
		this.userRepository.save(newMovie);		
	}
}
