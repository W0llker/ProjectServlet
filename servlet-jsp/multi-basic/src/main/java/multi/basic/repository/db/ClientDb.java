package multi.basic.repository.db;

import multi.basic.exception.client.ClientDbException;
import multi.basic.repository.ClientDao;
import multi.basic.repository.driver.PostgresConnection;
import multi.domain.Client;
import multi.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClientDb extends DbWork<Client> implements ClientDao {
    private final PostgresConnection driverDataBases;

    public ClientDb(EntityMapper<Client> entityMapper, PostgresConnection driverDataBases) {
        super(entityMapper);
        this.driverDataBases = driverDataBases;
    }

    @Override
    public Client findByLogin(String login) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.client p WHERE p.login=?");
            preparedStatement.setString(1, login);
            List<Client> clients = getEntity(preparedStatement.executeQuery(), new Client());
            return (clients.size() > 0) ? clients.get(0) : null;
        } catch (SQLException e) {
            throw new ClientDbException("Невозможно найти пользователя по логину");
        }
    }

    @Override
    public void save(Client client) {
        Connection connection = driverDataBases.getConnection();
        getIdAndRole(client);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO storesch.client" +
                    "(id,name,sur_name,login,password,role) VALUES (?,?,?,?,?,?)");
            preparedStatement.setLong(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurName());
            preparedStatement.setString(4, client.getLogin());
            preparedStatement.setString(5, client.getPassword());
            preparedStatement.setString(6, String.valueOf(client.getRole()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ClientDbException("Невозможно сохранить пользователя");
        }
    }

    private void getIdAndRole(Client client) {
        Connection connection = driverDataBases.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM storesch.client");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong(1) + 1;
            if (id == 1) {
                client.setId(id);
                client.setRole(Role.ADMIN);
            } else {
                client.setId(id);
                client.setRole(Role.CLIENT);
            }
        } catch (SQLException e) {
            throw new ClientDbException("Невозможно сохранить пользователя");
        }
    }

    @Override
    public void delete(long id) {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM storesch.client c WHERE c.id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new ClientDbException("Невозможно удалить пользователя");
        }
    }

    @Override
    public List<Client> getAllClient() {
        try {
            Connection connection = driverDataBases.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM storesch.client");
            return getEntity(preparedStatement.executeQuery(), new Client());
        } catch (Exception e) {
            throw new ClientDbException("Не удалось получить данные о клиентах");
        }
    }
}
