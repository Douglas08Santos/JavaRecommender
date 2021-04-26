package JavaRecommendation.test;

import java.util.ArrayList;
import java.util.Collections;

import JavaRecommendation.controller.Pearson;
import JavaRecommendation.data.MovieRepo;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Movie;
import JavaRecommendation.model.Rating;

public class testPearson {
    public static void main(String[] args) {
        ArrayList<User> users = UserRepo.getUsers();
        Pearson pearson = new Pearson(users);
        float threshold = 0;

        User myUser = UserRepo.getUser(148);
        System.out.println(myUser.getUserId());
        
        //ArrayList<Integer> myUserRated = myUser.getMoviesRated();
        ArrayList<Movie> allmovies = MovieRepo.getMovies();
        //int count = 0;
        ArrayList<Rating> recommender = new ArrayList<Rating>();
        for (Movie movie: allmovies) {
            //if (myUserRated.contains(movie.getMovieId())) {
                float predictedRating = pearson.predictRating(myUser, movie.getMovieId(), threshold);
                
                Rating rating = new Rating(movie.getMovieId(), predictedRating);
                recommender.add(rating);
                
        }
        Collections.sort(recommender, Collections.reverseOrder());
        for (Rating rating : recommender.subList(0, 10)) {
            if(rating.getValue() >= 1){
                System.out.println(rating.getItem() + ", " + rating.getValue());
            }
                
        }
       
       


    }
}
