package controller.command;

import model.entity.Order;
import model.entity.User;
import model.service.OrderService;
import util.CalculateOrderPrice;
import util.constant.Messages;
import util.constant.Page;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOrderCommand implements Command {

    private CalculateOrderPrice calculateOrderPrice;
    private OrderService orderService;

    public UpdateOrderCommand(CalculateOrderPrice calculateOrderPrice, OrderService orderService) {
        this.calculateOrderPrice = calculateOrderPrice;
        this.orderService = orderService;
    }

    private static class Holder {
        static final UpdateOrderCommand INSTANCE = new UpdateOrderCommand(CalculateOrderPrice.getInstance(), OrderService.getInstance());
    }

    public static UpdateOrderCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = createFromRequest(request);
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        if (!updateStrategy(order, user)){
            request.setAttribute(Parameters.ERROR_ORDER_UPDATE, Messages.ERROR_ORDER_UPDATE);
            return Page.ADMIN_ORDERS;
        }
        request.setAttribute(Parameters.SUCCESS_ORDER_UPDATE, Messages.SUCCESS_ORDER_UPDATE);
        return Page.ADMIN_ORDERS;
    }

    private Order createFromRequest(HttpServletRequest request) {
        Order order = (Order) request.getAttribute(Parameters.ORDER);
        order.setStatus(Order.Status.valueOf(request.getParameter(Parameters.STATUS)));
        order.setComment(request.getParameter(Parameters.ADMIN_COMMENT));
        return order;
    }

    private boolean updateStrategy(Order order, User user){
        if (order.getStatus().equals(Order.Status.ACCEPTED)){
            float newUserCount = calculateOrderPrice.calculateUserCountAfterOrderApproving(order, user.getCount());
            user.setCount(newUserCount);
            return orderService.updateWithUserCountChange(order, user);
        } else {
            return orderService.update(order);
        }
    }
}
