package model.dao;

import model.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> selectOrdersByStatus(Order.Status status, int offset, int limit);
    List<Order> selectByUserId(int userId);
    int selectOrdersByStatusCount(Order.Status status);
    boolean updateStatusAndComment(Order order);
}
