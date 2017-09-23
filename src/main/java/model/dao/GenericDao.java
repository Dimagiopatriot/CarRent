package model.dao;

import model.dao.util.SQLConnector;
import util.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public interface GenericDao<T> {

    default boolean delete(int id, String deleteQuery) {
        int updatedRow = 0;
        try (Connection connection = DriverManager.getConnection(SQLConnector.URL, SQLConnector.USER, SQLConnector.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, id);
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRow > 0;
    }

    boolean update(T t) throws DaoException;

    boolean insert(T t) throws DaoException;

    Optional<T> select(int id) throws DaoException;
}

