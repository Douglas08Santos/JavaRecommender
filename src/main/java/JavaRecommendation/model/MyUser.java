package JavaRecommendation.model;

import java.util.ArrayList;
import java.util.HashMap;

import JavaRecommendation.interfaces.User;


public class MyUser implements User{

    public static int COUNT = 0;
    private Integer userId;
    private int internalId;
    private HashMap<Integer, Rating> userRatings;
    private HashMap<Integer, Double> usersSim;

    public MyUser(Integer newUserId){
        userId = newUserId;
        userRatings = new HashMap<Integer, Rating>();
        usersSim = new HashMap<Integer, Double>();
        internalId = COUNT;
        COUNT++;
    }
    
    @Override
    public int getInternalId() {
        return internalId;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }
    public HashMap<Integer, Rating> getUserRatings() {
        return userRatings;
    }
    public HashMap<Integer, Double> getUsersSim() {
        return usersSim;
    }

    @Override
    public void addRating(Integer movieId, float rating) {
        userRatings.put(movieId, new Rating(movieId, rating));
        
    }
    @Override
    public boolean hasRating(Integer movieId) {
        return userRatings.containsKey(movieId);
    }

    @Override
    /**
     * Retorna uma lista contendo os filmes que foram avaliados
     * por 2 usuarios
     */
    public ArrayList<Integer> getCommomMovies(User other) {
        ArrayList<Integer> userRatings = getMoviesRated();
        ArrayList<Integer> common = new ArrayList<Integer>();

        for(Integer movieId: userRatings){
            if(other.hasRating(movieId)){
                common.add(movieId);
            }
        }
        return common;
    }

    @Override
    /**
     * Retorna a avaliação do usuário de determinado filme
     */
    public float getRating(Integer movieId) {
        if (hasRating(movieId)) {
            return userRatings.get(movieId).getValue();
        }else{
            return -1;
        }
        
    }

    @Override
    /**
     * Retorna o numero de avaliação que um usuário fez
     */
    public int numRatings() {
        return userRatings.size();
    }

    
    @Override
    /**
     * Retorna a avaliação média que o usuário fez em todos os filmes 
     */
    public float getAvgRatings() {
        float total = 0;
        float result = 0;
        int numRatings = numRatings();
        if (numRatings > 0) {
            for (Rating rating : userRatings.values()) {
                total = total + rating.getValue();
            }
            result = total / numRatings;
            return result;
        } else {
            return -1;
        }        
    }

    @Override
    public ArrayList<Integer> getMoviesRated() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(Integer movieId:userRatings.keySet()){
            result.add(movieId);
        }
        return result;
    }

    @Override
    public void addSim(Integer otherId, Double sim) {
        usersSim.put(otherId, sim);        
    }

    @Override
    public boolean hasSim(Integer otherId) {
       return usersSim.containsKey(otherId);
    }

    @Override
    public Double getSim(Integer otherId) {
        if (hasSim(otherId)) {
            return usersSim.get(otherId);
        }else{
            return -1.0;
        }
    }


   
    
    
}
