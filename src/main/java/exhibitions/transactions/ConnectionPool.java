package exhibitions.transactions;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionPool {
    private static InitialContext initialContext;
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        try {
            initialContext = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/exhibcalendar");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource.getConnection();
    }

    public static void initByDataSource(DataSource h2DataSource) {
        if (Objects.nonNull(dataSource)) {
            throw new IllegalStateException("Data source already initialized");
        }
        dataSource = h2DataSource;
    }
    public synchronized static void releaseDataSource() {
        dataSource = null;
    }
}
