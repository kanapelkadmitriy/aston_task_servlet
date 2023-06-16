package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.model.dto.CategoryDto;
import org.example.model.dto.ProductDto;
import org.example.service.ProductService;
import org.example.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/products/*")
public class OneProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() {
        this.productService = new ProductServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = getIdFromRequest(req);
        final ProductDto productDto = productService.findById(id);
        req.setAttribute("product", productDto);
        req.setAttribute("characteristic", productDto.getCharacteristics());
        req.getRequestDispatcher("/jsp/find_one_product_page.jsp").forward(req, resp);
    }

    private Long getIdFromRequest(HttpServletRequest req) {
        final String[] parameters = req.getPathInfo().split("/");
        return Long.parseLong(parameters[1]);
    }
}
