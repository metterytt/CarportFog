package functionLayer;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
