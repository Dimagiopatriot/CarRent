package controller.command;

import util.constant.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenMakeOrderPageCommand implements Command {

    private static class Holder{
        static final OpenMakeOrderPageCommand INSTANCE = new OpenMakeOrderPageCommand();
    }

    public static OpenMakeOrderPageCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.MAKE_ORDER;
    }
}
