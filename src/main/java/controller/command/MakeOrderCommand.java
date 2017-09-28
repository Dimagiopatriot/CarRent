package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeOrderCommand implements Command {

    private static class Holder{
        static final MakeOrderCommand INSTANCE = new MakeOrderCommand();
    }

    public static MakeOrderCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
