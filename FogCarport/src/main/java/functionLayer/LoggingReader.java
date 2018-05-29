package functionLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Used to read the logginfiles, to display for an IT worker on the site.
 * @author Snøvsen
 */
public class LoggingReader {

    private File file;
    private List<String> list;
    private Scanner scan;

    public LoggingReader(String filename) {
        file = new File("/var/carportlogging/" + filename + ".txt");
        list = new ArrayList();
    }

    public List<String> getList() {
        return list;
    }

    public void readLog() throws CarportException {
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                list.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new CarportException("The logging file could not be located", "logging");
        }
    }
}
