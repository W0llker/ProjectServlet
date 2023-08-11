package multi.basic.config;


import multi.basic.mapping.ProductMapper;
import multi.basic.mapping.UserMapper;
import multi.basic.repository.RepositoryClient;
import multi.basic.repository.RepositoryProduct;
import multi.basic.service.ClientService;

public class ApplicationContext {
    private static ApplicationContext applicationContext;
    private final ClientService clientService;
    private final RepositoryClient repositoryClient;
    private final UserMapper userMapper;
    private final RepositoryProduct repositoryProduct;
    private final ProductMapper productMapper;

    private ApplicationContext() {
        this.clientService = new ClientService();
        this.repositoryClient = new RepositoryClient();
        this.userMapper = new UserMapper();
        this.repositoryProduct = new RepositoryProduct();
        this.productMapper = new ProductMapper();
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
    public RepositoryClient getRepositoryClient() {
        return repositoryClient;
    }
}
