package JavaRecommendation.interfaces;

import java.util.ArrayList;

public interface User {
	public Integer getUserId();
	public int getInternalId();
    public void addRating(Integer movieId, float rating);
	public boolean hasRating(Integer movieId);
    public ArrayList<Integer> getCommomMovies(User other);
	public float getRating(Integer movieId);
	public int numRatings();
	public float getAvgRatings();
	public ArrayList<Integer> getMoviesRated();
	public void addSim(Integer otherId, Double sim);
	public boolean hasSim(Integer otherId);
	public Double getSim(Integer otherId);

}
