package controller.command;

import util.constant.Paths;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private Map<String, Command> commandMap;

    CommandFactory(){
        commandMap = new HashMap<>();
        commandMap.put(Paths.LOGIN, OpenLoginPageCommand.getInstance());
        commandMap.put(Paths.REGISTRATION, OpenRegistrationPageCommand.getInstance());
        commandMap.put(Paths.USER, OpenUserPageCommand.getInstance());
        commandMap.put(Paths.MAKE_ORDER_PAGE, OpenMakeOrderPageCommand.getInstance());
        commandMap.put(Paths.SIGN_UP, RegisterUserCommand.getInstance());
        commandMap.put(Paths.SIGN_IN, LoginUserCommand.getInstance());
        commandMap.put(Paths.LOGOUT, LogoutCommand.getInstance());
        commandMap.put(Paths.CREATE_ORDER, MakeOrderCommand.getInstance());
        commandMap.put(Paths.CLIENT_ORDERS, GetClientOrdersCommand.getInstance());
        commandMap.put(Paths.ADMIN_ORDERS, GetOrdersForAdminCommand.getInstance());
        commandMap.put(Paths.ADD_DAMAGE, OpenDamagePageCommand.getInstance());
        commandMap.put(Paths.CREATE_DAMAGE, AddDamageToOrderCommand.getInstance());
        commandMap.put(Paths.UPDATE_ORDER, UpdateOrderCommand.getInstance());
        commandMap.put(Paths.UPDATE_PERSONAL_INFO, UpdateClientInformationCommand.getInstance());
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
