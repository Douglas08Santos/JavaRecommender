package JavaRecommendation;

import java.util.ArrayList;
import java.util.Scanner;

import JavaRecommendation.controller.Pearson;
import JavaRecommendation.controller.PearsonCallable;
import JavaRecommendation.controller.PearsonExecutor;
import JavaRecommendation.controller.PearsonParallelStream;
import JavaRecommendation.data.MovieRepo;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Rating;

public class App3 {
    public static void main(String[] args) {
        String movieFile = "src/resources/ml-100k/movies.csv";
        String ratingFile = "src/resources/ml-100k/ratings.csv";
        UserRepo.init(ratingFile);
        MovieRepo.init(movieFile);
        int nTest = 5;
        int users[] = {19, 87, 30, 27, 96};
        Pearson pearson = new Pearson();
        PearsonExecutor pearsonExecutor = new PearsonExecutor();
        PearsonCallable pearsonCallable = new PearsonCallable();
        PearsonParallelStream pearsonStream = new PearsonParallelStream();
        User myUser;
        int optPearson = -1;
        int userId;
        long start = 0, end = 0;
        ArrayList<Rating> result = new  ArrayList<Rating>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema de Recomendação");
        System.out.println("1 - Sequencial");
        System.out.println("2 - Executor");
        System.out.println("3 - Callable");
        System.out.println("4 - ParallelStream");
        System.out.println("0 - Sair");
        optPearson = scanner.nextInt();
        while(optPearson != 0){   
            /**Gerando id aleátorios de 1 a 100 */
            System.out.println("Digite o userid: ");
            userId = scanner.nextInt();
            myUser = UserRepo.getUser(userId);
            switch (optPearson) {
                case 1:
                    start = System.currentTimeMillis();
                    result = pearson.getRecommendations(myUser);
                    end = System.currentTimeMillis();
                    break;
                case 2:
                    start = System.currentTimeMillis();
                    result = pearsonExecutor.getRecommendations(myUser);
                    end = System.currentTimeMillis();
                case 3:
                    start = System.currentTimeMillis();
                    result = pearsonCallable.getRecommendations(myUser);
                    end = System.currentTimeMillis();
                case 4:
                    start = System.currentTimeMillis();
                    result = pearsonStream.getRecommendations(myUser);
                    end = System.currentTimeMillis();                
                default:
                    break;
            }
            System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
            for (Rating rating : result.subList(0, 10)) {
                System.out.println(rating + ", " + 
                    MovieRepo.getTitle(rating.getItem()));                  
            }
            System.out.println("Tempo : "+ (end - start)/1000.0);
            System.out.println();
            System.out.println("Sistema de Recomendação");
            System.out.println("1 - Sequencial");
            System.out.println("2 - Executor");
            System.out.println("3 - Callable");
            System.out.println("4 - ParallelStream");
            System.out.println("0 - Sair");
            optPearson = scanner.nextInt();
        }
        scanner.close();
            
    }
}

