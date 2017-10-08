package model.service;

import model.dao.DamageDao;
import model.dao.impl.DamageDaoImpl;
import model.dao.util.DaoFactory;
import model.entity.Damage;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DamageServiceTest {

    private DaoFactory daoFactory;
    private DamageService damageService;
    private DamageDao damageDao;

    private void initialization(){
        daoFactory = mock(DaoFactory.class);
        damageDao = mock(DamageDaoImpl.class);
        damageService = new DamageService(daoFactory);
    }

    private void stubDaoFactory(){
        when(daoFactory.getDamageDao()).thenReturn(damageDao);
    }

    private void stubSelect(Damage damage){
        stubDaoFactory();
        when(damageDao.select(1)).thenReturn(Optional.of(damage));
    }

    private Damage buildDamage() {
        return new Damage.Builder()
                .addId(1)
                .addDamageDescription("Description")
                .addRepairBill(24.7f)
                .createDamage();
    }

    @Test
    public void testUpdate(){
        Damage damage = buildDamage();
        initialization();
        stubDaoFactory();
        damageService.update(damage);

        verify(daoFactory).getDamageDao();
        verify(damageDao).update(damage);
    }

    @Test
    public void testInsert(){
        Damage damage = buildDamage();
        initialization();
        stubDaoFactory();
        damageService.insert(damage);

        verify(daoFactory).getDamageDao();
        verify(damageDao).insert(damage);
    }

    @Test
    public void testSelect(){
        Damage damage = buildDamage();
        initialization();
        stubSelect(damage);

        Damage actualDamage = damageService.select(1).get();

        assertEquals(damage, actualDamage);
        verify(daoFactory).getDamageDao();
        verify(damageDao).select(1);
    }

}
