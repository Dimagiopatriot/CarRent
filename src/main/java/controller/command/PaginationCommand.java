package controller.command;

import controller.Util;
import model.entity.Order;
import model.service.OrderService;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PaginationCommand implements Command {


    private OrderService orderService;

    public PaginationCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    private static class Holder {
        static final PaginationCommand INSTANCE = new PaginationCommand(OrderService.getInstance());
    }

    public static PaginationCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order.Status status = (Order.Status) request.getSession().getAttribute(Parameters.SORT);
        int ordersCount = orderService.selectOrdersByStatusCount(status);
        int[] pages = Util.pages(ordersCount);
        String pageYouWant = request.getParameter(Parameters.PAGE_TRANSITION);

        int offset = getOffset(pageYouWant);
        List<Order> orders = orderService.selectOrderByStatus(status, offset, Util.LIMIT);
        request.getSession().setAttribute(Parameters.PAGES_FOR_ADMIN, pages);
        request.getSession().setAttribute(Parameters.CURRENT_PAGE, pageYouWant);
        request.getSession().setAttribute(Parameters.ORDERS, orders);
        request.getSession().setAttribute(Parameters.SORT, status);
        return Pages.ADMIN_ORDERS;
    }

    private int getOffset(String pageYouWant){
        int offset = 0;
        if (!pageYouWant.isEmpty()){
            offset = (Integer.valueOf(pageYouWant) * Util.LIMIT) - Util.LIMIT;
            return offset;
        }
        return offset;
    }
}
