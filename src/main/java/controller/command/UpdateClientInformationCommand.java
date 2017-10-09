package controller.command;

import model.entity.User;
import model.service.UserService;
import util.Validator;
import util.constant.Messages;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UpdateClientInformationCommand implements Command {

    private UserService userService;

    public UpdateClientInformationCommand(UserService userService) {
        this.userService = userService;
    }

    private static class Holder{
        final static UpdateClientInformationCommand INSTANCE = new UpdateClientInformationCommand(UserService.getInstance());
    }

    public static UpdateClientInformationCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String phone = request.getParameter(Parameters.PHONE_INPUT);
        String count = request.getParameter(Parameters.COUNT_INPUT);
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        List<String> errors = validateParams(phone, count);

        if (!errors.isEmpty()){
            request.setAttribute(Parameters.ERRORS, errors);
            return Pages.USER;
        }
        if (!updateUser(user, phone, count)){
            errors.add(Messages.ERROR);
            request.setAttribute(Parameters.ERRORS, errors);
            return Pages.USER;
        }

        request.setAttribute(Parameters.SUCCESS, Messages.USER_SUCCESS_UPDATED);
        return Pages.USER;
    }

    private boolean updateUser(User userForUpdating, String phone, String count){
        if (phone.isEmpty() && count.isEmpty()){
            return false;
        }
        float newCount = Float.parseFloat(count);
        if (!phone.equals(userForUpdating.getPhone()) && newCount != userForUpdating.getCount()){
            userForUpdating.setPhone(phone);
            userForUpdating.setCount(newCount);
            return userService.update(userForUpdating);
        }
        if (!phone.equals(userForUpdating.getPhone())){
            userForUpdating.setPhone(phone);
            return userService.updateUserPhone(userForUpdating);
        }
        if (newCount != userForUpdating.getCount()){
            userForUpdating.setCount(newCount);
            return userService.updateUserCount(userForUpdating);
        }
        return false;
    }

    private List<String> validateParams(String phone, String count){
        List<String> errors = new ArrayList<>();
        Validator validator = Validator.getInstance();
        if (phone == null || count == null){
            errors.add(Messages.STUB_ERROR_MESSAGE);
            return errors;
        }
        if (!validator.validatePhone(phone)){
            errors.add(Messages.PHONE_ERROR);
        }
        if (!validator.validateUserCount(count)){
            errors.add(Messages.COUNT_ERROR);
        }
        return errors;
    }
}
