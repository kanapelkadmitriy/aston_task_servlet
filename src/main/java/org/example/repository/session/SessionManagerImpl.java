package org.example.repository.session;

import org.example.repository.datasource.DataSourceHikariPostgreSQL;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SessionManagerImpl implements SessionManager{

    private static final int TIMEOUT_IN_SECONDS = 10;
    private final DataSource dataSource;
    private Connection connection;

    public SessionManagerImpl() {
        this.dataSource = DataSourceHikariPostgreSQL.getHikariDataSource();
    }

    @Override
    public Connection getCurrentSession() {
        checkConnection();
        return connection;
    }

    @Override
    public void beginSession() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void commitSession() {
        checkConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void rollbackSession() {
        checkConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void close() {
        checkConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    private void checkConnection() {
        try {
            if (connection == null || !connection.isValid(TIMEOUT_IN_SECONDS)) {
                throw new SessionManagerException("Connection is invalid");
            }
        } catch (SQLException ex) {
            throw new SessionManagerException(ex);
        }
    }
}
