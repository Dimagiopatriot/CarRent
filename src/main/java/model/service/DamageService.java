package model.service;

import model.dao.util.DaoFactory;
import model.entity.Damage;
import model.entity.Order;

import java.util.List;
import java.util.Optional;

public class DamageService {

    private DaoFactory daoFactory;

    public DamageService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder{
        static final DamageService INSTANCE = new DamageService(DaoFactory.getInstance());
    }

    public static DamageService getInstance(){
        return Holder.INSTANCE;
    }

    public Optional<Damage> selectOrderDamages(Order order){
        return daoFactory.getDamageDao().selectOrderDamages(order);
    }

    public boolean update(Damage damage){
        return daoFactory.getDamageDao().update(damage);
    }

    public boolean insert(Damage damage){
        return daoFactory.getDamageDao().insert(damage);
    }

    public Optional<Damage> select(int id){
        return daoFactory.getDamageDao().select(id);
    }
}
