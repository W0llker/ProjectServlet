package multi.basic.repository.db;

import multi.basic.repository.ClientDao;
import multi.basic.repository.driver.DriverDataBases;
import multi.basic.repository.driver.PostgresConnection;
import multi.domain.Client;
import multi.domain.Role;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClientDb implements ClientDao {
    private final EntityMapper<Client> entityMapper;
    private final PostgresConnection driverDataBases;

    public ClientDb(EntityMapper<Client> entityMapper, PostgresConnection driverDataBases) {
        this.entityMapper = entityMapper;
        this.driverDataBases = driverDataBases;
    }

    @Override
    public void save(Client client) {
        Connection connection = driverDataBases.getConnection();
        getIdAndRole(client);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storesch.client" +
                            "(id,name,sur_name,login,password,role) VALUES (?,?,?,?,?,?)");
            preparedStatement.setLong(1,client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurName());
            preparedStatement.setString(4, client.getLogin());
            preparedStatement.setString(5, client.getPassword());
            preparedStatement.setString(6, String.valueOf(client.getRole()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getIdAndRole(Client client) {
        Connection connection = driverDataBases.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM storesch.client");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong(1);
            if(id == null) {
                client.setId(0);
                client.setRole(Role.ADMIN);
            } else {
                client.setId(id + 1);
                client.setRole(Role.CLIENT);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM storesch.client c WHERE c.id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> getAllClient() {
        try {
            List<Client> clients = new ArrayList<>();
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.client");
            ResultSet resultSet = preparedStatement.executeQuery();
            Field[] fields = Client.class.getDeclaredFields();
            while (resultSet.next()) {
                Map<String,String> map = new LinkedHashMap<>();
                for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                    map.put(fields[i].getName(),resultSet.getString(i));
                }
                clients.add(entityMapper.mapperInEntity(map));
            }
            return clients;
        }catch (Exception e) {
            throw new RuntimeException("Не удалось получить данные о клиентах");

        }
    }
}
