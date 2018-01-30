package exhibitions.transactions;

import exhibitions.exception.TransactionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionManager {
    private static ThreadLocal<ConnectionWrapper> threadLocal = new ThreadLocal<>();
    private static Logger logger = Logger.getLogger(TransactionManager.class);
    private TransactionManager() {
    }

    public static void beginTransaction() throws SQLException, TransactionException {
        if (Objects.nonNull(threadLocal.get()))
            throw new TransactionException();
        Connection connection = ConnectionPool.getConnection();
        ConnectionWrapper wrapper = new ConnectionWrapper(connection,true);
        threadLocal.set(wrapper);
    }

    public static void endTransaction() throws SQLException, TransactionException {
        if (Objects.isNull(threadLocal.get()))
            throw new TransactionException();
        ConnectionWrapper wrapper = threadLocal.get();
        wrapper.getConnection().close();
        threadLocal.set(null);
    }

    public static ConnectionWrapper getConnection()  {
        if (Objects.isNull(threadLocal.get())){
            Connection connection = null;
            try {
                connection = ConnectionPool.getConnection();
            } catch (SQLException e) {
                logger.error(e);
            }
            ConnectionWrapper wrapper = new ConnectionWrapper(connection,false);
            return wrapper;
        } else {return threadLocal.get();}
    }
}
