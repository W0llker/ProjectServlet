package multi.basic.repository.db;

import multi.api.exception.basket.BasketDbException;
import multi.basic.repository.BasketDao;
import multi.basic.repository.driver.DriverDataBases;
import multi.domain.Basket;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BasketDb extends DbWork<Basket> implements BasketDao {
    private final DriverDataBases driverDataBases;

    public BasketDb(DriverDataBases driverDataBases, EntityMapper<Basket> basketEntityMapper) {
        super(basketEntityMapper);
        this.driverDataBases = driverDataBases;

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
            throw new BasketDbException("Невозможно сохранить");
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
            throw new BasketDbException("Не удалось получить макс первичный ключ");
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
            throw new BasketDbException("Невозможно удалить корзину");
        }
    }

    @Override
    public void deleteByOrderId(Long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM storesch.basket b WHERE b.order_id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new BasketDbException("Невозможно удалить корзину");
        }
    }

    @Override
    public List<Basket> getBasketsByUser(long orderId) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.basket b WHERE b.order_id=?");
            preparedStatement.setLong(1, orderId);
            return getEntity(preparedStatement.executeQuery(), new Basket());
        } catch (SQLException e) {
            throw new BasketDbException("Невозможно получить данные");
        }
    }

    @Override
    public Basket findById(int id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.basket b WHERE b.id=?");
            preparedStatement.setLong(1, id);
            List<Basket> baskets = getEntity(preparedStatement.executeQuery(), new Basket());
            return baskets.size() > 0 ? baskets.get(0) : null;
        } catch (Exception e) {
            throw new BasketDbException("Невозможно получить данные");
        }
    }
}
