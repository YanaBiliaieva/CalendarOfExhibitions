package exhibitions.controller;

import exhibitions.controller.command.FactoryCommand;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecurityConfiguration {
    private Logger logger = Logger.getLogger(SecurityConfiguration.class);
    private static final SecurityConfiguration config = new SecurityConfiguration();

    private Map<String,String> grant = new HashMap<>();

    public static SecurityConfiguration getInstance(){
        return config;
    }

    private SecurityConfiguration() {
        grant.put(FactoryCommand.ORDER,"AUTH");
        grant.put(FactoryCommand.TICKETS,"AUTH");
        grant.put(FactoryCommand.LOGOUT,"AUTH");

        grant.put(FactoryCommand.HALLS,"ADMIN");
        grant.put(FactoryCommand.ADMIN_PANEL,"ADMIN");
        grant.put(FactoryCommand.PAYMENTS,"ADMIN");
        grant.put(FactoryCommand.CREATE_EXHIBITION,"ADMIN");

        grant.put(FactoryCommand.ADD_CITY,"ADMIN");
        grant.put(FactoryCommand.USERS,"ADMIN");
        grant.put(FactoryCommand.EXHIBITIONS,"ALL");
        grant.put(FactoryCommand.LOGIN,"ALL");
        grant.put(FactoryCommand.REGISTER,"ALL");
        grant.put(FactoryCommand.EXPOSITION,"ALL");


        //grant.put(FactoryCommand.DEFAULT,"ALL");
    }

    public String security(String command){
        return grant.get(command);
    }

    public Set<String> getEndPoints(){
        logger.info("grant.keySet()="+grant.keySet());
        return grant.keySet();
    }
}
