package model.service;

import model.dao.UserAuthDao;
import model.dao.UserDao;
import model.dao.impl.UserAuthDaoImpl;
import model.dao.impl.UserDaoImpl;
import model.dao.util.ConnectionManager;
import model.dao.util.DaoFactory;
import model.entity.User;
import model.entity.UserAuth;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private DaoFactory daoFactory;
    private UserService userService;
    private UserAuthService userAuthService;
    private ConnectionManager connectionManager;
    private UserAuthDao userAuthDao;
    private UserDao userDao;

    private void initialization(){
        daoFactory = mock(DaoFactory.class);
        connectionManager = mock(ConnectionManager.class);
        userDao = mock(UserDaoImpl.class);
        userAuthDao = mock(UserAuthDaoImpl.class);

        userAuthService = new UserAuthService(daoFactory);
        userService = new UserService(daoFactory, connectionManager, userAuthService);
    }

    private User buildUser(){
        return new User.Builder()
                .addName("Test")
                .addSurname("Test")
                .addPhone("+3890000")
                .addCount(3.f)
                .addUserAuth(buildUserAuth())
                .createUser();
    }

    private UserAuth buildUserAuth(){
        return new UserAuth.Builder()
                .addId(1)
                .addEmail("email")
                .addPassword("pass")
                .addRole(UserAuth.Role.CLIENT)
                .createUserAuth();
    }

    private void stubDaoFactory(){
        when(daoFactory.getUserDao()).thenReturn(userDao);
    }

    @Test
    public void testUpdateUserCount(){
        User user = buildUser();
        initialization();
        stubDaoFactory();
        userService.updateUserCount(user);

        verify(daoFactory).getUserDao();
        verify(userDao).updateUserCount(user);
    }

    @Test
    public void testUpdateUserPhone(){
        User user = buildUser();
        initialization();
        stubDaoFactory();
        userService.updateUserPhone(user);

        verify(daoFactory).getUserDao();
        verify(userDao).updateUserPhone(user);
    }

    @Test
    public void testUpdate(){
        User user = buildUser();
        initialization();
        stubDaoFactory();
        userService.update(user);

        verify(daoFactory).getUserDao();
        verify(userDao).update(user);
    }

    @Test
    public void testInsert(){
        User user = buildUser();
        initialization();
        stubDaoFactory();
        when(daoFactory.getUserAuthDao()).thenReturn(userAuthDao);
        userService.insert(user);

        verify(daoFactory).getUserDao();
        verify(daoFactory).getUserAuthDao();
        verify(userDao).insert(user);
    }

    @Test
    public void testSelect(){
        User user = buildUser();
        initialization();
        stubDaoFactory();
        when(userDao.select(1)).thenReturn(Optional.of(user));

        User actualUser = userService.select(1).get();
        assertEquals(user, actualUser);

        verify(daoFactory).getUserDao();
        verify(userDao).select(1);
    }

}
