package exhibitions.util;
import exhibitions.transactions.ConnectionPool;
import exhibitions.transactions.ConnectionWrapper;
import exhibitions.transactions.TransactionManager;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class H2ConnectionManager {

    private static final String CREATE_DB_PATH = "src\\test\\resources\\createDB.sql";
    private static Logger logger = Logger.getLogger(H2ConnectionManager.class);
    private static H2ConnectionManager h2ConnectionManager = new H2ConnectionManager();

    private H2ConnectionManager() {
        initH2ConnectionPool();
        initializeDB();
    }

    public static H2ConnectionManager getH2ConnectionManager() {
        return h2ConnectionManager;
    }

    private static DataSource getH2DataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:exhibcalendar;Mode=MYSQL;DB_CLOSE_DELAY=-1");
        dataSource.setUser("root");
        dataSource.setPassword("rout");
        return dataSource;
    }

    public static void initH2ConnectionPool() {
        try {
            DataSource h2DataSource = getH2DataSource();
            ConnectionPool.initByDataSource(h2DataSource);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private void initializeDB() {
        executeSQLScriptsFromFile(CREATE_DB_PATH);
    }

    public void executeSQLScriptsFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException();
        }
        try {
            String script = new String(Files.readAllBytes(file.toPath()));
            script = script.replaceAll("[\\s]+", " ");
            String[] queries = script.split(";");
            Connection connection = getConnection();
            try {
                for (String query : queries) {
                    if (query.trim().isEmpty()) {
                        continue;
                    }
                    Statement statement = connection.createStatement();
                    statement.execute(query);
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                   logger.error(e);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void truncateTable(String tableName) throws SQLException {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {

            String sqlTruncate = "DELETE FROM " + tableName + ";";
            statement.executeUpdate(sqlTruncate);
        } catch (SQLException e) {
            throw new SQLException("FAILED TO TRUNCATE TABLE " + tableName);
        }
    }

    public Connection getConnection() {
        ConnectionWrapper wrapper=TransactionManager.getConnection();
        return wrapper.getConnection();

    }

    public static void releaseDataSource(){
        ConnectionPool.releaseDataSource();
    }
}
