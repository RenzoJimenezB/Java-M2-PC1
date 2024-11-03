package pe.edu.tecsup.mod02_lab02;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListCategoriesServlet", value = "/list-categories-servlet")
public class ListCategoriesServlet extends HttpServlet {

    private static final String QUERY_SELECT_CATEGORIES = "SELECT * FROM categorias";

    private Connection connection;

    @Override
    public void init() throws ServletException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen1?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "tchai1712");
            System.out.println("Database connection established");

        } catch (SQLException e) {
            System.err.println("SQL error during database connection: " + e.getMessage());
            throw new ServletException("Database connection initialisation failed", e);

        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found", e);
        }
    }

    private List<String> getCategories() throws SQLException {
        List<String> categories = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_CATEGORIES); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categories.add(rs.getString("nombre"));
            }
        }
        return categories;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (connection == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database connection not available");
            return;
        }

        try {
            List<String> categories = getCategories();

            if (categories.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }

            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>");
            writer.println("<h1>Lista de categorias</h1>");
            writer.println("<ul>");

            for (String category : categories) {
                writer.println("<li>" + category + "</li>");
            }

            writer.println("</ul>");
            writer.println("</body></html>");


        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while accessing categories");
            System.err.println("SQLException: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userInput = req.getParameter("question");
        String normalizedInput = Normalizer.normalize(userInput, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {

            if ("si".equalsIgnoreCase(normalizedInput)) {
                doGet(req, resp);

            } else {
                writer.println("<html><body>");
                writer.println("<p>Bye</p>");
                writer.println("</body></html>");
            }
        }
    }

    @Override
    public void destroy() {

        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");

            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }

        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("AbandonedConnectionCleanupThread stopped");
        } catch (Exception e) {
            System.out.println("Error stopping AbandonedConnectionCleanupThread: " + e.getMessage());
        }
    }
}
