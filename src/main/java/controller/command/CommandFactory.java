package controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    private static class Holder{
        static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance(){
        return Holder.INSTANCE;
    }

    public Command getCommand(HttpServletRequest request){
        return null;
    }
}
