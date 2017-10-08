package controller.command;

import util.constant.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    private static class Holder{
        static final LogoutCommand INSTANCE = new LogoutCommand();
    }

    public static LogoutCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return Pages.MAIN;
    }
}
