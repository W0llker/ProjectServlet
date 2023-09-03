package multi.basic.repository.db;

import multi.basic.repository.ProductDao;
import multi.basic.repository.driver.DriverDataBases;
import multi.domain.Client;
import multi.domain.Product;
import multi.domain.Role;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductDb implements ProductDao {
    private final DriverDataBases driverDataBases;
    private final EntityMapper<Product> entityMapper;

    public ProductDb(DriverDataBases driverDataBases, EntityMapper<Product> entityMapper) {
        this.driverDataBases = driverDataBases;
        this.entityMapper = entityMapper;
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.products p WHERE p.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Field[] fields = Product.class.getDeclaredFields();
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
    public List<Product> findProductByIds(List<Long> longs) {
        List<Product> products = new ArrayList<>();
        for (Long id : longs) {
            products.add(find(id));
        }
        return products;
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllProduct() {
        try {
            List<Product> products = new ArrayList<>();
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.products");
            ResultSet resultSet = preparedStatement.executeQuery();
            Field[] fields = Product.class.getDeclaredFields();
            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                    map.put(fields[i].getName(), resultSet.getString(i));
                }
                products.add(entityMapper.mapperInEntity(map));
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить данные о клиентах");
        }
    }
}
