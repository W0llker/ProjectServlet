package multi.basic.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.contract.ProductApi;
import multi.api.dto.product.ProductRequest;
import multi.basic.config.ApplicationContext;
import multi.domain.TypeProduct;

import java.io.IOException;

public class ProductAdminServlet extends HttpServlet {
    private final ProductApi productService;

    public ProductAdminServlet() {
        productService = ApplicationContext.getInstance().getProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productService.showProduct());
        req.getRequestDispatcher("authentication/admin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("addProduct") != null) {
                var type = req.getParameter("type");
                var name = req.getParameter("name");
                var code = req.getParameter("code");
                var price = req.getParameter("price");
                productService.createProduct(new ProductRequest(TypeProduct.valueOf(type)
                        , name, Long.parseLong(code), Double.parseDouble(price)));
            } else if (req.getParameter("editProduct") != null) {
                var id = req.getParameter("edit_id");
                var type = req.getParameter("edit_type");
                var name = req.getParameter("edit_name");
                var code = req.getParameter("edit_code");
                var price = req.getParameter("edit_price");
                productService.updateProduct(Integer.parseInt(id), new ProductRequest(TypeProduct.valueOf(type)
                        , name, Long.parseLong(code), Double.parseDouble(price)));

            } else if (req.getParameter("deleteProduct") != null) {
                var id = req.getParameter("delete_id");
                productService.deleteProduct(Long.parseLong(id));
            } else if (req.getParameter("logout") != null) {
                req.getSession().invalidate();
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        doGet(req, resp);
    }
}
