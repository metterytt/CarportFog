package presentationLayer;

import functionLayer.CarportException;
import functionLayer.LoggingReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * All logs are fetched and put into a list to be shown on logging.jsp.
 *
 * @author Snøvsen
 */
public class LogReader extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        LoggingReader lr = new LoggingReader("ExceptionLogs");
        lr.readLog();
        request.setAttribute("exceptionLoggingList", lr.getList());

        LoggingReader lrCustomer = new LoggingReader("LoginCustomer");
        lrCustomer.readLog();
        request.setAttribute("customerLoginLoggingList", lrCustomer.getList());

        return "logging";
    }

}
