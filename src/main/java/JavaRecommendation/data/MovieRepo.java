package JavaRecommendation.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import JavaRecommendation.model.Movie;

public class MovieRepo {
    private static HashMap<Integer, Movie> moviesMap;
    
    /* 
    *   Read csv movie data
    */
    @SuppressWarnings("unused")
    private static void loadMovies(){
        System.out.println("Loading Movies...");
        String csvPath = "src/resources/ml-100k/movies.csv";
        try {
            System.out.println("Opening: " + csvPath);
            FileReader fileReader = new FileReader(csvPath);
            BufferedReader reader = new BufferedReader(fileReader);
            String header = reader.readLine();
            String line = "";
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                Integer movieId = Integer.parseInt(data[0]);
                String title = data[1];
                String genres = data[2];
                Movie newMovie = new Movie(movieId, title, genres);
                moviesMap.put(movieId, newMovie);
            }
            reader.close();
            System.out.println("Loaded movies");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    /*
     *  Initialize movieMap 
     */
    public static void init(){
        if(moviesMap == null){
            moviesMap = new HashMap<Integer, Movie>();
            try {
                loadMovies();
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
    }

    public static boolean containsID(Integer movieID) {
		init();
		return moviesMap.containsKey(movieID);
	}
	
	public static String getTitle(Integer movieID) {
		init();
		return moviesMap.get(movieID).getTitle();
	}
	
	public static String getGenres(Integer movieID) {
		init();
		return moviesMap.get(movieID).getGenres();
	}
	
	public static int size() {
		init();
		return moviesMap.size();
	}
	
	public static ArrayList<Movie> getMovies() {
		init();
		ArrayList<Movie> result = new ArrayList<Movie>(moviesMap.values());
		return result;
	}
	

}
