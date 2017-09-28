package controller.command;

import util.constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenUserPageCommand implements Command{

    private static class Holder{
        static final OpenUserPageCommand INSTANCE = new OpenUserPageCommand();
    }

    public static OpenUserPageCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.USER;
    }
}
