package model.dao.impl;

import model.dao.DamageDao;
import model.dao.util.ConnectionManager;
import model.dao.util.JdbcConnection;
import model.entity.Damage;
import model.entity.Order;
import util.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DamageDaoImpl implements DamageDao {

    private ConnectionManager connectionManager;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_DAMAGE_DESCRIPTION = "damage_description";
    private static final String COLUMN_REPAIR_BILL = "repair_bill";

    private static final String INSERT_QUERY = "INSERT INTO car_rent.damage(order_id, damage_description, repair_bill) values(?, ?, ?);";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM car_rent.damage WHERE id = ?;";
    private static final String SELECT_QUERY_BY_ORDER_ID = "SELECT * FROM car_rent.damage WHERE order_id = ?;";
    private static final String UPDATE_QUERY = "UPDATE car_rent.damage SET order_id=?, damage_description=?, repair_bill=?" +
            "WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM car_rent.damage WHERE id = ?;";

    public DamageDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static class Holder {
        static final DamageDaoImpl INSTANCE = new DamageDaoImpl(ConnectionManager.getInstance());
    }

    public static DamageDaoImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Damage> selectOrderDamages(Order order) {
        List<Damage> damages = new ArrayList<>();
        try(JdbcConnection connection = connectionManager.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(SELECT_QUERY_BY_ORDER_ID)) {
            statement.setInt(1, order.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    Damage damage = buildDamage(resultSet);
                    damage.setOrder(order);
                    damages.add(damage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return damages;
    }

    @Override
    public boolean update(Damage damage) throws DaoException {

        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(UPDATE_QUERY)){

            statement.setInt(1, damage.getOrder().getId());
            statement.setString(2, damage.getDamageDescription());
            statement.setFloat(3, damage.getRepairBill());
            statement.setInt(4, damage.getId());
            updatedRow = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public boolean insert(Damage damage) throws DaoException {
        int updatedRow = 0;
        try (JdbcConnection connection = connectionManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){

            statement.setInt(1, damage.getOrder().getId());
            statement.setString(2, damage.getDamageDescription());
            statement.setFloat(3, damage.getRepairBill());
            updatedRow = statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    damage.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return updatedRow > 0;
    }

    @Override
    public Optional<Damage> select(int id) throws DaoException {
        Optional<Damage> damage = Optional.empty();
        try(JdbcConnection connection = connectionManager.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            damage = Optional.of(buildDamage(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }

        return damage;
    }

    private Damage buildDamage(ResultSet resultSet) throws SQLException {
        return new Damage.Builder()
                .addId(resultSet.getInt(COLUMN_ID))
                .addDamageDescription(resultSet.getString(COLUMN_DAMAGE_DESCRIPTION))
                .addRepairBill(resultSet.getFloat(COLUMN_REPAIR_BILL))
                .createDamage();
    }
}
