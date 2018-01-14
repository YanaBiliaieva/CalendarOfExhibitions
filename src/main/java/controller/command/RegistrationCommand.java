package controller.command;

import org.apache.log4j.Logger;
import exception.DAOException;
import exception.RegistrationException;
import exception.TransactionException;
import services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrationCommand implements Command {
    private Logger logger = Logger.getLogger(RegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(Objects.isNull(name) || Objects.isNull(login) || Objects.isNull(password)){
                request.setAttribute("errorMessage","All fields must been not null");
                request.getRequestDispatcher(FactoryCommand.REGISTER).forward(request,response);
            return name;
        }
        RegisterService registerService = RegisterService.getRegisterService();
        try {
            registerService.register(name, login, password);
            request.getRequestDispatcher("/").forward(request,response);
        } catch (RegistrationException e){
            request.setAttribute("errorMessage",e.getMessage());
            request.getRequestDispatcher(FactoryCommand.REGISTER).forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
