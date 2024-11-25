package com.api.blogging.services;

import com.api.blogging.dto.CategoryDTO;
import com.api.blogging.models.CategoryModel;
import com.api.blogging.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Obtener todas las categorías
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Obtener una categoría por su ID
    public Optional<CategoryModel> getCategoryById(int categoryId) {
        return categoryRepository.findById((long) categoryId);
    }

    // Crear una nueva categoría
    public CategoryModel createCategory(CategoryDTO categoryDTO) {
        CategoryModel category = new CategoryModel();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);
    }

    // Actualizar una categoría existente
    public CategoryModel updateCategory(int categoryId, CategoryDTO categoryDTO) {
        CategoryModel category = categoryRepository.findById((long) categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);
    }

    // Eliminar una categoría
    public void deleteCategory(int categoryId) {
        CategoryModel category = categoryRepository.findById((long) categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        categoryRepository.delete(category);
    }
}
