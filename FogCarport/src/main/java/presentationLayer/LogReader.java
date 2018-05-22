/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.LoggingReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rasmus
 */
public class LogReader extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        
        //load site here
        if(request.getParameter("load") != null){
            return "logging";
            
        }
        
         if(request.getParameter("showExceptionLogs") != null){
        //if param != null
        LoggingReader lr = new LoggingReader("/var/carportlogging/ExceptionLogs.txt");
        lr.readLog();
        request.setAttribute("logginglist", lr.getList());
         return "logging"; 
         }
       
            
      if(request.getParameter("showCustomerLog") != null){
        //if param != null
        LoggingReader lrCustomer = new LoggingReader("/var/carportlogging/LoginCustomer.txt");
        lrCustomer.readLog();
        request.setAttribute("logginglistCustomer", lrCustomer.getList());
            return "logging";
              }
        
        return "logging";
    }
    
}
