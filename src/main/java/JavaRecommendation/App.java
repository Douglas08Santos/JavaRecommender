package JavaRecommendation;

import java.util.ArrayList;
import java.util.Collections;

import JavaRecommendation.controller.Pearson;
import JavaRecommendation.controller.PearsonCallable;
import JavaRecommendation.controller.PearsonExecutor;
import JavaRecommendation.data.MovieRepo;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Rating;

public class App 
{
    public static void main( String[] args )
    {
        long start = System.currentTimeMillis();
        UserRepo.init();
        MovieRepo.init();
        //Pearson pearson = new Pearson();
        //PearsonExecutor pearson = new PearsonExecutor();
        PearsonCallable pearson = new PearsonCallable();

        //O usuario que receberá as recomendações
        User myUser = UserRepo.getUser(463);  
        //Calcula as recomendações
        ArrayList<Rating> result = pearson.getRecommendations(myUser);
        //Organiza as recomendações recebidas
        Collections.sort(result, Collections.reverseOrder());
        //Imprimi os 10 como melhores indicações ao usuário
        System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        for (Rating rating : result.subList(0, 10)) {
            System.out.println(rating.getItem() + ", " + 
                MovieRepo.getTitle(rating.getItem()) +" = " +
                rating.getValue());                   
        }  
        long end = System.currentTimeMillis();   
        System.out.println((end - start)/1000.0 + " segs"); 
    }
        
}