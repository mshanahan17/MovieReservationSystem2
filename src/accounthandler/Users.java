package accounthandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Users {
    public static void newUser(String userName, String password){

        Properties prop = new Properties();
        OutputStream out = null;

        try {
            out = new FileOutputStream("data/config.properties");
            prop.setProperty(userName, password);

            prop.store(out, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
