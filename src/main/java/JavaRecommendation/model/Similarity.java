package JavaRecommendation.model;

import JavaRecommendation.interfaces.User;

public class Similarity {
    private User simUser1;
    private User simUser2;
    private double similarity;

    public Similarity(User user1, User user2, double sim){
        simUser1 = user1;
        simUser2 = user2;
        similarity = sim; 
    }

    public User getSimUser1() {
        return simUser1;
    }
    public User getSimUser2() {
        return simUser2;
    }
    public double getSimilarity() {
        return similarity;
    }
}
