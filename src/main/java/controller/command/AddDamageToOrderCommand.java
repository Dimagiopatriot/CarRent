package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDamageToOrderCommand implements Command{

    private static class Holder{
        static final AddDamageToOrderCommand INSTANCE = new AddDamageToOrderCommand();
    }

    public static AddDamageToOrderCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
