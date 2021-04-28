package JavaRecommendation.test;

import java.util.ArrayList;
import java.util.Collections;

import JavaRecommendation.controller.Pearson;
import JavaRecommendation.controller.PearsonCallable;
import JavaRecommendation.controller.PearsonExecutor;
import JavaRecommendation.data.MovieRepo;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Rating;
/**
 * Recommender Sequencial
 * TODO:
 * usage: testPearson.java <ratings file> <movie file> <user id> <num_recom>
 */
public class testPearson {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        UserRepo.init();
        MovieRepo.init();
        Pearson pearson = new Pearson();
        //PearsonExecutor pearson = new PearsonExecutor();
        //PearsonCallable pearson = new PearsonCallable();

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


 /**
         * 
         *  Top 10 filmes recomendados para o usuário: 463
         *  40491, "Match Factory Girl, The (Tulitikkutehtaan tyttö) (1990)" = 6.542424
            8477, "Jetée, La (1962)" = 6.542424
            87234, Submarine (2010) = 6.1773863
            3567, Bossa Nova (2000) = 6.1478786
            2314, Beloved (1998) = 6.079641
            4055, Panic (2000) = 6.079641
            156605, Paterson = 6.042424
            25771, "Andalusian Dog, An (Chien andalou, Un) (1929)" = 6.0206404
            4021, Before Night Falls (2000) = 5.9760647
            132333, Seve (2014) = 5.9405074
         */
