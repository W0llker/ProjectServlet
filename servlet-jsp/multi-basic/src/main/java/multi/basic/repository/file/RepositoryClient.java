package multi.basic.repository.file;


import multi.basic.repository.ClientDao;
import multi.domain.Client;
import multi.domain.Role;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositoryClient extends FileWork<Client> implements ClientDao {
    public RepositoryClient() {
        path = "D:\\ProjectServletJsp\\servlet-jsp\\multi-basic\\src\\main\\resources\\ListClient";
        list = new ArrayList<>();
    }

    public void save(Client client) {
        list = deserialization();
        try {
            if (deserialization().size() > 0) {
                Optional<Client> optional = deserialization().stream().max(Comparator.comparing(Client::getId));
                client.setId(optional.get().getId() + 1);
                client.setRole(Role.CLIENT);
            } else {
                client.setId(0);
                client.setRole(Role.ADMIN);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        list.add(client);
        serialization(list);
    }

    @Override
    public Client findByLogin(String login) {
        return null;
    }

    public void delete(long id) {
        list = deserialization();
        list.removeIf(client -> client.getId() == id);
        serialization(list);
    }
    public List<Client> getAllClient() {
        return  deserialization();
    }

}
