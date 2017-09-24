package model.service;

import model.dao.util.DaoFactory;
import model.entity.Order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private DaoFactory daoFactory;

    public OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder{
        static final OrderService INSTANCE = new OrderService(DaoFactory.getInstance());
    }

    public static OrderService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Order> selectCertainTimeOrders(Date from, Date to){
        return daoFactory.getOrderDao().selectCertainTimeOrders(from, to);
    }

    public boolean update(Order order){
        return daoFactory.getOrderDao().update(order);
    }

    public boolean insert(Order order){
        return daoFactory.getOrderDao().insert(order);
    }

    public Optional<Order> select(int id){
        return daoFactory.getOrderDao().select(id);
    }
}
