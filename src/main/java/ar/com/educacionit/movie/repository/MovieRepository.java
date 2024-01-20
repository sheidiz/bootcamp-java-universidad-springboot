package ar.com.educacionit.movie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.educacionit.movie.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	public Optional<Movie> findByOriginalTitle(String title);
}
