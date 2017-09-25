package model.dao;

import model.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> selectCertainTimeOrders(Date dateFrom, Date dateTo);
}