package JavaRecommendation;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import JavaRecommendation.controller.Pearson;
import JavaRecommendation.controller.PearsonCallable;
import JavaRecommendation.controller.PearsonExecutor;
import JavaRecommendation.controller.PearsonParallelStream;
import JavaRecommendation.data.UserRepo;
import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.Rating;

@State(Scope.Benchmark)
public class TestBenchmark {
	
	Pearson pearson;
	PearsonExecutor pearsonEx;
	PearsonCallable pearsonCall;
	PearsonParallelStream pearsonStream;
	
	@Setup
	public void setup() {
		pearson = new Pearson();
		pearsonEx = new PearsonExecutor();
		pearsonCall = new PearsonCallable();
		pearsonStream = new PearsonParallelStream();
	}
	
	@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void Sequencial() {
    	int users[] = {19, 87, 30, 27, 96};
    	User myUser = UserRepo.getUser(users[new Random().nextInt(4)]);
    	System.out.println("Sequencial: " + myUser.getUserId());
        ArrayList<Rating> result = pearson.getRecommendations(myUser);
        //System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        System.out.println(result.subList(0, 10));
    
    }
    
	@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void Executor() {
    	int users[] = {19, 87, 30, 27, 96};
    	User myUser = UserRepo.getUser(users[new Random().nextInt(4)]); 
    	System.out.println("Executor: " + myUser.getUserId());
        ArrayList<Rating> result = pearsonEx.getRecommendations(myUser);
        //System.out.println("\nTop 10 filmes recomendados para o usuário: " + myUser.getUserId());
        System.out.println(result.subList(0, 10));
    }
	@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void Callabe() {
    	int users[] = {19, 87, 30, 27, 96};
    	User myUser = UserRepo.getUser(users[new Random().nextInt(4)]);
    	System.out.println("Callable: " + myUser.getUserId());
        ArrayList<Rating> result = pearsonCall.getRecommendations(myUser);
        System.out.println(result.subList(0, 10));
    }
	@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void ParallelStream() {
    	int users[] = {19, 87, 30, 27, 96};
    	User myUser = UserRepo.getUser(users[new Random().nextInt(4)]);
    	System.out.println("\nParallelStream: " + myUser.getUserId());
        ArrayList<Rating> result = pearsonStream.getRecommendations(myUser);
        System.out.println(result.subList(0, 10));
    }
}
