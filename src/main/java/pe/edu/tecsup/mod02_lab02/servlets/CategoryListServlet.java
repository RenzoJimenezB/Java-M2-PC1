package pe.edu.tecsup.mod02_lab02.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.edu.tecsup.mod02_lab02.entities.Category;
import pe.edu.tecsup.mod02_lab02.repositories.CategoryRepository;
import pe.edu.tecsup.mod02_lab02.services.CategoryService;
import pe.edu.tecsup.mod02_lab02.utils.DBConnectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.Normalizer;
import java.util.List;

@WebServlet("/categories")
public class CategoryListServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CategoryListServlet.class);
    private CategoryService categoryService;

    @Override
    public void init() {
        categoryService = new CategoryService(new CategoryRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("CategoryListServlet.doGet");

        try (Connection connection = DBConnectionManager.getConnection()) {
            List<Category> categories = categoryService.getCategories(connection);

            logger.info("\n{}", String.join("\n",
                    categories.stream().map(Category::toString).toList()));

            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/categories.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userInput = req.getParameter("question");
        String normalizedInput = Normalizer
                .normalize(userInput, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        if ("si".equalsIgnoreCase(normalizedInput)) {
            doGet(req, resp);

        } else {
            req.getRequestDispatcher("/WEB-INF/views/bye.jsp").forward(req, resp);
        }
    }
}
