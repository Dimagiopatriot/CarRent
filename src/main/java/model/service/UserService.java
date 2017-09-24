package model.service;

import model.dao.util.DaoFactory;
import model.entity.User;

import java.util.Optional;

public class UserService {

    private DaoFactory daoFactory;

    public UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class Holder{
        static final UserService INSTANCE = new UserService(DaoFactory.getInstance());
    }

    public static UserService getInstance(){
        return Holder.INSTANCE;
    }

    public boolean updateUserPhone(User user){
        return daoFactory.getUserDao().updateUserPhone(user);
    }

    public boolean updateUserCount(User user){
        return daoFactory.getUserDao().updateUserCount(user);
    }

    public boolean update(User user){
        return daoFactory.getUserDao().update(user);
    }

    public boolean insert(User user){
        return daoFactory.getUserDao().insert(user);
    }

    public Optional<User> select(int id){
        return daoFactory.getUserDao().select(id);
    }
}
