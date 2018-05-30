package presentationLayer;

import functionLayer.CarportException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Directs the user to the desired jsp-page. Through a hidden field, a page from
 * the list is set. If the parameter matches one of the strings in the list, it
 * is returned for the user. If a user is logging out, the attribute is removed
 * from the session.
 *
 * @author Snøvsen
 */
public class Navbar extends Command {

    List<String> pages;

    public Navbar() {
        this.pages = new ArrayList();
        this.pages.add("login");
        this.pages.add("profile");
        this.pages.add("employee");
        this.pages.add("customer");
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        if (request.getParameter("logout") != null) {
            if (request.getSession().getAttribute("employee") != null) {
                request.getSession().removeAttribute("employee");
                return "index";
            } else {
                request.getSession().removeAttribute("customer");
                return "index";
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
        } else {
            throw new CarportException("Something went wrong, try reloading", "index");
        }
    }
}
