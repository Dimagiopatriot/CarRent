package controller.filter;

import model.entity.User;
import util.constant.Path;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GuestFilter extends CommonVisitorFilter {

    List<String> notAllowedForGuest;

    @Override
    boolean isNotAllowed(HttpServletRequest req, User user) {
        return user == null && notAllowedForGuest.contains(req.getServletPath());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        notAllowedForGuest = new ArrayList<>();
        notAllowedForGuest.add(Path.LOGOUT);
        notAllowedForGuest.add(Path.USER);
        notAllowedForGuest.add(Path.ADD_DAMAGE);
        notAllowedForGuest.add(Path.CHANGE_STATUS);
        notAllowedForGuest.add(Path.ADMIN_ORDERS);
        notAllowedForGuest.add(Path.ADMIN_ORDERS_SORT);
        notAllowedForGuest.add(Path.CLIENT_ORDERS);
        notAllowedForGuest.add(Path.UPDATE_PERSONAL_INFO);
        notAllowedForGuest.add(Path.MAKE_ORDER);
    }

    @Override
    public void destroy() {

    }
}
