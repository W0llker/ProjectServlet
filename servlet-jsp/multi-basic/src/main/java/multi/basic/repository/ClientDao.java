package multi.basic.repository;

import multi.domain.Client;

import java.util.List;

public interface ClientDao {
    void save(Client client);
    void delete(long id);
    List<Client> getAllClient();
}
