package multi.basic.controller.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.BasketApi;
import multi.api.contract.OrderApi;
import multi.api.contract.ProductApi;
import multi.api.dto.order.OrderResponse;
import multi.api.dto.user.UserResponse;
import multi.basic.config.ApplicationContext;
import multi.basic.service.BasketService;
import multi.basic.service.OrderService;
import multi.basic.service.ProductService;
import multi.domain.Client;

import java.io.IOException;
import java.math.BigInteger;

public class OrderServlet extends HttpServlet{
    public final ProductApi productService;
    public final BasketApi basketService;
    public final OrderApi orderService;

    public OrderServlet() {
        this.productService = ApplicationContext.getInstance().getProductServicel();
        this.basketService = ApplicationContext.getInstance().getBasketService();
        this.orderService = ApplicationContext.getInstance().getOrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products",productService.showProduct());
        req.getRequestDispatcher("authentication/client.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserResponse user = (UserResponse) req.getSession(false).getAttribute("userAuth");
        OrderResponse orderResponse = orderService.createOrder(user.getId());
        req.getSession(false).setAttribute("order",orderResponse);
        if(req.getParameter("id") != null) {
            basketService.createBasket(orderResponse.getId(),Long.parseLong(req.getParameter("id")),Integer.parseInt(req.getParameter("count")));
        }else if(req.getParameter("basket") != null) {
            req.setAttribute("orderDto",orderService.getOrderOrderId(orderResponse.getId()));
            req.getRequestDispatcher("/client/basket.jsp").forward(req,resp);
        }
        doGet(req,resp);
    }
}
