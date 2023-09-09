package multi.basic.controller.basket;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.BasketApi;
import multi.api.contract.OrderApi;
import multi.api.dto.basket.OrderDto;
import multi.api.dto.order.OrderResponse;
import multi.basic.config.ApplicationContext;
import multi.basic.service.BasketService;
import multi.basic.service.OrderService;

import java.io.IOException;

public class BasketServlet extends HttpServlet {
    private final OrderApi orderService;
    private final BasketApi basketService;

    public BasketServlet() {
        this.orderService = ApplicationContext.getInstance().getOrderService();
        this.basketService = ApplicationContext.getInstance().getBasketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderResponse orderResponse = (OrderResponse) req.getSession(false).getAttribute("order");
        OrderDto orderDto = orderService.getOrderOrderId(orderResponse.getId());
        if(orderDto.getBasket().size() != 0) req.setAttribute("orderDto", orderDto);
        req.getRequestDispatcher("/client/basket.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id_delete") != null) {
            basketService.deleteBasket(Long.parseLong(req.getParameter("id_delete")));
        }else if (req.getParameter("place_an_order") != null & req.getParameter("place_an_order").equals("")) {
            System.out.println("Прошло");
        }
        doGet(req,resp);
    }
}
