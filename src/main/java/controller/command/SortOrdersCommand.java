package controller.command;

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
        List<Order> orders = orderService.selectOrderByStatus(status);
        request.setAttribute(Parameters.ORDERS, orders);
        request.setAttribute(Parameters.SORT, status);
        return Pages.ADMIN_ORDERS;
    }
}
