/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tendencias;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

/**
 *
 * @author Normalito
 */
public class TwitterDivisor {
    
    private static Status status;
    public static void main(String[] args) {
        beginDivision();
    }
    
    private static void beginDivision(){
        String file="C:\\Users\\Normalito\\Downloads\\tweetsPapaStream.txt";
        //Gson gson = new Gson();
        
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream.forEach(
                    s-> {
                try {
                    status=TwitterObjectFactory.createStatus(s);
                    PrintWriter writer = new PrintWriter("C:\\Users\\Normalito\\Documents\\Tweets\\"+status.getUser().getScreenName()+".txt", "UTF-8");
                    writer.println(status.getText());
                    writer.close();
                } catch (TwitterException ex) {
                    Logger.getLogger(TwitterDivisor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TwitterDivisor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(TwitterDivisor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
        }       
        catch (IOException ex) {
            Logger.getLogger(TwitterDivisor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
