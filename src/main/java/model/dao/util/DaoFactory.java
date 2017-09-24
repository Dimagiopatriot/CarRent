package model.dao.util;

import model.dao.DamageDao;
import model.dao.OrderDao;
import model.dao.UserAuthDao;
import model.dao.UserDao;
import model.dao.impl.DamageDaoImpl;
import model.dao.impl.OrderDaoImpl;
import model.dao.impl.UserAuthDaoImpl;
import model.dao.impl.UserDaoImpl;

public class DaoFactory {

    private static class Holder{
        static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance(){
        return Holder.INSTANCE;
    }

    public DamageDao getDamageDao() {
        return DamageDaoImpl.getInstance();
    }

    public OrderDao getOrderDao(){
        return OrderDaoImpl.getInstance();
    }

    public UserAuthDao getUserAuthDao(){
        return UserAuthDaoImpl.getInstance();
    }

    public UserDao getUserDao(){
        return UserDaoImpl.getInstance();
    }
}
