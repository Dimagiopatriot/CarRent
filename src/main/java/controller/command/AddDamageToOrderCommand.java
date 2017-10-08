package controller.command;

import model.entity.Damage;
import model.entity.Order;
import model.service.DamageService;
import util.constant.Messages;
import util.constant.Pages;
import util.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDamageToOrderCommand implements Command{

    private DamageService damageService;

    public AddDamageToOrderCommand(DamageService damageService) {
        this.damageService = damageService;
    }

    private static class Holder{
        static final AddDamageToOrderCommand INSTANCE = new AddDamageToOrderCommand(DamageService.getInstance());
    }

    public static AddDamageToOrderCommand getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = (Order) request.getSession().getAttribute(Parameters.ORDER);
        Damage damage = createDamageFromRequest(request);
        damage.setOrder(order);
        if (!damageService.insert(damage)){
            request.setAttribute(Parameters.ERRORS, Messages.DAMAGE_ADD_ERROR);
            return Pages.DAMAGE;
        }
        request.setAttribute(Parameters.SUCCESS, Messages.SUCCESS_DAMAGE_MESSAGE);
        return Pages.DAMAGE;
    }

    private Damage createDamageFromRequest(HttpServletRequest request){
        return new Damage.Builder()
                .addDamageDescription(request.getParameter(Parameters.DAMAGE_DESCRIPTION))
                .addRepairBill(Float.parseFloat(request.getParameter(Parameters.DAMAGE_REPAIR_BILL)))
                .createDamage();
    }
}
