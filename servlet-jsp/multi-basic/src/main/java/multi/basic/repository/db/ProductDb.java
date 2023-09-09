package multi.basic.repository.db;

import multi.api.exception.product.ProductDbException;
import multi.basic.repository.ProductDao;
import multi.basic.repository.driver.DriverDataBases;
import multi.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDb extends DbWork<Product> implements ProductDao {
    private final DriverDataBases driverDataBases;

    public ProductDb(DriverDataBases driverDataBases, EntityMapper<Product> entityMapper) {
        super(entityMapper);
        this.driverDataBases = driverDataBases;
    }

    @Override
    public Product save(Product good) {
        Connection connection = driverDataBases.getConnection();
        good.setId(getId());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storesch.products" +
                    "(id,type_good,name_product,code_product,price) VALUES (?,?,?,?,?)");
            preparedStatement.setLong(1, good.getId());
            preparedStatement.setString(2, String.valueOf(good.getTypeGood()));
            preparedStatement.setString(3, good.getNameProduct());
            preparedStatement.setLong(4, good.getCodeProduct());
            preparedStatement.setDouble(5, good.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProductDbException("Невозможно получить данные о продуктах");
        }
        return good;
    }

    private long getId() {
        Connection connection = driverDataBases.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM storesch.products");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong(1);
            return id + 1;
        } catch (SQLException e) {
            throw new ProductDbException("Невозможно получить данные о продуктах");
        }

    }

    @Override
    public void delete(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM storesch.products p WHERE p.id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new ProductDbException("Невозможно удалить данные");
        }
    }

    @Override
    public Product find(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.products p WHERE p.id=?");
            preparedStatement.setLong(1, id);
            List<Product> products = getEntity(preparedStatement.executeQuery(), new Product());
            return products.size() > 0 ? products.get(0) : null;
        } catch (SQLException e) {
            throw new ProductDbException("Невозможно найти данные");
        }
    }

    @Override
    public List<Product> findProductByIds(List<Long> longs) {
        List<Product> products = new ArrayList<>();
        for (Long id : longs) {
            products.add(find(id));
        }
        return products;
    }

    @Override
    public Product findProductByCode(Long code) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.products p WHERE p.code_product=?");
            preparedStatement.setLong(1, code);
            List<Product> products = getEntity(preparedStatement.executeQuery(), new Product());
            return products.size() > 0 ? products.get(0) : null;
        } catch (SQLException e) {
            throw new ProductDbException("Невозможно найти продукт по коду");
        }
    }

    @Override
    public void update(Product product) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE storesch.products  SET type_good=?,name_product=?,code_product=?,price=? WHERE id=?");
            preparedStatement.setString(1, String.valueOf(product.getTypeGood()));
            preparedStatement.setString(2, product.getNameProduct());
            preparedStatement.setLong(3, product.getCodeProduct());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setLong(5, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProductDbException("Невозможно обновить продукт");
        }
    }

    @Override
    public List<Product> getAllProduct() {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.products");
            return getEntity(preparedStatement.executeQuery(), new Product());
        } catch (SQLException e) {
            throw new ProductDbException("Невозможно получить данные о продуктах");
        }
    }
}
