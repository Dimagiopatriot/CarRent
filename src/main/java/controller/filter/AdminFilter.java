package controller.filter;

import model.entity.User;
import model.entity.UserAuth;
import util.constant.Path;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AdminFilter extends CommonVisitorFilter {

    private List<String> notAllowedForAdmin;

    @Override
    boolean isNotAllowed(HttpServletRequest req, User user) {
        return user != null && user.getUserAuth().getRole().equals(UserAuth.Role.ADMIN) && notAllowedForAdmin.contains(req.getServletPath());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        notAllowedForAdmin = new ArrayList<>();
        notAllowedForAdmin.add(Path.LOGIN);
        notAllowedForAdmin.add(Path.REGISTRATION);
        notAllowedForAdmin.add(Path.CLIENT_ORDERS);
        notAllowedForAdmin.add(Path.UPDATE_PERSONAL_INFO);
        notAllowedForAdmin.add(Path.MAKE_ORDER);
    }

    @Override
    public void destroy() {

    }
}
