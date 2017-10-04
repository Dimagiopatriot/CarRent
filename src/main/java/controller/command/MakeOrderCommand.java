package controller.command;

import model.entity.Order;
import model.entity.User;
import model.service.OrderService;
import util.Validator;
import util.constant.Messages;
import util.constant.Page;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MakeOrderCommand implements Command {

    private OrderService orderService;

    public MakeOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    private static class Holder{
        static final MakeOrderCommand INSTANCE = new MakeOrderCommand(OrderService.getInstance());
    }

    public static MakeOrderCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = createOrder(request);
        List<String> errors = validateDates(order);
        errors.addAll(validateUserCount(request));
        if (!errors.isEmpty()){
            setAttributes(request, order, errors);
            return Page.MAKE_ORDER;
        }
        if (!orderService.insert(order)){
            errors.add(Messages.ORDER_ERROR);
            setAttributes(request, order, errors);
            return Page.MAKE_ORDER;
        }
        request.setAttribute(Parameters.SUCCESS, Messages.SUCCESS_ORDER_MESSAGE);
        return Page.MAKE_ORDER;
    }

    private void setAttributes(HttpServletRequest request, Order order, List<String> errors){
        request.setAttribute(Parameters.CAR, order.getCar());
        request.setAttribute(Parameters.DATE_FROM, order.getDateStartRent());
        request.setAttribute(Parameters.DATE_TO, order.getDateEndRent());
        request.setAttribute(Parameters.ERRORS, errors);
    }

    private List<String> validateUserCount(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        float price = parsePriceParameter(request);
        List<String> errors = new ArrayList<>();
        if (user.getCount() < price){
            errors.add(Messages.NOT_ENOUGH_MONEY_ERROR);
        }
        return errors;
    }

    private float parsePriceParameter(HttpServletRequest request){
        String price = request.getParameter(Parameters.PRICE);
        if (price.isEmpty()){
            return 0;
        }
        return Float.parseFloat(price);
    }

    private List<String> validateDates(Order order){
        List<String> errors = new ArrayList<>();
        Validator validator = Validator.getInstance();
        if (!validator.validateDate(dateCast(order.getDateStartRent()), dateCast(order.getDateEndRent()))){
            errors.add(Messages.WRONG_DATE_ERROR);
        }
        return errors;
    }

    private LocalDate dateCast(Date date){
        return date.toLocalDate();
    }

    private Order createOrder(HttpServletRequest request){
        return new Order.Builder()
                .addCar(Order.Car.valueOf(request.getParameter(Parameters.CAR)))
                .addDateStartRent(Date.valueOf(request.getParameter(Parameters.DATE_FROM)))
                .addDateEndRent(Date.valueOf(request.getParameter(Parameters.DATE_TO)))
                .addStatus(Order.Status.GET_FOR_CONFIRMATION)
                .addUserId(((User) request.getSession().getAttribute(Parameters.USER)).getUserAuth().getId())
                .createOrder();
    }

}
