package controller.command;

import model.entity.Order;
import model.service.OrderService;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetOrdersForAdminCommand implements Command{

    private OrderService orderService;

    public GetOrdersForAdminCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    private static class Holder{
        static final GetOrdersForAdminCommand INSTANCE = new GetOrdersForAdminCommand(OrderService.getInstance());
    }

    public static GetOrdersForAdminCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = orderService.selectOrderByStatus(Order.Status.GET_FOR_CONFIRMATION);
        request.setAttribute(Parameters.ORDERS, orders);
        return Pages.ADMIN_ORDERS;
    }
}
