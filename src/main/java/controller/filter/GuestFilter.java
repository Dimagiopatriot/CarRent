package controller.filter;

import model.entity.User;
import util.constant.Paths;

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
        notAllowedForGuest.add(Paths.LOGOUT);
        notAllowedForGuest.add(Paths.USER);
        notAllowedForGuest.add(Paths.ADD_DAMAGE);
        notAllowedForGuest.add(Paths.UPDATE_ORDER);
        notAllowedForGuest.add(Paths.ADMIN_ORDERS);
        notAllowedForGuest.add(Paths.CREATE_DAMAGE);
        notAllowedForGuest.add(Paths.CLIENT_ORDERS);
        notAllowedForGuest.add(Paths.UPDATE_PERSONAL_INFO);
        notAllowedForGuest.add(Paths.MAKE_ORDER_PAGE);
        notAllowedForGuest.add(Paths.CREATE_ORDER);
        notAllowedForGuest.add(Paths.SORT_ORDERS);
    }

    @Override
    public void destroy() {

    }
}
