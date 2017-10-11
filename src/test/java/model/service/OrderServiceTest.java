package model.service;

import model.dao.OrderDao;
import model.dao.impl.OrderDaoImpl;
import model.dao.util.ConnectionManager;
import model.dao.util.DaoFactory;
import model.entity.Order;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private DaoFactory daoFactory;
    private OrderService orderService;
    private OrderDao orderDao;
    private ConnectionManager connectionManager;

    private Order buildOrder() {
        return new Order.Builder()
                .addId(1)
                .addCar(Order.Car.BMW)
                .addDateStartRent(Date.valueOf(LocalDate.now()))
                .addDateEndRent(Date.valueOf(LocalDate.now()))
                .addStatus(Order.Status.ACCEPTED)
                .addComment("Comment")
                .addUserId(1)
                .createOrder();
    }

    private List<Order> buildOrders(){
        return Arrays.asList(buildOrder(), buildOrder());
    }

    private void initialization(){
        daoFactory = mock(DaoFactory.class);
        orderDao = mock(OrderDaoImpl.class);
        connectionManager = mock(ConnectionManager.class);
        orderService = new OrderService(daoFactory, connectionManager);
    }

    private void stubDaoFactory(){
        when(daoFactory.getOrderDao()).thenReturn(orderDao);
    }

    private void stubSelectOrderByStatus(List<Order> orders){
        stubDaoFactory();
        //when(orderDao.selectOrdersByStatus(Order.Status.ACCEPTED)).thenReturn(orders);
    }

    private void stubSelect(Order order){
        stubDaoFactory();
        when(orderDao.select(1)).thenReturn(Optional.of(order));
    }

    @Test
    public void testSelectOrderByStatus(){
        List<Order> orders = buildOrders();
        initialization();
        stubSelectOrderByStatus(orders);

        List<Order> actualOrders = orderService.selectOrderByStatus(Order.Status.ACCEPTED, 0, 5);
        assertEquals(orders, actualOrders);

        verify(daoFactory).getOrderDao();
        verify(orderDao).selectOrdersByStatus(Order.Status.ACCEPTED,0, 5);
    }

    @Test
    public void testUpdate(){
        Order order = buildOrder();
        initialization();
        stubDaoFactory();

        orderService.update(order);

        verify(daoFactory).getOrderDao();
        verify(orderDao).update(order);
    }

    @Test
    public void testUpdateStatusAndComment(){
        Order order = buildOrder();
        initialization();
        stubDaoFactory();

        orderService.updateStatusAndComment(order);

        verify(daoFactory).getOrderDao();
        verify(orderDao).updateStatusAndComment(order);
    }

    @Test
    public void testInsert(){
        Order order = buildOrder();
        initialization();
        stubDaoFactory();

        orderService.insert(order);

        verify(daoFactory).getOrderDao();
        verify(orderDao).insert(order);
    }


    @Test
    public void testSelect(){
        Order order = buildOrder();
        initialization();
        stubSelect(order);

        Order actualOrder = orderService.select(1).get();
        assertEquals(order, actualOrder);

        verify(daoFactory).getOrderDao();
        verify(orderDao).select(1);
    }

}
