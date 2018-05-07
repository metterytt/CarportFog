/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rasmus
 */
public class Navbar extends Command {

    List<String> pages;

    public Navbar() {
        this.pages = new ArrayList();
        this.pages.add("login");
    }
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

    if(request.getParameter("logout") != null){
        if(request.getSession().getAttribute("employee") != null){
        request.getSession().removeAttribute("employee");}
        else{
            request.getSession().removeAttribute("customer");
        }
    }
        
        String page = null;
        for (String str : pages) {
            if (request.getParameter(str) != null) {
                page = request.getParameter(str);
            }
        }
        if (page != null) {
            return page;
        }
        else {
            throw new CarportException("Something went wrong, try reloading", "index");
        }
    }
}
