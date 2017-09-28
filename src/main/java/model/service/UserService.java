package model.service;

import model.dao.util.ConnectionManager;
import model.dao.util.DaoFactory;
import model.entity.User;
import util.exception.DaoException;

import java.util.Optional;

public class UserService {

    private DaoFactory daoFactory;
    private ConnectionManager connectionManager;

    public UserService(DaoFactory daoFactory, ConnectionManager connectionManager) {
        this.daoFactory = daoFactory;
        this.connectionManager = connectionManager;
    }

    private static class Holder{
        static final UserService INSTANCE = new UserService(DaoFactory.getInstance(), ConnectionManager.getInstance());
    }

    public static UserService getInstance(){
        return Holder.INSTANCE;
    }

    public boolean updateUserPhone(User user){
        boolean isUpdated = false;
        try {
            connectionManager.startTransaction();
            isUpdated = daoFactory.getUserDao().updateUserPhone(user);
            connectionManager.commit();
        } catch (DaoException ex){
            connectionManager.rollback();
        }
        return isUpdated;
    }

    public boolean updateUserCount(User user){
        boolean isUpdated = false;
        try {
            connectionManager.startTransaction();
            isUpdated = daoFactory.getUserDao().updateUserCount(user);
            connectionManager.commit();
        } catch (DaoException ex){
            connectionManager.rollback();
        }
        return isUpdated;
    }

    public boolean update(User user){
        boolean isUpdated = false;
        try {
            connectionManager.startTransaction();
            isUpdated = daoFactory.getUserDao().update(user);
            connectionManager.commit();
        } catch (DaoException ex){
            connectionManager.rollback();
        }
        return isUpdated;
    }

    public boolean insert(User user){

        boolean isCreated = false;
        try{
            connectionManager.startTransaction();
            UserAuthService.getInstance().insert(user.getUserAuth());
            isCreated = daoFactory.getUserDao().insert(user);
            connectionManager.commit();
        } catch (DaoException ex){
            connectionManager.rollback();
        }
        return isCreated;
    }

    public Optional<User> select(int id){
        return daoFactory.getUserDao().select(id);
    }
}
