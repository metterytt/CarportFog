/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Jesper
 */
public class CarportLog {

    public Logger logger;
    FileHandler fh;

//   private String file = "/var/carportlogging/ExceptionLogs.txt";
//   private String file = "/Users/rasmu/Desktop/carportlogging/ExceptionLogs.txt";
    public CarportLog(String message, String filename) {
        try {
            File f = new File("/var/carportlogging/" + filename + ".txt");
            if (!f.exists()) {
                f.createNewFile();
            }

            fh = new FileHandler(filename, true);

            logger = Logger.getLogger(CarportLog.class.getName());
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.log(Level.SEVERE, message);
            fh.close();

        }
        catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

    }

}

//     private final static Logger LOGGER = Logger.getLogger(Logging.class.getName());
//    
//     private static FileHandler fh = null;
//
//    public static void initLogin() throws IOException {
//        try {
//            fh = new FileHandler("/Users/Jesper/Desktop/gitProjects/Carport/CarportFog/Logs/loginlogs.txt", true); // sidste parameter appende til eksisterende hvis true 
//
//        } catch (SecurityException | IOException e) {
//            e.printStackTrace();
//        }
//        fh.setFormatter(new SimpleFormatter());
//        LOGGER.addHandler(fh);
//    }
//
//    public static Logger getLOGGER() {
//        return LOGGER;
//    }
