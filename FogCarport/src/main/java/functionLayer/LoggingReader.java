/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Rasmus
 */
public class LoggingReader {

    private File file;
    private List<String> list;
    private Scanner scan;

    public LoggingReader(String filename) {
//        file = new File("/var/carportlogging/ExceptionLogs.txt");
            file = new File(filename);
//        this.file = new File("/Users/rasmu/Desktop/carportlogging/ExceptionLogs.txt");
        this.list = new ArrayList();
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
        }
        catch (FileNotFoundException e) {
            throw new CarportException("The logging file could not be located", "logging");
        }
    }
}
