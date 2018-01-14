package controller.command;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class FactoryCommand {
    private Logger logger = Logger.getLogger(FactoryCommand.class);
    private static final FactoryCommand factoryCommand = new FactoryCommand();
    public static final String LOGIN = "/loginUser";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";
    public static final String USERS = "/users";
    public static final String HALLS = "/halls";
    public static final String EXPOSITIONS = "/expositions";
    public static final String ORDERS = "/order";
    public static final String PAYMENTS = "/payments";
    public static final String TICKETS = "/tickets";
    public static final String EXHIBITIONS = "/";
    public static final String ERROR = "/error";
    private Map<String, Command> commandMap = new HashMap<>();

    private FactoryCommand() {
        logger.info("In FactoryCommand constructor");
        commandMap.put(LOGIN, new LoginCommand());
        commandMap.put(REGISTER, new RegistrationCommand());
        commandMap.put(LOGOUT, new LogoutCommand());
        commandMap.put(USERS, new UsersCommand());
        commandMap.put(HALLS, new HallsCommand());
        commandMap.put(EXPOSITIONS, new ExpositionsCommand());
        commandMap.put(ORDERS, new OrdersCommand());
        commandMap.put(PAYMENTS, new PaymentsCommand());
        commandMap.put(TICKETS, new TicketsCommand());
        commandMap.put(EXHIBITIONS, new ExhibitionsCommand());
       // commandMap.put(ERROR, new ErrorCommand());
    }

    public static FactoryCommand getInstance() {
        return factoryCommand;
    }

    public Command getCommand(String command) {
        return commandMap.get(command);

    }

}
