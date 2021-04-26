package JavaRecommendation.model;

/*
 * Informações classe Movie.java
 * Classe responsavel por representar os dados dos filmes
 */
public class Movie {
  
    private Integer movieId;    
    private String title;
    private String genres;

    public Movie(Integer newmovieid, String newtitle, String newgenres) {
        movieId = newmovieid;
        title = newtitle.trim();
        genres = newgenres.trim();
    }
    public Integer getMovieId() {
        return movieId;
    }
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        String result = "Movie: [movie_id = "+ movieId +", title = "
				+ title + ", genres = " + genres + "];\n";					
		return result;
    }
}
