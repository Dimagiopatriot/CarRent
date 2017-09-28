package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserCommand implements Command {

    private static class Holder{
        static final RegisterUserCommand INSTANCE = new RegisterUserCommand();
    }

    public static RegisterUserCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
