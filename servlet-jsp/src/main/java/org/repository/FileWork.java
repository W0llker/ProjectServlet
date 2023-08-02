package org.repository;

import org.entity.Client;

import java.util.List;

public interface FileWork {
    void saveClient(Client client);
    void deleteClient(long client);
    Client findClient(long id);
    List<Client> getClientList();
}
