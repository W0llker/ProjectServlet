package multi.basic.controller.basket;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.BasketApi;
import multi.api.contract.OrderApi;
import multi.api.dto.basket.OrderDto;
import multi.api.dto.order.OrderRequest;
import multi.api.dto.order.OrderResponse;
import multi.basic.config.ApplicationContext;
import multi.domain.StatusOrder;

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
        if (orderDto.getBasket().size() != 0) req.setAttribute("orderDto", orderDto);
        req.getRequestDispatcher("/client/basket.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("id_delete") != null) {
                basketService.deleteBasket(Long.parseLong(req.getParameter("id_delete")));
            } else if (req.getParameter("place_an_order") != null & req.getParameter("place_an_order").equals("")) {
                long idOrder = Long.parseLong(req.getParameter("id_order"));
                long userId = Long.parseLong(req.getParameter("id_user"));
                double cost = Double.parseDouble(req.getParameter("cost"));
                orderService.changeStatus(new OrderRequest(idOrder, userId, cost), StatusOrder.COMPLETED);
                req.getSession(false).setAttribute("order", orderService.createOrder(userId));
                req.getRequestDispatcher("/actionClient").forward(req, resp);
            }
        }catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        doGet(req, resp);
    }
}
