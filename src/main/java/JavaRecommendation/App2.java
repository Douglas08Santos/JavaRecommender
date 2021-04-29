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


public class App2
{
    public static void main( String[] args )
    {
        String movieFile = "src/resources/ml-1m/movies.dat";
        String ratingFile = "src/resources/ml-1m/ratings.dat";
        UserRepo.init(ratingFile);
        MovieRepo.init(movieFile);
        int nTest = 5;
        Pearson pearson = new Pearson();
        PearsonExecutor pearsonExecutor = new PearsonExecutor();
        PearsonCallable pearsonCallable = new PearsonCallable();
        PearsonParallelStream pearsonStream = new PearsonParallelStream();
        User myUser;
        String fastest = "";
        long fastestTime = 0;
        System.out.println("Teste Recommender Sequencial");
        long total = 0;
        for (int i = 0; i < nTest; i++) {
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
        fastest = "Sequencial";
        fastestTime = total;
        System.out.println();
        System.out.println("Teste Recommender Executor");
        total = 0;
        for (int i = 0; i < nTest; i++) {
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
        if(fastestTime > total){
            fastest = "Executor";
            fastestTime = total;
        }
        System.out.println();
        System.out.println("Teste Recommender Callable");
        total = 0;
        for (int i = 0; i < nTest; i++) {
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
        if(fastestTime > total){
            fastest = "Callable";
            fastestTime = total;
        }
        System.out.println();
        System.out.println("Teste Recommender ParallelStream");
        total = 0;
        for (int i = 0; i < nTest; i++) {
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
        if(fastestTime > total){
            fastest = "ParallelStream";
            fastestTime = total;
        }
        System.out.println("Fastest: " + fastest + " = " + fastestTime/5.0/1000 + " ms" );
        System.out.println();     
    }
        
}