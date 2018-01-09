package model.dao;

import model.entities.Client;
import exception.DAOException;
import transactions.ConnectionWrapper;
import transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {

    private static final String SQL = "select * from clients where login = ?";

    ClientDao() {
    }

    public Client getByLogin(String login) throws SQLException, DAOException {
        ConnectionWrapper con = TransactionManager.getConnection();
        try {
            PreparedStatement statement = con.preparedStatement(SQL);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setName(resultSet.getString("name"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                return client;
            }
        } catch (SQLException e){
            throw  new DAOException();
        } finally {
            con.close();
        }
        return null;
    };

    public void register(String name, String login, String password){}

    public void insert(Client client) {
    }
}
