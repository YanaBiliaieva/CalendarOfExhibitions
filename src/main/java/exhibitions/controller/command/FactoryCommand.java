package exhibitions.controller.command;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class FactoryCommand {


    private Logger logger = Logger.getLogger(FactoryCommand.class);
    private static final FactoryCommand factoryCommand = new FactoryCommand();
    public static final String ADD_CITY = "/addcity";
    public static final String ADMIN_PANEL = "/adminpanel";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";
    public static final String USERS = "/users";
    public static final String HALLS = "/createhall";
    public static final String EXPOSITION = "/exposition";
    public static final String ORDER = "/order";
    public static final String PAYMENTS = "/payments";
    public static final String TICKETS = "/tickets";
    public static final String EXHIBITIONS = "/exhibitions";
    public static final String ERROR = "/error";
    public static final String CREATE_EXHIBITION = "/createex";
    public static final String CABINET ="/cabinet" ;
    private Map<String, Object> commandMap = new HashMap<>();

    private FactoryCommand() {
        logger.info("In FactoryCommand constructor");

        commandMap.put(LOGIN, new LoginCommand());
        commandMap.put(REGISTER, new RegistrationCommand());
        commandMap.put(LOGOUT, new LogoutCommand());
        commandMap.put(USERS, new UsersCommand());
        commandMap.put(EXPOSITION, new ExpositionsCommand());
        commandMap.put(ORDER, new OrdersCommand());
        commandMap.put(PAYMENTS, new PaymentsCommand());
        commandMap.put(TICKETS, new TicketsCommand());
        commandMap.put(EXHIBITIONS, new ExhibitionsCommand());
        commandMap.put(ERROR, new ErrorCommand());
        commandMap.put(ADMIN_PANEL,new AdminPanelCommand());
        commandMap.put(CREATE_EXHIBITION,new CreateExhibitionCommand());
        commandMap.put(HALLS, new HallsCommand());
        commandMap.put(ADD_CITY, new CityCommand());
        commandMap.put(CABINET, new CabinetCommand());
    }

    public static FactoryCommand getInstance() {
        return factoryCommand;
    }

    public Object getCommand(String command) {
        return commandMap.get(command);
    }

}
