package JavaRecommendation.controller;


import java.util.ArrayList;
import java.util.Collections;

import JavaRecommendation.data.MovieRepo;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.SimilarityMetric;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Rating;

public class PearsonParallelStream implements SimilarityMetric{
    private ArrayList<User> usersList;
    ArrayList<Rating> recommendations;
    private User lastUser = null;

    public PearsonParallelStream(){
        ArrayList<User> users = UserRepo.getUsers();
        usersList = users;
        System.out.println("Parallel Initialized");
    }

    
    @Override
    public void calculateSimilarity(User user1, User user2) {
        ArrayList<Integer> commonMovies = user1.getCommomMovies(user2);
        float avgUser1 = user1.getAvgRatings();
        float avgUser2 = user2.getAvgRatings();
        float top = 0, bottonUser1 = 0, bottonUser2 = 0;


        for (Integer movieId : commonMovies) {
            float user1diff = user1.getRating(movieId) - avgUser1;
            float user2diff = user2.getRating(movieId) - avgUser2;
            top = (user1diff * user2diff);
            bottonUser1 = bottonUser1 + (user1diff * user1diff);
            bottonUser2 = bottonUser2 + (user2diff * user2diff);
        }

        double botton = Math.sqrt(bottonUser1 * bottonUser2);
        
        /**
         * Armazena o coeficiente de Pearson(valor de similaridade)
         * na memória entre os 2 usuários
         * 
         */
        if (botton > 0) {
            int sizeCommon = commonMovies.size();
            if (sizeCommon < 50) {
                double value = (sizeCommon * (1.0/50)) * (top/botton);
                user1.addSim(user2.getUserId(), value);
                user2.addSim(user1.getUserId(), value);
            } else {
                double value = top / botton;
                user1.addSim(user2.getUserId(), value);
                user2.addSim(user1.getUserId(), value);
            }
        } else {
            user1.addSim(user2.getUserId(), 0.0);
            user2.addSim(user1.getUserId(), 0.0);          
        }
    }
    /**
     * Recupera o valor de Pearson calculado anteriormente entre 2 usuário da memória
     * @param user
     * @param other
     * @return O valor de similaridades entre os dois usuários
     */
    private double getPearson(User user, User other) {
        if (user.hasSim(other.getUserId())) {
            return user.getSim(other.getUserId());
        } else {
            //Se não possui sim, calcula
            calculateSimilarity(user, other);
            return user.getSim(other.getUserId());
        }
    }
    
    @Override
    public float predictRating(User user, Integer movieId, float threshold) {
        ArrayList<User> neighbours = calculateNeighbours(user, threshold);
        float top = 0, botton = 0;
        float avgUser = user.getAvgRatings();
        for (User neigh : neighbours) {
            if (neigh.hasRating(movieId)) {
                top += getPearson(user, neigh) * (neigh.getRating(movieId) - neigh.getAvgRatings());
                botton += Math.abs(getPearson(user, neigh));
            }
        }
        if (botton > 0) {
            float prediction = avgUser + (top/botton);
            return prediction;
        } else {
            return -1;
        }
    }
    /**
     * Gerar a lista de filmes recomendados para o user
     * @param user
     */
    private void generateRecommendations(User user){
        recommendations = new ArrayList<Rating>();
        usersList.parallelStream().forEach(curr ->{
            if(user.getUserId() != curr.getUserId()){
                getPearson(user, curr);                    
            }
        });
        MovieRepo.getMovies().parallelStream()
        .forEach(movie -> {
            float predictedRating = predictRating(user, movie.getMovieId(), 0.0f);            
            Rating rating = new Rating(movie.getMovieId(), predictedRating);
            recommendations.add(rating);
        });

        Collections.sort(recommendations, Collections.reverseOrder());

    }

    /**
     * 
     * @return a lista de filmes recommendados
     */
    public ArrayList<Rating> getRecommendations(User user) {
        //Primeira vez
        if (lastUser == null) {
            generateRecommendations(user);
            lastUser = user;
            return recommendations;
        }
        //Outras consultas
        if (lastUser.getUserId() == user.getUserId()) {
            return recommendations;
        }else{
            generateRecommendations(user);
            lastUser = user;
            return recommendations;
        }
        
    }

    
    /**
     * Calcula o conjunto de vizinhos que serão usados ​​na previsão de uma
     * classificação de filme para um determinado usuário.
     * @param user
     * @param threshold
     * @return A lista de vizinhos que são mais semelhantes ao usuário fornecido
     */
    //Target
    private ArrayList<User> calculateNeighbours(User user, float threshold) {
        ArrayList<User> result = new ArrayList<User>();
        for (User current : usersList) {
            if(user.getUserId() != current.getUserId()){
                if(getPearson(user, current) > threshold){
                    result.add(current);
                }
            }
        }
        return result;
    }
   
}
