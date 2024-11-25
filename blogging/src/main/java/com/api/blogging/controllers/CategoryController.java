package com.api.blogging.controllers;

import com.api.blogging.dto.CategoryDTO;
import com.api.blogging.models.CategoryModel;
import com.api.blogging.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    // Obtener todas las categorías
    @GetMapping("/get_categories")
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Obtener una categoría por su ID
    @GetMapping("/get_categories/{categoryId}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable int categoryId) {
        Optional<CategoryModel> category = categoryService.getCategoryById(categoryId);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva categoría
    @PostMapping("/create")
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryModel createdCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // Actualizar una categoría existente
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable int categoryId, @RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryModel updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una categoría
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
