/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("navbar", new Navbar());
        commands.put("inputcarport", new InputCarport());
        commands.put("sendrequest", new SendRequest());
        commands.put("registeremployee", new RegisterEmployee());
        commands.put("allcalculations", new AllCalculations());
        commands.put("backtoemp", new BackToEmp());
        commands.put("allrequests", new AllRequests());

    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException;

}
