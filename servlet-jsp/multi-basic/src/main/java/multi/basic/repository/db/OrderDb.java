package multi.basic.repository.db;

import multi.api.exception.order.OrderDbException;
import multi.basic.repository.OrderDao;
import multi.basic.repository.driver.DriverDataBases;
import multi.domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDb extends DbWork<Order> implements OrderDao {
    private final DriverDataBases driverDataBases;

    public OrderDb(DriverDataBases driverDataBases, EntityMapper<Order> entityMapper) {
        super(entityMapper);
        this.driverDataBases = driverDataBases;

    }

    @Override
    public Order save(Order order) {
        try {
            Connection connection = driverDataBases.getConnection();
            order.setId(getId());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storesch.order (id,user_id,cost,status) VALUES (?,?,?,?)");
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setDouble(3, order.getCost());
            preparedStatement.setString(4, String.valueOf(order.getStatus()));
            preparedStatement.execute();
            return order;
        } catch (Exception e) {
            throw new OrderDbException("Невозможно сохранить заказ");
        }
    }

    private long getId() {
        Connection connection = driverDataBases.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM storesch.order");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong(1);
            return id + 1;
        } catch (SQLException e) {
            throw new OrderDbException("Невозможно сохранить заказ");
        }
    }

    @Override
    public List<Order> findOrderByUserId(long userId) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.order r WHERE r.user_id=?");
            preparedStatement.setLong(1, userId);
            return getEntity(preparedStatement.executeQuery(), new Order());
        } catch (Exception e) {
            throw new OrderDbException("Невозможно найти заказ по пользователю");
        }
    }

    @Override
    public Order update(Order order) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE storesch.order  SET user_id=?,cost=?,status=? WHERE id=?");
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setDouble(2, order.getCost());
            preparedStatement.setString(3, String.valueOf(order.getStatus()));
            preparedStatement.setLong(4, order.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new OrderDbException("Невозможно обновить заказ");
        }
        return order;
    }

    @Override
    public Order findById(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.order r WHERE r.id=?");
            preparedStatement.setLong(1, id);
            List<Order> orders = getEntity(preparedStatement.executeQuery(), new Order());
            return (orders.size() > 0) ? orders.get(0) : null;
        } catch (Exception e) {
            throw new OrderDbException("Невозможно найти заказ по id");
        }
    }

    @Override
    public List<Order> getAllOrder() {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.order");
            return getEntity(preparedStatement.executeQuery(), new Order());
        } catch (Exception e) {
            throw new OrderDbException("Невозможно получить все заказы");
        }
    }
}
