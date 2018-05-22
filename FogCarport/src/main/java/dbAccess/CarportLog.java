/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Jesper
 */
public class CarportLog {
    
   public Logger logger;
   FileHandler fh;
   
   public CarportLog(String file_name){
       try{
       File f = new File(file_name);
       if(!f.exists()){
           f.createNewFile();
       }
       
       fh = new FileHandler(file_name, true);
       logger = Logger.getLogger("test");
       logger.addHandler(fh);
       SimpleFormatter formatter = new SimpleFormatter();
       fh.setFormatter(formatter);
       
       
       }catch(SecurityException | IOException e){
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