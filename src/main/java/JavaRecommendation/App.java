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
        String movieFile = "src/resources/ml-100k/movies.csv";
        String ratingFile = "src/resources/ml-100k/ratings.csv";
        UserRepo.init(ratingFile);
        MovieRepo.init(movieFile);

        Pearson pearson = new Pearson();
        PearsonExecutor pearsonExecutor = new PearsonExecutor();
        PearsonCallable pearsonCallable = new PearsonCallable();
        PearsonParallelStream pearsonStream = new PearsonParallelStream();
        User myUser; 
        System.out.println("Teste Recommender Sequencial");
        long total = 0;
        for (int i = 0; i < 5; i++) {
            /**Gerando id aleátorios de 1 a 100 */
            myUser = UserRepo.getUser(new Random().nextInt(100) + 1);
            long start = System.currentTimeMillis();
            ArrayList<Rating> result = pearson.getRecommendations(myUser);
            long end = System.currentTimeMillis();
            total += end - start;
            System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
            for (Rating rating : result.subList(0, 10)) {
                System.out.println(rating + ", " + 
                    MovieRepo.getTitle(rating.getItem()));                  
            }
            System.out.println("Tempo " + (i+1) +": "+ (end - start)/1000.0); 
        }
        System.out.println("Tempo médio: " + (total/5.0)/1000 + " ms");
        System.out.println();
        System.out.println("Teste Recommender Executor");
        total = 0;
        for (int i = 0; i < 5; i++) {
            /**Gerando id aleátorios de 1 a 100 */
            myUser = UserRepo.getUser(new Random().nextInt(100) + 1);
            long start = System.currentTimeMillis();
            ArrayList<Rating> result = pearsonExecutor.getRecommendations(myUser);
            long end = System.currentTimeMillis();
            total += end - start;
            System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
            for (Rating rating : result.subList(0, 10)) {
                System.out.println(rating + ", " + 
                    MovieRepo.getTitle(rating.getItem()));                  
            }
            System.out.println("Tempo " + (i+1) +": "+ (end - start)/1000.0); 
        }
        System.out.println("Tempo médio: " + (total/5.0)/1000 + " ms");
        System.out.println();
        System.out.println("Teste Recommender Callable");
        total = 0;
        for (int i = 0; i < 5; i++) {
            /**Gerando id aleátorios de 1 a 100 */
            myUser = UserRepo.getUser(new Random().nextInt(100) + 1);
            long start = System.currentTimeMillis();
            ArrayList<Rating> result = pearsonCallable.getRecommendations(myUser);
            long end = System.currentTimeMillis();
            total += end - start;
            System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
            for (Rating rating : result.subList(0, 10)) {
                System.out.println(rating + ", " + 
                    MovieRepo.getTitle(rating.getItem()));                  
            }
            System.out.println("Tempo " + (i+1) +": "+ (end - start)/1000.0); 
        }
        System.out.println("Tempo médio: " + (total/5.0)/1000 + " ms");
        System.out.println();
        System.out.println("Teste Recommender ParallelStream");
        total = 0;
        for (int i = 0; i < 5; i++) {
            /**Gerando id aleátorios de 1 a 100 */
            myUser = UserRepo.getUser(new Random().nextInt(100) + 1);
            long start = System.currentTimeMillis();
            ArrayList<Rating> result = pearsonStream.getRecommendations(myUser);
            long end = System.currentTimeMillis();
            total += end - start;
            System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
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