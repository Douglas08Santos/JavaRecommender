package JavaRecommendation.controller;


import JavaRecommendation.interfaces.SimilarityMetric;
import JavaRecommendation.interfaces.User;

public class PearsonFork implements SimilarityMetric{

    @Override
    public void calculateSimilarity(User user1, User user2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public float predictRating(User user, Integer movieId, float threshold) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
