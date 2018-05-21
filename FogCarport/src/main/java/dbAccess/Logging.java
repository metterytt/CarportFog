/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Jesper
 */
public class Logging {
    
     private final static Logger logger = Logger.getLogger(Logging.class.getName());
    
     private static FileHandler fh = null;

    public static void initLogin() throws IOException {
        try {
            fh = new FileHandler("/Users/Jesper/Desktop/gitProjects/Carport/CarportFog/Logs/loginlogs.txt", true); // sidste parameter appende til eksisterende hvis true 

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
    }

    public static Logger getLogger() {
        return logger;
    }
    
    
    
}
