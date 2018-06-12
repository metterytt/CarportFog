package functionLayer;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Used to log whenever an exception is thrown and when a customer logs in /
 * fails to.
 *
 * @author Snøvsen
 */
public class CarportLog {

    private Logger logger;
    private FileHandler fh;

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
            if (filename.equals("LoginCustomer")) {
                logger.log(Level.INFO, message);
            } else {
                logger.log(Level.SEVERE, message);
            }
            fh.close();

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
