package JavaRecommendation;

import java.util.ArrayList;
import java.util.Random;

import JavaRecommendation.controller.Pearson;
import JavaRecommendation.controller.PearsonCallable;
import JavaRecommendation.controller.PearsonExecutor;
import JavaRecommendation.controller.PearsonParallelStream;
import JavaRecommendation.data.MovieRepo;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Rating;


public class App 
{
    public static void main( String[] args )
    {
        String movieFile = "src/resources/ml-10m/movies.dat";
        String ratingFile = "src/resources/ml-10m/ratings.dat";
        UserRepo.init(ratingFile);
        MovieRepo.init(movieFile);
        int nTest = 5;
        Pearson pearson = new Pearson();
        //PearsonExecutor pearson = new PearsonExecutor();
        //PearsonCallable pearson = new PearsonCallable();
        //PearsonParallelStream pearson = new PearsonParallelStream();
        User myUser;
        long total = 0;
        for (int i = 0; i < nTest; i++) {
            /**Gerando id aleátorios de 1 a 100 */
            myUser = UserRepo.getUser(new Random().nextInt(100) + 1);
            System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
            long start = System.currentTimeMillis();
            ArrayList<Rating> result = pearson.getRecommendations(myUser);
            long end = System.currentTimeMillis();
            total += end - start;
            for (Rating rating : result.subList(0, 10)) {
                System.out.println(rating + ", " + 
                    MovieRepo.getTitle(rating.getItem()));                  
            }
            System.out.println("Tempo " + (i+1) +": "+ (end - start)/1000.0); 
        }
        System.out.println("Tempo médio: " + (total/5.0)/1000 + " ms");
        System.out.println();     
    }
        
}