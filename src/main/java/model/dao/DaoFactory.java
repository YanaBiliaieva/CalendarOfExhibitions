package model.dao;

public class DaoFactory {
    private DaoFactory(){};
    private static ClientDao clientDao = new ClientDao();
    private static DaoFactory daoFactory = new DaoFactory();
    public static DaoFactory getInstance(){
        return daoFactory;
    }

    public static ClientDao getClientDao(){
        return clientDao;
    }
}
