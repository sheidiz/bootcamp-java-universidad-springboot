package ar.com.educacionit.movie.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//el nombre de atributo es igual al de la base!

	@Column(name = "adult", nullable = false) // NOT NULL
	private Boolean adult;

	@Column(name="backdrop_path", length = 255, nullable = true)
	private String backdropPath;

	@OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
	private List<MovieGenre> genreIds;

	@Column(name = "originalLanguage") // NOT NULL
	private String originalLanguage;

	@Column(name = "original_title", unique = true, length = 255)
	private String originalTitle;

	@Column(name = "overview") // NOT NULL
	private String overview;

	@Column(name = "popularity") // NOT NULL
	private Double popularity;

	@Column(name = "poster_path") // NOT NULL
	private String posterPath;

	@Column(name = "release_date") // NOT NULL
	private String releaseDate;

	@Column(name = "title") // NOT NULL
	private String title;

	@Column(name = "video") // NOT NULL
	private Boolean video;

	@Column(name = "vote_average") // NOT NULL
	private Double voteAverage;

	@Column(name = "vote_count") // NOT NULL
	private Integer voteCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getVideo() {
		return video;
	}

	public void setVideo(Boolean video) {
		this.video = video;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", adult=" + adult + ", backdropPath=" + backdropPath + ", originalLanguage="
				+ originalLanguage + ", originalTitle=" + originalTitle + ", overview=" + overview + ", popularity="
				+ popularity + ", posterPath=" + posterPath + ", releaseDate=" + releaseDate + ", title=" + title
				+ ", video=" + video + ", voteAverage=" + voteAverage + ", voteCount=" + voteCount + "]";
	}

}