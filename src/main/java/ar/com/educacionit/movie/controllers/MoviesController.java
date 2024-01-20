package ar.com.educacionit.movie.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.educacionit.movie.domain.Movie;
import ar.com.educacionit.movie.domain.MovieGenre;
import ar.com.educacionit.movie.dto.MovieRequestDTO;
import ar.com.educacionit.movie.dto.MovieUpdateRequestDTO;
import ar.com.educacionit.movie.dto.ReqResUser;
import ar.com.educacionit.movie.exceptions.MyBadRequestValidException;
import ar.com.educacionit.movie.security.service.ExternalUserService;
import ar.com.educacionit.movie.security.service.FeignExternalUserService;
import ar.com.educacionit.movie.services.MovieService;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/movie")
public class MoviesController {
	
	
	private final MovieService movieService;
	private final ExternalUserService externalUserServie;
	
	public MoviesController(MovieService movieService, ExternalUserService externalUserServie) {
		this.movieService = movieService;
		this.externalUserServie = externalUserServie;
	}
	
	@GetMapping
	//si no tiene @PreAuthorize no valida!
	public List<Movie> findAllMovies() {
		return movieService.obtenerListado();
	}
	
	//una pelicula dada si id 
	@GetMapping("/{id}") 
	@Operation(
			summary = "Obtiene una pelicula dado su ID",
			description = "consulta la tabla MOVIE",
			tags = {"movie","get"}
	)
	@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				content = {
					@Content(
							schema = @Schema(implementation = Movie.class),
							mediaType = "application/json"
					)					
				}
			),
			@ApiResponse(
				responseCode = "401",
				content = {@Content(schema = @Schema())}
			),
			@ApiResponse(
					responseCode = "500",
					content = {@Content(schema = @Schema())}
				)
		}
	)
	public ResponseEntity<Movie> buscarPorId(
			@PathVariable("id") Long id
		) {
		
		Movie movie = movieService.obtenerPorId(id);
		
		//a un servicio externo!!!
		//http://algo.com.ar/api/v1/algo
		//RestTemplate (OK)
		//Feign(OK)
		
		//HttpClient (queda pendiente)
		//ReqResUser user = externalUserServie.getUserById(1L);
		
		FeignExternalUserService feignService = Feign.builder()
			.client(new OkHttpClient())
			.encoder(new GsonEncoder())
			.decoder(new GsonDecoder())
			.target(FeignExternalUserService.class, "https://reqres.in/api/users/"+id);
	
		ReqResUser user = feignService.getUserById();
		
		System.out.println(user);
		
		if(movie == null) {
			return ResponseEntity.notFound().build();			
		}
		
		return ResponseEntity.ok(movieService.obtenerPorId(id));
	}
	
	//crear una pelicula
	@PostMapping
	public ResponseEntity<Long> crearMovie (
				@RequestBody MovieRequestDTO request
			) {
		
		//el post no es idempotente
		
		Optional<Movie> movie = this.movieService.buscarPorTitulo(request.getOriginalTitle());
		if(movie.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(movie.get().getId());
		}
		
		//lo crea y luego lo retorna
		Movie newMovie = new Movie();
		newMovie.setAdult(request.getAdult());
		newMovie.setBackdropPath(request.getBackdropPath());
		
		List<MovieGenre> genres = request.getGenreIds()
			.stream()
			.map(x -> new MovieGenre(x))
			.collect(Collectors.toList());
		newMovie.setGenreIds(genres);
		
		newMovie.setOriginalLanguage(request.getOriginalLanguage());
		newMovie.setOriginalTitle(request.getOriginalTitle());
		newMovie.setOverview(request.getOverview());
		newMovie.setPopularity(request.getPopularity());
		newMovie.setPosterPath(request.getPosterPath());
		newMovie.setReleaseDate(request.getReleaseDate());
		newMovie.setTitle(request.getTitle());
		newMovie.setVideo(request.getVideo());
		newMovie.setVoteAverage(request.getVoteAverage());
		
		newMovie.setVoteCount(request.getVoteCount());
		
		this.movieService.crearMovie(newMovie);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newMovie.getId());
	}
	
	
	//eliminar una peli dado si id 
	@DeleteMapping("/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Void> eliminarPorId(
			@PathVariable("id") Long id
		) {
		
		try {
			movieService.eliminarPorId(id);
		
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	//actualizar una peli
	@PutMapping("/{id}")//2
	public ResponseEntity<Void> update(
			@PathVariable(name = "id", required = true) Long id,
			@Validated @RequestBody MovieUpdateRequestDTO request
		) {
		
		if(!id.equals(request.getId()) ) {
			throw new MyBadRequestValidException(
				"los ids son distintos"
			);
		}
		//idempotente (ver como hacer)
		
		//buscar y no existe!!!
		//throw new MethodArgumentNotValidException(null, null) 
		
		//agregan la logica!!!
		
		return null;
	}
	
	//agregar endpoint para recibir binario!
	// blob / file system! url > obtener luego la imgen
	
}
