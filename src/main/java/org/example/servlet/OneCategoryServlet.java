package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.model.dto.CategoryDto;
import org.example.service.CategoryService;
import org.example.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/categories/*")
public class OneCategoryServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() {
        this.categoryService = new CategoryServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = getIdFromRequest(req);
        final CategoryDto categoryDto = categoryService.findById(id);
        req.setAttribute("category", categoryDto);
        req.setAttribute("products", categoryDto.getProducts());
        req.getRequestDispatcher("/jsp/find_one_category_page.jsp").forward(req, resp);
    }

    private Long getIdFromRequest(HttpServletRequest req) {
        final String[] parameters = req.getPathInfo().split("/");
        return Long.parseLong(parameters[1]);
    }
}
