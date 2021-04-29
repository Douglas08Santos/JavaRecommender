package JavaRecommendation.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import JavaRecommendation.interfaces.User;
import JavaRecommendation.model.MyUser;

public class UserRepo {
    private static HashMap<Integer, User> usersMap;
    private static String filePath = "src/resources/ml-10m/ratings.dat";
    @SuppressWarnings("unused")
    private static void loadUsers(String filePath) {
        try {
            System.out.println("Open: " + filePath);
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
            int count = 0;
            while((line = reader.readLine()) != null){
                String[] data = line.split(limite);
                Integer userId = Integer.parseInt(data[0]);
                Integer movieId = Integer.parseInt(data[1]);
                Float rating = Float.parseFloat(data[2]);
                String ts = data[3];
                count++;
                //System.out.println(count);
                
                addRating(userId, movieId, rating);
                
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    public static void init(String ratingsFile){
        if(usersMap == null){
            usersMap = new HashMap<Integer, User>();
            loadUsers(ratingsFile);
        }
    }

    public static void init(){
        if(usersMap == null){
            usersMap = new HashMap<Integer, User>();
            loadUsers(filePath);
        }
    }

    public static void addRating(Integer userId, Integer movieId, float rating) {
        init();
        User user = null;
        if(usersMap.containsKey(userId)){
            user = usersMap.get(userId);
        }else{
            user = new MyUser(userId);
            usersMap.put(userId, user);
        }
        user.addRating(movieId, rating);
    }

    public static User getUser(Integer userId) {
		init();
		return usersMap.get(userId);
	}
	
	public static ArrayList<User> getUsers() {
		init();
		ArrayList<User> result = new ArrayList<User>(usersMap.values());		
		return result;
	}
	
	public static int size() {
		init();
		return usersMap.size();
	}
}
