package ar.com.educacionit.movie.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Getter
//@Setter
@NoArgsConstructor //constructor vacio
@AllArgsConstructor
@Builder
public class MovieUpdateRequestDTO {
	
	@NotNull
	Long id; //1
	
	@NotBlank
	private String originalTitle;
	
	@NotBlank
	private String overview;
	
	@NotNull
	private Long popularity;
	
	@NotBlank
	private String posterPath;
	
	@NotNull
	private Float voteAverage;
	
	@NotNull
	@Min(0)
	@Max(10)
	private Integer voteCount;

	//@AllArgsConstructor
	/*public MovieUpdateRequestDTO(String originalTitle, String overview, Long popularity, String posterPath,
			Float voteAverage, Integer voteCount) {
		super();
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.popularity = popularity;
		this.posterPath = posterPath;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}*/

}
