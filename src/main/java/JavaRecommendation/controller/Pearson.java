package JavaRecommendation.controller;

import java.util.ArrayList;

import JavaRecommendation.interfaces.SimilarityMetric;
import JavaRecommendation.interfaces.User;

public class Pearson implements SimilarityMetric{
    private static float min_rating = 1;
    private static float max_rating = 5;
    private double[][] simMatrix = null;
    private ArrayList<User> usersList;

    public Pearson(final ArrayList<User> users){
        System.out.println("Loading Pearson");
        simMatrix = new double[users.size()][users.size()];
        usersList = users;
        System.out.println("Calculing All Similarities");
        calculateAllSimilarity(users);

    }
    
    @Override
    /**
     * Calcula o coeficiente de Pearson(valor de similaridade) entre os 2 usuários
     */
    public double calculateSimilarity(User user1, User user2) {
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

        if (botton > 0) {
            int sizeCommon = commonMovies.size();
            if (sizeCommon < 50) {
                return (sizeCommon * (1.0/50)) * (top/botton);
            } else {
                return top / botton;
            }
        } else {
            return 0;            
        }
    }

    private void calculateAllSimilarity(ArrayList<User> users) {
        for (User user1 : users) {
            for (User user2 : users) {
                setSim(user1, user2, calculateSimilarity(user1, user2));
            }
        }
    }

    /**
     * Armazena o coeficiente de Pearson(valor de similaridade)
     * na memória entre os 2 usuários
     * @param user1 Primeiro usuário
     * @param user2 Segundo usuário
     * @param value valor de similaridade entre os 2 usuários
     */

    private void setSim(User user1, User user2, double value) {
        simMatrix[user1.getInternalId()][user2.getInternalId()] = value;
        simMatrix[user1.getInternalId()][user2.getInternalId()] = value;
    }

    

    @Override
    public float predictRating(User user, Integer movieId, float threshold) {
        ArrayList<User> neighbours = calculateNeighbours(user, threshold);
        float top = 0, botton = 0;
        float avgUser = user.getAvgRatings();
        //System.out.println("predict: " + user.getUserId() + ", " + movieId);
        //System.out.println("predict: " + neighbours.size());
        for (User neigh : neighbours) {
            if (neigh.hasRating(movieId)) {
                top += getPearson(user, neigh) * (neigh.getRating(movieId) - neigh.getAvgRatings());
                //System.out.println("predict: " + user.getUserId() + ", " + neigh.getUserId() + " = " + getPearson(user, neigh));
                botton += Math.abs(getPearson(user, neigh));
            }
        }
        //System.out.println("predict: avg/top/botton: "+ avgUser +","+ top + ", " + botton);
        if (botton > 0) {
            float prediction = avgUser + (top/botton);
            /*
            if (prediction < min_rating) {
                prediction = min_rating;
            }
            if (prediction > max_rating) {
                prediction = max_rating;
            }*/
            return prediction;
        } else {
            return -1;
        }


    }
    /**
     * Calcula o conjunto de vizinhos que serão usados ​​na previsão de uma
     * classificação de filme para um determinado usuário.
     * @param user
     * @param threshold
     * @return A lista de vizinhos que são mais semelhantes ao usuário fornecido
     */
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
    /**
     * Recupera o valor de Pearson calculado anteriormente entre 2 usuário da memória
     * @param user
     * @param other
     * @return O valor de similaridades entre os dois usuários
     */
    private double getPearson(User user, User other) {
        return simMatrix[user.getInternalId()][other.getInternalId()];
    }

    
}
