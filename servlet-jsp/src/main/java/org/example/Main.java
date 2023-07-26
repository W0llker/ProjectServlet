package org.example;

import org.entity.Client;
import org.repository.RepositoryClient;

public class Main {
    public static void main(String[] args) {
        RepositoryClient repositoryClient = new RepositoryClient();
        Client vadiam = new Client("Вадим","Пупс","Wolk","1234");
        Client canya = new Client("Саня","Пупс","Zaved","1234");
        Client egor = new Client("Егор","Пупс","Reklow","1234");

//        repositoryClient.saveClient(vadiam);
//        repositoryClient.saveClient(canya);
//        repositoryClient.saveClient(egor);
        repositoryClient.findClient(5);
        System.out.println(repositoryClient.getClientList());
    }
}