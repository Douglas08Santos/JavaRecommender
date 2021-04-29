package JavaRecommendation.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import JavaRecommendation.model.Movie;

public class MovieRepo {
    private static HashMap<Integer, Movie> moviesMap;
    private static String filePath;
    
    /* 
    *   Read csv movie data
    */
    @SuppressWarnings("unused")
    private static void loadMovies(String filePath){
        try {
            System.out.println("Opening: " + filePath);
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String header = reader.readLine();
            String line = "";
            String limite = "";
            if (filePath.contains(".dat")) {
                limite = "::";
            } else {
                limite = ",";
            }
            while((line = reader.readLine()) != null){
                String[] data = line.split(limite);
                Integer movieId = Integer.parseInt(data[0]);
                String title = data[1];
                //Caso o titulo do filme possua ',' 
                for (int i = 2; i < data.length-1; i++) {
                    title = title + limite + data[i];
                }                
                String genres = data[data.length - 1];
                Movie newMovie = new Movie(movieId, title, genres);
                moviesMap.put(movieId, newMovie);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    /*
     *  Initialize movieMap 
     */
    public static void init(String filePath){
        if(moviesMap == null){
            moviesMap = new HashMap<Integer, Movie>();
            try {
                loadMovies(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
    }
    /*
     *  Initialize movieMap 
     */
    public static void init(){
        if(moviesMap == null){
            moviesMap = new HashMap<Integer, Movie>();
            if(filePath == null){
                filePath = "src/resources/ml-100k/movies.csv";
            }
            try {
                loadMovies(filePath);
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
