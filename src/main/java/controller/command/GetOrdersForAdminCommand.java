package controller.command;

import controller.Util;
import model.entity.Order;
import model.service.OrderService;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetOrdersForAdminCommand implements Command {

    private OrderService orderService;

    public GetOrdersForAdminCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    private static class Holder {
        static final GetOrdersForAdminCommand INSTANCE = new GetOrdersForAdminCommand(OrderService.getInstance());
    }

    public static GetOrdersForAdminCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int ordersCount = orderService.selectOrdersByStatusCount(Order.Status.GET_FOR_CONFIRMATION);
        int[] pages = Util.pages(ordersCount);
        List<Order> orders = orderService.selectOrderByStatus(Order.Status.GET_FOR_CONFIRMATION, Util.MIN_OFFSET, Util.LIMIT);
        request.getSession().setAttribute(Parameters.PAGES_FOR_ADMIN, pages);
        request.getSession().setAttribute(Parameters.CURRENT_PAGE, 1);
        request.getSession().setAttribute(Parameters.ORDERS, orders);
        request.getSession().setAttribute(Parameters.SORT, Order.Status.GET_FOR_CONFIRMATION);
        return Pages.ADMIN_ORDERS;
    }
}
