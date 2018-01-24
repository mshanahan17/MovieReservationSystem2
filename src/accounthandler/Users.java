package accounthandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Users {
    public static void newUser(String userName, String password){

        Properties prop = new Properties();

        try {

            prop.setProperty(userName, password);
            prop.store(new FileOutputStream("account.properties"), null);
            System.out.println("test");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
