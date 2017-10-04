package controller.command;

import model.entity.Order;
import model.entity.User;
import model.service.OrderService;
import util.constant.Page;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetClientOrdersCommand implements Command{

    private OrderService orderService;

    public GetClientOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    private static class Holder{
        static final GetClientOrdersCommand INSTANCE = new GetClientOrdersCommand(OrderService.getInstance());
    }

    public static GetClientOrdersCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = ((User)request.getSession().getAttribute(Parameters.USER)).getUserAuth().getId();
        List<Order> orders = orderService.selectOrderByUserId(userId);
        request.setAttribute(Parameters.ORDERS, orders);
        return Page.CLIENT_ORDERS;
    }
}
