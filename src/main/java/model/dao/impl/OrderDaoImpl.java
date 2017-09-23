package model.dao.impl;

import model.dao.OrderDao;
import model.entity.Order;
import util.exception.DaoException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao{

    private final static String COLUMN_ID = "id";
    private final static String COLUMN_CAR = "car";
    private final static String COLUMN_DATE_FROM = "date_from";
    private final static String COLUMN_DATE_TO = "date_to";
    private final static String COLUMN_STATUS = "order_status";
    private final static String COLUMN_COMMMENT = "comment";
    private final static String COLUMN_USER_ID = "order_user_id";

    @Override
    public List<Order> selectCertainTimeOrders(Date dateFrom, Date dateTo) {
        return null;
    }

    @Override
    public boolean update(OrderDao orderDao) throws DaoException {
        return false;
    }

    @Override
    public boolean insert(OrderDao orderDao) throws DaoException {
        return false;
    }

    @Override
    public Optional<OrderDao> select(int id) throws DaoException {
        return null;
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        return new Order.Builder()
                .addId(resultSet.getInt(COLUMN_ID))
                .addCar(Order.Car.valueOf(resultSet.getString(COLUMN_CAR).toUpperCase()))
                .addDateStartRent(resultSet.getDate(COLUMN_DATE_FROM))
                .addDateEndRent(resultSet.getDate(COLUMN_DATE_TO))
                .addStatus(Order.Status.valueOf(resultSet.getString(COLUMN_STATUS).toUpperCase()))
                .addComment(resultSet.getString(COLUMN_COMMMENT))
                .addUserId(resultSet.getInt(COLUMN_USER_ID))
                .createOrder();
    }
}
