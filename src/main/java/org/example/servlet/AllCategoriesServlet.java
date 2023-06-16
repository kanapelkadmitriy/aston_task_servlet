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
import java.util.Optional;

@WebServlet("/categories")
public class AllCategoriesServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() {
        this.categoryService = new CategoryServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.findAll());
        req.getRequestDispatcher("/jsp/find_all_categories_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, String> requestBody = new ObjectMapper().readValue(req.getReader(), HashMap.class);
        final String name = Optional.ofNullable(requestBody.get("name"))
                .orElseThrow(() -> new RuntimeException("имя категории не переданно"));
        final CategoryDto savedCategory = categoryService.save(CategoryDto.builder()
                .name(name)
                .build());
        req.setAttribute("category", savedCategory);
        req.getRequestDispatcher("/jsp/save_category_page.jsp").forward(req, resp);

    }
}
