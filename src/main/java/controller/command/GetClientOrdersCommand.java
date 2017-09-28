package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetClientOrdersCommand implements Command{

    private static class Holder{
        static final GetClientOrdersCommand INSTANCE = new GetClientOrdersCommand();
    }

    public static GetClientOrdersCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
