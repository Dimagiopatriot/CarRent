package model.dao.impl;

import model.dao.OrderDao;
import model.dao.util.ConnectionManager;
import model.dao.util.JdbcConnection;
import model.entity.Order;
import util.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao{

    private ConnectionManager connectionManager;

    private final static String COLUMN_ID = "id";
    private final static String COLUMN_CAR = "car";
    private final static String COLUMN_DATE_FROM = "date_from";
    private final static String COLUMN_DATE_TO = "date_to";
    private final static String COLUMN_STATUS = "order_status";
    private final static String COLUMN_COMMENT = "comment";
    private final static String COLUMN_USER_ID = "order_user_id";

    private final static int COLUMN_CAR_INDEX = 1;
    private final static int COLUMN_DATE_FROM_INDEX = 2;
    private final static int COLUMN_DATE_TO_INDEX = 3;
    private final static int COLUMN_STATUS_INDEX = 4;
    private final static int COLUMN_COMMENT_INDEX = 5;
    private final static int COLUMN_USER_ID_INDEX = 6;
    private final static int COLUMN_ID_INDEX = 7;


    private final static String SELECT_QUERY_BY_STATUS = "SELECT * FROM car_rent.order WHERE order_status=?";
    private final static String SELECT_QUERY_BY_ID = "SELECT * FROM car_rent.order WHERE id=?;";
    private final static String UPDATE_QUERY  = "UPDATE car_rent.order SET car=?, date_from=?, date_to=?, " +
            "order_status=?, comment=?, order_user_id=? WHERE id=?;";
    private final static String INSERT_QUERY = "INSERT INTO car_rent.order(car, date_from, date_to, order_status, " +
            "comment, order_user_id) VALUES (?, ?, ?, ?, ?, ?);";

    public OrderDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder {
        static final OrderDaoImpl INSTANCE = new OrderDaoImpl(ConnectionManager.getInstance());
    }

    public static OrderDaoImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Order> selectOrdersByStatus(Order.Status status) {
        List<Order> orders = new ArrayList<>();
        try(JdbcConnection connection = connectionManager.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(SELECT_QUERY_BY_STATUS)) {
            statement.setString(1, status.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    orders.add(buildOrder(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return orders;
    }

    @Override
    public boolean update(Order order) throws DaoException {
        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_QUERY)){

            statement.setString(COLUMN_CAR_INDEX, order.getCar().toString());
            statement.setDate(COLUMN_DATE_FROM_INDEX, order.getDateStartRent());
            statement.setDate(COLUMN_DATE_TO_INDEX, order.getDateEndRent());
            statement.setString(COLUMN_STATUS_INDEX, order.getStatus().toString());
            statement.setString(COLUMN_COMMENT_INDEX, order.getComment());
            statement.setInt(COLUMN_USER_ID_INDEX, order.getUserId());
            statement.setInt(COLUMN_ID_INDEX, order.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean insert(Order order) throws DaoException {
        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(COLUMN_CAR_INDEX, order.getCar().toString());
            statement.setDate(COLUMN_DATE_FROM_INDEX, order.getDateStartRent());
            statement.setDate(COLUMN_DATE_TO_INDEX, order.getDateEndRent());
            statement.setString(COLUMN_STATUS_INDEX, order.getStatus().toString());
            statement.setString(COLUMN_COMMENT_INDEX, order.getComment());
            statement.setInt(COLUMN_USER_ID_INDEX, order.getUserId());
            updatedRow = statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    order.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public Optional<Order> select(int id) throws DaoException {
        Optional<Order> order = Optional.empty();
        try(JdbcConnection connection = connectionManager.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            order = Optional.of(buildOrder(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

        return order;
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        return new Order.Builder()
                .addId(resultSet.getInt(COLUMN_ID))
                .addCar(Order.Car.valueOf(resultSet.getString(COLUMN_CAR).toUpperCase()))
                .addDateStartRent(resultSet.getDate(COLUMN_DATE_FROM))
                .addDateEndRent(resultSet.getDate(COLUMN_DATE_TO))
                .addStatus(Order.Status.valueOf(resultSet.getString(COLUMN_STATUS).toUpperCase()))
                .addComment(resultSet.getString(COLUMN_COMMENT))
                .addUserId(resultSet.getInt(COLUMN_USER_ID))
                .createOrder();
    }
}
