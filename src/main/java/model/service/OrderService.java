package model.service;

import model.dao.util.ConnectionManager;
import model.dao.util.DaoFactory;
import model.entity.Damage;
import model.entity.Order;
import model.entity.User;
import util.exception.DaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private DaoFactory daoFactory;
    private ConnectionManager connectionManager;

    public OrderService(DaoFactory daoFactory, ConnectionManager connectionManager) {
        this.daoFactory = daoFactory;
        this.connectionManager = connectionManager;
    }

    private static class Holder{
        static final OrderService INSTANCE = new OrderService(DaoFactory.getInstance(), ConnectionManager.getInstance());
    }

    public static OrderService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Order> selectOrderByStatus(Order.Status status, int offset, int limit){
        return daoFactory.getOrderDao().selectOrdersByStatus(status, offset, limit);
    }

    public int selectOrdersByStatusCount(Order.Status status){
        return daoFactory.getOrderDao().selectOrdersByStatusCount(status);
    }

    public List<Order> selectOrderByUserId(int userId){
        return daoFactory.getOrderDao().selectByUserId(userId);
    }

    public boolean update(Order order){
        return daoFactory.getOrderDao().update(order);
    }

    public boolean updateStatusAndComment(Order order){
        return daoFactory.getOrderDao().updateStatusAndComment(order);
    }

    public boolean updateWithUserCountChange(Order order, User user){
        boolean isUpdated = false;
        try {
            connectionManager.startTransaction();
            daoFactory.getUserDao().updateUserCount(user);
            isUpdated = daoFactory.getOrderDao().update(order);
            connectionManager.commit();
        } catch (DaoException ex){
            connectionManager.rollback();
        }
        return isUpdated;
    }

    public boolean insert(Order order){
        return daoFactory.getOrderDao().insert(order);
    }

    public Optional<Order> select(int id){
        return daoFactory.getOrderDao().select(id);
    }
}
