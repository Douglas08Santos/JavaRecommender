package JavaRecommendation;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import JavaRecommendation.interfaces.User;


public class TestBenchmark {
	
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void Sequencial() {
    	User myUser = UserRepo.getUser(19);
        Pearson pearson = new Pearson();
        ArrayList<Rating> result = pearson.getRecommendations(myUser);
        System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        System.out.println(result.subList(0, 10));
    
    }
    
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void Executor() {
    	User myUser = UserRepo.getUser(19);
        PearsonExecutor pearson = new PearsonExecutor();
        ArrayList<Rating> result = pearson.getRecommendations(myUser);
        System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        System.out.println(result.subList(0, 10));
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void Callabe() {
    	User myUser = UserRepo.getUser(19);
        PearsonCallable pearson = new PearsonCallable();
        ArrayList<Rating> result = pearson.getRecommendations(myUser);
        System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        System.out.println(result.subList(0, 10));
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void ParallelStream() {
    	User myUser = UserRepo.getUser(19);
        PearsonParallelStream pearson = new PearsonParallelStream();
        ArrayList<Rating> result = pearson.getRecommendations(myUser);
        System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        System.out.println(result.subList(0, 10));
    }
}
