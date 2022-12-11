import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import gimkitBot.Classic;

public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));

        String id = props.getProperty("id");
        String name = props.getProperty("name");

        Classic gBot = new Classic(id, name);
        
        gBot.start();
        gBot.stop();
    }
}