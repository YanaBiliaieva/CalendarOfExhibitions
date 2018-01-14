package controller;

import controller.command.FactoryCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecurityConfiguration {
    private static final SecurityConfiguration config = new SecurityConfiguration();

    private Map<String,String> grant = new HashMap<>();

    public static SecurityConfiguration getInstance(){
        return config;
    }

    private SecurityConfiguration() {
        grant.put(FactoryCommand.EXHIBITIONS,"ALL");
        grant.put(FactoryCommand.LOGIN,"ALL");
        grant.put(FactoryCommand.REGISTER,"ALL");
        grant.put(FactoryCommand.LOGOUT,"AUTH");
        grant.put(FactoryCommand.USERS,"AUTH");
        grant.put(FactoryCommand.HALLS,"AUTH");
        grant.put(FactoryCommand.EXPOSITIONS,"AUTH");
        grant.put(FactoryCommand.ORDERS,"AUTH");
        grant.put(FactoryCommand.PAYMENTS,"AUTH");
        grant.put(FactoryCommand.TICKETS,"AUTH");

        grant.put("/","ALL");
    }

    public String security(String command){
        return grant.get(command);
    }

    public Set<String> getEndPoints(){
        return grant.keySet();
    }
}
