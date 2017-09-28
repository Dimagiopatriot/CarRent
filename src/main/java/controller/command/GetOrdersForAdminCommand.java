package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrdersForAdminCommand implements Command{

    private static class Holder{
        static final GetOrdersForAdminCommand INSTANCE = new GetOrdersForAdminCommand();
    }

    public static GetOrdersForAdminCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
