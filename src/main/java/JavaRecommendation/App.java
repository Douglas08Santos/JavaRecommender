package JavaRecommendation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import JavaRecommendation.model.Movie;

/**
 * Hello world!
 * TODO: Fazer o sistema de recomendação
 *  - Sequencial
 *  - Executor
 *  -  
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Loading Movies...");
        String csvPath = "src/resources/ml-100k/test.txt";
        try {
            System.out.println("Opening: " + csvPath);
            FileReader fileReader = new FileReader(csvPath);
            BufferedReader reader = new BufferedReader(fileReader);
            String header = reader.readLine();
            String line = "";
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                
                
               
            }
            reader.close();
            System.out.println("Loaded movies");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       
    }
}