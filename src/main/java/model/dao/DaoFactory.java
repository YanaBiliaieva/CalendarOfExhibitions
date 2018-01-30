package model.dao;

import model.dao.implementation.*;

/** Class containing methods for returning dao objects for different entities. */
public class DaoFactory {
    /** @return the MySQL-oriented implementation of the IUserDAO interface. */
    public static UserDaoImpl getUserDaoImpl(){
        return new UserDaoImpl();
    }
    public static HallDaoImpl getHallDaoImpl(){
        return new HallDaoImpl();
    }
    public static PaymentDaoImpl getPaymentDaoImpl(){
        return new PaymentDaoImpl();
    }
    public static ExpositionDaoImpl getExpositionDaoImpl(){
        return new ExpositionDaoImpl();
    }
    public static TicketsDaoImpl getTicketsDaoImpl(){
        return new TicketsDaoImpl();
    }
    public static CityDaoImpl getCityDaoImpl(){
        return new CityDaoImpl();
    }
}
