package model.dao;

import model.entity.Damage;
import model.entity.Order;

import java.util.List;

public interface DamageDao extends GenericDao<Damage> {

   List<Damage> selectOrderDamages (Order order);
}
