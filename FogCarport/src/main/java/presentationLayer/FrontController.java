package presentationLayer;

import functionLayer.CarportLog;
import functionLayer.CarportException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Before going to a new jsp-page, everything goes here to execute a new servlet.
 * First a servlet is found in Command, and then executed.
 * Depending on the returned page, the user is forwarded or an exception is thrown for the user on a given page.
 * Each time an exception is thrown, it will get logged for an IT-employee to view.
 * @author Snøvsen
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Command moveForward = Command.from(request);
            String page = moveForward.execute(request, response);
            if (page.equals("index")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/" + page + ".jsp").forward(request, response);
            }
        } catch (CarportException ex) {
            request.setAttribute("error", ex.getMessage());

            new CarportLog(ex.getMessage(), "ExceptionLogs");

            if (ex.getPage().equals("index")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/" + ex.getPage() + ".jsp").forward(request, response);

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
