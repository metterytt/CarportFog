/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import dbAccess.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Used to log whenever an exception is thrown and when a customer logs in / fails to.
 * @author Snøvsen
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

            fh = new FileHandler(f.getPath(), true);

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
