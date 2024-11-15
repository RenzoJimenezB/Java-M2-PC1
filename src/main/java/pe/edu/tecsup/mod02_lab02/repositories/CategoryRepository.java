package pe.edu.tecsup.mod02_lab02.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.edu.tecsup.mod02_lab02.entities.Category;
import pe.edu.tecsup.mod02_lab02.exceptions.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static final Logger logger = LogManager.getLogger(CategoryRepository.class);

    public List<Category> findAll(Connection connection) {
        logger.info("CategoryRepository.findAll()");

        String sql = """
                SELECT *
                FROM categorias
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Category> categories = new ArrayList<>();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("nombre"));
                category.setDescription(rs.getString("descripcion"));
                category.setOrder(rs.getInt("orden"));
                categories.add(category);
            }
            return categories;

        } catch (SQLException e) {
            logger.error("DB error while fetching categories", e);
            throw new RepositoryException(e.getMessage());
        }
    }
}