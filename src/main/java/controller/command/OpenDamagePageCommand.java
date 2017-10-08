package controller.command;

import model.entity.Order;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenDamagePageCommand implements Command{

    private static class Holder{
        static final OpenDamagePageCommand INSTANCE = new OpenDamagePageCommand();
    }

    public static OpenDamagePageCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = createFromRequest(request);
        request.getSession().setAttribute(Parameters.ORDER, order);
        return Pages.DAMAGE;
    }

    private Order createFromRequest(HttpServletRequest request) {
        return new Order.Builder()
                .addId(Integer.parseInt(request.getParameter(Parameters.ID)))
                .createOrder();
    }
}
