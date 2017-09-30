package model.dao;

import model.entity.Damage;
import model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface DamageDao extends GenericDao<Damage> {

   Optional<Damage> selectOrderDamages (Order order);
}
