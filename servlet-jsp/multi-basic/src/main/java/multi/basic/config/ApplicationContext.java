package multi.basic.config;


import multi.basic.mapping.ProductMapper;
import multi.basic.mapping.UserMapper;
import multi.basic.repository.RepositoryClient;
import multi.basic.repository.RepositoryProduct;
import multi.basic.service.ClientService;
import multi.basic.service.ProductService;

public class ApplicationContext {
    private static ApplicationContext applicationContext;
    private final ClientService clientService;
    private final ProductService productServicel;

    private ApplicationContext() {
        clientService = new ClientService(new UserMapper(),new RepositoryClient());
        productServicel = new ProductService(new RepositoryProduct(),new ProductMapper());
    }
    public static synchronized ApplicationContext getInstance() {
        if(applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ProductService getProductServicel() {
        return productServicel;
    }
}
