package model.service;

import model.dao.UserAuthDao;
import model.dao.impl.UserAuthDaoImpl;
import model.dao.util.DaoFactory;
import model.entity.UserAuth;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserAuthServiceTest {

    private DaoFactory daoFactory;
    private UserAuthDao userAuthDao;
    private UserAuthService userAuthService;

    private void initialization() {
        daoFactory = mock(DaoFactory.class);
        userAuthDao = mock(UserAuthDaoImpl.class);
        userAuthService = new UserAuthService(daoFactory);
    }

    private void stubDaoFactory() {
        when(daoFactory.getUserAuthDao()).thenReturn(userAuthDao);
    }

    private UserAuth buildUserAuth() {
        return new UserAuth.Builder()
                .addId(1)
                .addEmail("email")
                .addPassword("pass")
                .addRole(UserAuth.Role.CLIENT)
                .createUserAuth();
    }

    @Test
    public void testSelectByEmailPassword() {
        UserAuth userAuth = buildUserAuth();
        initialization();
        stubDaoFactory();
        when(userAuthDao.selectByEmailPassword("email", "pass")).thenReturn(Optional.of(userAuth));

        UserAuth actualUserAuth = userAuthService.selectByEmailPassword("email", "pass").get();
        assertEquals(userAuth, actualUserAuth);

        verify(daoFactory).getUserAuthDao();
        verify(userAuthDao).selectByEmailPassword("email", "pass");
    }

    @Test
    public void testUpdate() {
        UserAuth userAuth = buildUserAuth();
        initialization();
        stubDaoFactory();
        userAuthService.update(userAuth);

        verify(daoFactory).getUserAuthDao();
        verify(userAuthDao).update(userAuth);
    }

    @Test
    public void testInsert(){
        UserAuth userAuth = buildUserAuth();
        initialization();
        stubDaoFactory();
        userAuthService.insert(userAuth);

        verify(daoFactory).getUserAuthDao();
        verify(userAuthDao).insert(userAuth);
    }

    @Test
    public void testSelect(){
        UserAuth userAuth = buildUserAuth();
        initialization();
        stubDaoFactory();
        when(userAuthDao.select(1)).thenReturn(Optional.of(userAuth));

        UserAuth actualUserAuth = userAuthService.select(1).get();
        assertEquals(userAuth, actualUserAuth);

        verify(daoFactory).getUserAuthDao();
        verify(userAuthDao).select(1);
    }
}
