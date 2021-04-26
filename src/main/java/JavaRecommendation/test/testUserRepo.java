package JavaRecommendation.test;

import java.util.ArrayList;

import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;

public class testUserRepo {
    public static void main(String[] args) {
        //Teste userRepo
        
        System.out.println(UserRepo.size());
        int userid = 2;
        
        ArrayList<User> users =  UserRepo.getUsers();

        for (User user : users) {
            System.out.println(user.getUserId());
        }

        /*
        *   Filme avaliados pelos userid = 2 
        */
        int[] search = {318, 333, 1704, 3578, 6874, 8798, 46970,
            48516, 58559, 60756, 68157, 71535, 74458, 77455, 79132,
            80489, 80906, 86345, 89774, 91529, 91658, 99114, 106782,
            109487, 112552, 114060, 115713, 122882, 131724};

        User user = UserRepo.getUser(userid);
        System.out.println(user.getMoviesRated());
        ArrayList<Integer> movieRated = user.getMoviesRated();
        System.out.println(movieRated.size());
        for (int i : search) {
            if(movieRated.contains(i)){
                System.out.println(i + " OK");
            }else{
                System.out.println(i + " Error");
            }
        }
        System.out.println(0);
    }
}
