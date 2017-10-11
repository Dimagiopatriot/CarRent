package controller.command;

import controller.Util;
import model.entity.Order;
import model.service.OrderService;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SortOrdersCommand implements Command {

    private OrderService orderService;

    public SortOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    private static class Holder{
        static final SortOrdersCommand INSTANCE = new SortOrdersCommand(OrderService.getInstance());
    }

    public static SortOrdersCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Order.Status status = Order.Status.valueOf(request.getParameter(Parameters.SORT));
        int ordersCount = orderService.selectOrdersByStatusCount(status);
        int[] pages = Util.pages(ordersCount);
        List<Order> orders = orderService.selectOrderByStatus(status, Util.MIN_OFFSET, Util.LIMIT);
        request.getSession().setAttribute(Parameters.PAGES_FOR_ADMIN, pages);
        request.getSession().setAttribute(Parameters.CURRENT_PAGE, 1);
        request.getSession().setAttribute(Parameters.ORDERS, orders);
        request.getSession().setAttribute(Parameters.SORT, status);
        return Pages.ADMIN_ORDERS;
    }
}
