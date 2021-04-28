package JavaRecommendation.test;

import JavaRecommendation.data.MovieRepo;

public class testMovieRepo {
    public static void main(String[] args) {
        /*
        *   Teste Movie Repository
        */
        System.out.println(MovieRepo.size());
        int movieid = 40491;
        /*
        *   movieId: 193587
            title: Bungo Stray Dogs: Dead Apple (2018)
            genres: Action|Animation
        */
        System.out.println(MovieRepo.containsID(movieid));
        System.out.println(MovieRepo.getTitle(movieid));
        System.out.println(MovieRepo.getGenres(movieid));

        
    }
}
