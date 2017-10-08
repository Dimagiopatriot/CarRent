package controller.filter;

import model.entity.User;
import model.entity.UserAuth;
import util.constant.Paths;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ClientFilter extends CommonVisitorFilter {

    private List<String> notAllowedForClient;

    @Override
    boolean isNotAllowed(HttpServletRequest req, User user) {
        return user != null && user.getUserAuth().getRole().equals(UserAuth.Role.CLIENT) && notAllowedForClient.contains(req.getServletPath());

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notAllowedForClient = new ArrayList<>();
        notAllowedForClient.add(Paths.LOGIN);
        notAllowedForClient.add(Paths.REGISTRATION);
        notAllowedForClient.add(Paths.ADD_DAMAGE);
        notAllowedForClient.add(Paths.UPDATE_ORDER);
        notAllowedForClient.add(Paths.ADMIN_ORDERS);
        notAllowedForClient.add(Paths.CREATE_DAMAGE);
    }

    @Override
    public void destroy() {

    }
}
