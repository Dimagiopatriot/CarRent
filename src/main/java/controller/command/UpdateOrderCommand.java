package controller.command;

import model.entity.Order;
import model.entity.User;
import model.entity.UserAuth;
import model.service.OrderService;
import model.service.UserService;
import util.CalculateOrderPrice;
import util.constant.Messages;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class UpdateOrderCommand implements Command {

    private CalculateOrderPrice calculateOrderPrice;
    private OrderService orderService;
    private UserService userService;

    public UpdateOrderCommand(CalculateOrderPrice calculateOrderPrice, OrderService orderService, UserService userService) {
        this.calculateOrderPrice = calculateOrderPrice;
        this.orderService = orderService;
        this.userService = userService;
    }

    private static class Holder {
        static final UpdateOrderCommand INSTANCE = new UpdateOrderCommand(CalculateOrderPrice.getInstance(),
                OrderService.getInstance(), UserService.getInstance());
    }

    public static UpdateOrderCommand getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = createFromRequest(request);
        Optional<User> user = retrieveUser(order.getUserId());
        if (!user.isPresent()){
            request.setAttribute(Parameters.ERROR_ORDER_UPDATE, Messages.ERROR_ORDER_UPDATE);
            List<Order> orders = orderService.selectOrderByStatus(Order.Status.GET_FOR_CONFIRMATION);
            request.setAttribute(Parameters.ORDERS, orders);
            return Pages.ADMIN_ORDERS;
        }

        if (!updateStrategy(order, user.get())) {
            request.setAttribute(Parameters.ERROR_ORDER_UPDATE, Messages.ERROR_ORDER_UPDATE_NOT_ENOUGH_MONEY);
            List<Order> orders = orderService.selectOrderByStatus(Order.Status.GET_FOR_CONFIRMATION);
            request.setAttribute(Parameters.ORDERS, orders);
            return Pages.ADMIN_ORDERS;
        }
        List<Order> orders = orderService.selectOrderByStatus(Order.Status.GET_FOR_CONFIRMATION);
        request.setAttribute(Parameters.ORDERS, orders);
        return Pages.ADMIN_ORDERS;
    }

    private Optional<User> retrieveUser(int userId) {
        User user;
        Optional<User> userOptional = userService.select(userId);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            UserAuth userAuth = new UserAuth();
            userAuth.setId(userId);
            user.setUserAuth(userAuth);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    private Order createFromRequest(HttpServletRequest request) {
        return new Order.Builder()
                .addId(Integer.parseInt(request.getParameter(Parameters.ID)))
                .addStatus(Order.Status.valueOf(request.getParameter(Parameters.STATUS)))
                .addDateStartRent(Date.valueOf(request.getParameter(Parameters.DATE_FROM)))
                .addDateEndRent(Date.valueOf(request.getParameter(Parameters.DATE_TO)))
                .addCar(Order.Car.valueOf(request.getParameter(Parameters.CAR).toUpperCase()))
                .addComment(request.getParameter(Parameters.ADMIN_COMMENT))
                .addUserId(Integer.parseInt(request.getParameter(Parameters.USER_ID)))
                .createOrder();
    }

    private boolean updateStrategy(Order order, User user) {
        if (order.getStatus().equals(Order.Status.ACCEPTED)) {
            float newUserCount = calculateOrderPrice.calculateUserCountAfterOrderApproving(order, user.getCount());
            if (newUserCount < 0f){
                return false;
            }
            user.setCount(newUserCount);
            return orderService.updateWithUserCountChange(order, user);
        } else {
            return orderService.updateStatusAndComment(order);
        }
    }
}
