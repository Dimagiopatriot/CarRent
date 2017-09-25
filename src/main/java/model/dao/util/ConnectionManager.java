package model.dao.util;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;

public class ConnectionManager {

    private DataSource dataSource;
    private static ThreadLocal<JdbcConnection> connectionThreadLocal = new ThreadLocal<>();

    private ConnectionManager() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/carRentDB");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class Holder {
        static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    public static ConnectionManager getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized JdbcConnection getConnection() {
        JdbcConnection connection = connectionThreadLocal.get();
        if(connection == null) {
            try {
                return new JdbcConnection(dataSource.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return connection;
        }
    }

    public void startTransaction(){
        JdbcConnection connection = getConnection();
        connection.startTransaction();
        connectionThreadLocal.set(connection);
    }
    public void commit(){
        JdbcConnection connection = connectionThreadLocal.get();
        connection.commit();
        close(connection);
    }

    public void rollback(){
        JdbcConnection connection = connectionThreadLocal.get();
        connection.rollback();
        close(connection);
    }

    private void close(JdbcConnection connection){
        connectionThreadLocal.remove();
        connection.close();
    }
}
