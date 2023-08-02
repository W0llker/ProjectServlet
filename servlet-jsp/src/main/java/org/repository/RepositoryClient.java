package org.repository;

import org.entity.Client;
import org.entity.Role;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositoryClient implements FileWork{
    private final String path = "D:\\It_AcademySpring\\Project\\src\\main\\resources\\ListClient";
    private List<Client> clientList;

    public RepositoryClient() {
        this.clientList = new ArrayList<>();
    }

    public void saveClient(Client client) {
        clientList = deserialization();
        if (deserialization().size() > 0) {
            Optional<Client> optional = deserialization().stream().max(Comparator.comparing(Client::getId));
            client.setId(optional.get().getId() + 1);
            client.setRole(Role.CLIENT);
        } else {
            client.setId(0);
            client.setRole(Role.ADMIN);
        }
        clientList.add(client);
        serialization(clientList);
    }

    @Override
    public void deleteClient(long id) {
        clientList = getClientList();
        clientList.removeIf(client -> client.getId() == id);
        System.out.println(clientList);
        serialization(clientList);
    }

    @Override
    public Client findClient(long id) {
        clientList = getClientList();
        Optional<Client> optional = clientList.stream().filter(client -> client.getId() == id).findFirst();
        if(optional.isEmpty()) {
            System.out.println("Такого клиента нету");
            return null;
        }
        return optional.get();
    }

    public List<Client> getClientList() {
        return deserialization();
    }

    private List<Client> deserialization() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            clientList = (List<Client>) objectInputStream.readObject();
        } catch (Exception e) {

        }
        return clientList;
    }
    private void serialization(List<Client> clientList) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))) {
            stream.writeObject(clientList);
        } catch (Exception e) {

        }
    }
}
