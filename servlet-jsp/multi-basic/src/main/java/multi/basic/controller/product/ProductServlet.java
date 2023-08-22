package multi.basic.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import multi.api.dto.ProductRequest;
import multi.basic.config.ApplicationContext;
import multi.basic.service.ProductService;
import multi.domain.TypeProduct;

import java.io.IOException;

public class ProductServlet extends HttpServlet {
    ProductService productService;

    public ProductServlet() {
        productService = ApplicationContext.getInstance().getProductServicel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("addProduct") != null) {
            var type = req.getParameter("type");
            var name = req.getParameter("name");
            var code = req.getParameter("code");
            var price = req.getParameter("price");
            if (name.equals("") || code.equals("") || price.equals("")) {
                req.setAttribute("error","Для добавление нужно ввести все параметры");
                req.getRequestDispatcher("authentication/admin.jsp").forward(req,resp);
            } else {
                productService.createProduct(new ProductRequest(TypeProduct.valueOf(type)
                        ,name,Long.parseLong(code),Double.parseDouble(price)));
                req.getRequestDispatcher("authentication/admin.jsp").forward(req,resp);
            }
        } else if (req.getParameter("showProduct") != null) {
            req.setAttribute("products", productService.showProduct());
            req.getRequestDispatcher("authentication/admin.jsp").forward(req,resp);
        } else if (req.getParameter("editProduct") != null) {
            var id = req.getParameter("edit_id");
            var type = req.getParameter("edit_type");
            var name = req.getParameter("edit_name");
            var code = req.getParameter("edit_code");
            var price = req.getParameter("edit_price");
            if (name.equals("") || code.equals("") || price.equals("")) {
                req.setAttribute("error", "Для добавление нужно ввести все параметры");
                req.getRequestDispatcher("authentication/admin.jsp").forward(req, resp);
            }else {
                productService.updateProduct(Integer.parseInt(id), new ProductRequest(TypeProduct.valueOf(type)
                        , name, Long.parseLong(code), Double.parseDouble(price)));
                req.getRequestDispatcher("authentication/admin.jsp").forward(req, resp);
            }
        } else if (req.getParameter("deleteProduct") != null) {
            var id = req.getParameter("delete_id");
            productService.deleteProduct(Integer.parseInt(id));
            req.getRequestDispatcher("authentication/admin.jsp").forward(req, resp);
        }
    }
}
