package multi.basic.repository;


import multi.domain.Client;
import multi.domain.Role;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositoryClient {
    private final String path = "D:\\ProjectServletJsp\\servlet-jsp\\multi-basic\\src\\main\\resources\\ListClient";
    private List<Client> list;

    public RepositoryClient() {
        this.list = new ArrayList<>();
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

    public void delete(long id) {
        list = getList();
        list.removeIf(client -> client.getId() == id);
        System.out.println(list);
        serialization(list);
    }

    public Client find(long id) {
        list = getList();
        Optional<Client> optional = list.stream().filter(client -> client.getId() == id).findFirst();
        if (optional.isEmpty()) {
            System.out.println("Такого клиента нету");
            return null;
        }
        return optional.get();
    }

    public List<Client> getList() {
        return deserialization();
    }

    private List<Client> deserialization() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            list = (List<Client>) objectInputStream.readObject();
        } catch (Exception e) {

        }
        return list;
    }

    private void serialization(List<Client> clientList) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))) {
            stream.writeObject(clientList);
        } catch (Exception e) {

        }
    }
}
