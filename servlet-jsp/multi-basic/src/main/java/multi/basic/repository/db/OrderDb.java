package multi.basic.repository.db;

import multi.basic.repository.OrderDao;
import multi.basic.repository.driver.DriverDataBases;
import multi.domain.Order;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderDb implements OrderDao {
    private final DriverDataBases driverDataBases;
    private final EntityMapper<Order> entityMapper;

    public OrderDb(DriverDataBases driverDataBases, EntityMapper<Order> entityMapper) {
        this.driverDataBases = driverDataBases;
        this.entityMapper = entityMapper;
    }

    @Override
    public Order save(Order order) {
        try {
            Connection connection = driverDataBases.getConnection();
            order.setId(getId());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storesch.order (id,user_id,cost,status) VALUES (?,?,?,?)");
            preparedStatement.setLong(1,order.getId());
            preparedStatement.setLong(2,order.getUserId());
            preparedStatement.setDouble(3,order.getCost());
            preparedStatement.setString(4,String.valueOf(order.getStatus()));
            preparedStatement.execute();
            return order;
        }catch (Exception e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Order> findOrderByUserId(long userId) {
        try {
            List<Order> orderList = new ArrayList<>();
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.order r WHERE r.user_id=?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Field[] fields = Order.class.getDeclaredFields();
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                    map.put(fields[i].getName(), resultSet.getString(i));
                }
                orderList.add(entityMapper.mapperInEntity(map));
            }
            return orderList;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public Order findById(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.order r WHERE r.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Field[] fields = Order.class.getDeclaredFields();
            Map<String, String> map = new LinkedHashMap<>();
            for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                map.put(fields[i].getName(), resultSet.getString(i));
            }
            return entityMapper.mapperInEntity(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllOrder() {
        try {
            List<Order> orders = new ArrayList<>();
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.order");
            ResultSet resultSet = preparedStatement.executeQuery();
            Field[] fields = Order.class.getDeclaredFields();
            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                    map.put(fields[i].getName(), resultSet.getString(i));
                }
                orders.add(entityMapper.mapperInEntity(map));
            }
            return orders;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить данные о клиентах");
        }
    }
}
