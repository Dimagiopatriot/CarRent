package controller.command;

import util.constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenRegistrationPageCommand implements Command {

    private static class Holder{
        static final OpenRegistrationPageCommand INSTANCE = new OpenRegistrationPageCommand();
    }

    public static OpenRegistrationPageCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.REGISTRATION;
    }
}
