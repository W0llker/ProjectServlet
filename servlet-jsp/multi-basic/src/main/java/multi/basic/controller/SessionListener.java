package multi.basic.controller;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import multi.api.contract.BasketApi;
import multi.api.dto.order.OrderResponse;
import multi.basic.config.ApplicationContext;

public class SessionListener implements HttpSessionListener{
    private final BasketApi basketService;

    public SessionListener() {
        this.basketService = ApplicationContext.getInstance().getBasketService();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Закрытие сессии");
        OrderResponse orderResponse =(OrderResponse) se.getSession().getAttribute("order");
        basketService.deleteBasketByOrderId(orderResponse.getId());
    }
}
