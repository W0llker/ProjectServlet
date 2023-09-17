package multi.basic.config;


import multi.api.contract.BasketApi;
import multi.api.contract.ClientApi;
import multi.api.contract.OrderApi;
import multi.api.contract.ProductApi;
import multi.basic.mapping.BasketMapper;
import multi.basic.mapping.OrderMapper;
import multi.basic.mapping.ProductMapper;
import multi.basic.mapping.UserMapper;
import multi.basic.repository.db.*;
import multi.basic.repository.driver.PostgresConnection;
import multi.basic.service.BasketService;
import multi.basic.service.ClientService;
import multi.basic.service.OrderService;
import multi.basic.service.ProductService;
import multi.domain.Basket;
import multi.domain.Client;
import multi.domain.Order;
import multi.domain.Product;

public class ApplicationContext {
    private static ApplicationContext applicationContext;
    private final ClientApi clientService;
    private final ProductApi productService;
    private final OrderApi orderService;
    private final BasketApi basketService;

    private ApplicationContext() {
        clientService = new ClientService(new UserMapper(), new ClientDb(new EntityMapper<>(Client.class), new PostgresConnection()));
        productService = new ProductService(new ProductDb(new PostgresConnection(), new EntityMapper<>(Product.class)), new ProductMapper());
        orderService = new OrderService(new OrderMapper(), new OrderDb(new PostgresConnection(), new EntityMapper<>(Order.class)), new ProductDb(new PostgresConnection(), new EntityMapper<>(Product.class)), new BasketDb(new PostgresConnection(), new EntityMapper<>(Basket.class)), new BasketMapper(), new ProductMapper());
        basketService = new BasketService(new BasketDb(new PostgresConnection(), new EntityMapper<>(Basket.class)), new BasketMapper());
    }

    public static synchronized ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public ClientApi getClientService() {
        return clientService;
    }

    public ProductApi getProductService() {
        return productService;
    }

    public OrderApi getOrderService() {
        return orderService;
    }

    public BasketApi getBasketService() {
        return basketService;
    }
}
