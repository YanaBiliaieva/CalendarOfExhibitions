package exhibitions.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Pool {
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<Connection>(10, true);
    private static String url = "jdbc:mysql://127.0.0.1:3306/exhibcalendar";
    private static String user = "root";
    private static String password = "rout";

    private void createPool() {
        for (int i = 0; i < 20; i++) {
            try {
                pool.add(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return conn; //возвращать прокси - оболочку для соединения чтобы пользователь мог только получать соединение, которым может управлять только пул
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                pool.put(conn);
            } catch (InterruptedException e) {
                e.printStackTrace();//можно через два списка - перебросить данное соединение из одного списка в другой
            }
        }
    }

}
