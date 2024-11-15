package pe.edu.tecsup.mod02_lab02.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.edu.tecsup.mod02_lab02.entities.Category;
import pe.edu.tecsup.mod02_lab02.exceptions.RepositoryException;
import pe.edu.tecsup.mod02_lab02.repositories.CategoryRepository;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories(Connection connection) {
        logger.info("CategoryService.getCategories()");

        try {
            return categoryRepository.findAll(connection);
            
        } catch (RepositoryException e) {
            logger.error("Repository error: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
