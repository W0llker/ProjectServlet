package multi.core;


import multi.core.domain.Client;
import multi.core.domain.Product;
import multi.core.domain.TypeProduct;
import multi.core.repository.RepositoryClient;
import multi.core.repository.RepositoryProduct;

public class Main {
    public static void main(String[] args) {
        RepositoryClient repositoryClient = new RepositoryClient();
        Client vadiam = new Client("Вадим","Пупс","Wolk","1234");
        Client canya = new Client("Саня","Пупс","Zaved","1234");
        Client egor = new Client("Егор","Пупс","Reklow","1234");

//        repositoryClient.save(vadiam);
//        repositoryClient.save(canya);
//        repositoryClient.save(egor);
//        System.out.println(repositoryClient.find(1));
//        System.out.println(repositoryClient.getList());


        Product product = new Product(TypeProduct.BUILDING,"цемент",123456,2.43);
        Product product1 = new Product(TypeProduct.BUILDING,"доски",1236436,2);
        Product product2 = new Product(TypeProduct.MEAL,"мясо",12152,5);

        RepositoryProduct repositoryProduct = new RepositoryProduct();
//        repositoryProduct.save(product);
//        repositoryProduct.save(product1);
//        repositoryProduct.save(product2);
        repositoryProduct.delete(2);
        System.out.println(repositoryProduct.getList());

    }
}