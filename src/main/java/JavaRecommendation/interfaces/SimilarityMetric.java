package JavaRecommendation.interfaces;


public interface SimilarityMetric {
    /**
     * Calcula o valor de similaridade entre os 2 usuários
     * @param user1 Primeiro usuário
     * @param user2 Segundo usuário
     * @return valor de similaridade entre os 2 usuários 
     */
    public double calculateSimilarity(User user1, User user2);
    /**
     * Prevê a classificação de um filme para um determinado 
     * usando usando a métrica de similaridade
     * @param user
     * @param movieId
     * @param threshold
     * @return a avaliação prevista que o usuário teria feito para aquele filme
     */
    public float predictRating(User user, Integer movieId, float threshold);
}
