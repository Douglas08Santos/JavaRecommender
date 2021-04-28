package JavaRecommendation.test;

import java.util.ArrayList;
import java.util.Collections;

import JavaRecommendation.controller.Pearson;
import JavaRecommendation.data.MovieRepo;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Movie;
import JavaRecommendation.model.Rating;
/**
 * TODO:
 * usage: testPearson.java <ratings file> <movie file> <user id> <num_recom>
 */
public class testPearson {
    public static void main(String[] args) {
        //Carrega todo os usuários
        ArrayList<User> users = UserRepo.getUsers();
        //Inicializa o pearson
        Pearson pearson = new Pearson(users);
        float threshold = 0;

        //O usuario que receberá as recomendações
        User myUser = UserRepo.getUser(148);
        
        //A lista de todos os filmes
        ArrayList<Movie> allmovies = MovieRepo.getMovies();
        //Salvar as recomendações
        ArrayList<Rating> recommender = new ArrayList<Rating>();
        //Calcula as recomendações
        for (Movie movie: allmovies) {
            //if (myUserRated.contains(movie.getMovieId())) {
                float predictedRating = pearson.predictRating(myUser, movie.getMovieId(), threshold);
                
                Rating rating = new Rating(movie.getMovieId(), predictedRating);
                recommender.add(rating);
                
        }
        //Organiza as recomendações recebidas
        Collections.sort(recommender, Collections.reverseOrder());
        //Imprimi os 10 como melhores indicações ao usuário
        System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        for (Rating rating : recommender.subList(0, 10)) {
            System.out.println(rating.getItem() + ", " + 
                MovieRepo.getTitle(rating.getItem()) +" = " +
                rating.getValue());                   
        }         
    }
}
