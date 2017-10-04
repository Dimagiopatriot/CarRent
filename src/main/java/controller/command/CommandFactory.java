package controller.command;

import util.constant.Path;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private Map<String, Command> commandMap;

    CommandFactory(){
        commandMap = new HashMap<>();
        commandMap.put(Path.LOGIN, OpenLoginPageCommand.getInstance());
        commandMap.put(Path.REGISTRATION, OpenRegistrationPageCommand.getInstance());
        commandMap.put(Path.USER, OpenUserPageCommand.getInstance());
        commandMap.put(Path.MAKE_ORDER_PAGE, OpenMakeOrderPageCommand.getInstance());
        commandMap.put(Path.SIGN_UP, RegisterUserCommand.getInstance());
        commandMap.put(Path.SIGN_IN, LoginUserCommand.getInstance());
        commandMap.put(Path.LOGOUT, LogoutCommand.getInstance());
        commandMap.put(Path.CREATE_ORDER, MakeOrderCommand.getInstance());
        commandMap.put(Path.CLIENT_ORDERS, GetClientOrdersCommand.getInstance());
        commandMap.put(Path.ADMIN_ORDERS, GetOrdersForAdminCommand.getInstance());
        commandMap.put(Path.ADD_DAMAGE, AddDamageToOrderCommand.getInstance());
        commandMap.put(Path.UPDATE_ORDER, UpdateOrderCommand.getInstance());
    }

    private static class Holder{
        static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance(){
        return Holder.INSTANCE;
    }

    public Command getCommand(HttpServletRequest request){
        String servletPath = request.getServletPath();
        Command command = commandMap.get(servletPath);
        if (command == null){
            command = OpenMainPageCommand.getInstance();
        }
        return command;
    }
}
