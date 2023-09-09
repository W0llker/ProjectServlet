package multi.basic.repository.db;

import multi.basic.repository.BasketDao;
import multi.basic.repository.driver.DriverDataBases;
import multi.domain.Basket;
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

public class BasketDb implements BasketDao {
    private final DriverDataBases driverDataBases;
    private final EntityMapper<Basket> basketEntityMapper;

    public BasketDb(DriverDataBases driverDataBases, EntityMapper<Basket> basketEntityMapper) {
        this.driverDataBases = driverDataBases;
        this.basketEntityMapper = basketEntityMapper;
    }

    @Override
    public Basket save(Basket basket) {
        try {
            Connection connection = driverDataBases.getConnection();
            basket.setId(getId());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storesch.basket (id,order_id,product_id,count) VALUES (?,?,?,?)");
            preparedStatement.setLong(1, basket.getId());
            preparedStatement.setLong(2, basket.getOrderId());
            preparedStatement.setLong(3, basket.getProductId());
            preparedStatement.setInt(4, basket.getCount());
            preparedStatement.execute();
            return basket;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private long getId() {
        Connection connection = driverDataBases.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM storesch.basket");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong(1);
            return id + 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM storesch.basket b WHERE b.id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Basket> getBasketsByUser(long orderId) {
        try {
            List<Basket> baskets = new ArrayList<>();
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.basket b WHERE b.order_id=?");
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Field[] fields = Basket.class.getDeclaredFields();
            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
              map.put(fields[i].getName(), resultSet.getString(i));
        }
        baskets.add(basketEntityMapper.mapperInEntity(map));
    }
            return baskets;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить данные о клиентах");
        }
    }

    @Override
    public Basket findById(int id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.basket b WHERE b.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Field[] fields = Basket.class.getDeclaredFields();
            Map<String, String> map = new LinkedHashMap<>();
            for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                map.put(fields[i].getName(), resultSet.getString(i));
            }
            return basketEntityMapper.mapperInEntity(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
