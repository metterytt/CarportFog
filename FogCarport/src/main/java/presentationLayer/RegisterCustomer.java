/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesper
 */
public class RegisterCustomer extends Command{
    
     @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
    
      if(request.getParameter("registercustomer") != null){
            
            String username = request.getParameter("username");
            String psw1 = request.getParameter("password1");
            String psw2 = request.getParameter("password2");
            
            if(!psw1.equals(psw2)){
                request.setAttribute("error", "Passwords skal være ens!");
            }else{
                
                StorageFacade.registerCustomer(username, psw2);
                request.setAttribute("complete", "Brugeren er nu registreret og kan logge ind!");
            }
        }

   
        
    return "registeremployee";
    }
    
    
}

