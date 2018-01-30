package exhibitions.controller.command;

import exhibitions.controller.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandResult {
    private Logger logger = Logger.getLogger(CommandResult.class);
    public final String property;
    public CommandResult(String property) {
        logger.info("In CommandResult constructor" );
        this.property = property;
    }
}