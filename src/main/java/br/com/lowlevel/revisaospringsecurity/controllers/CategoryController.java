package br.com.lowlevel.revisaospringsecurity.controllers;

import br.com.lowlevel.revisaospringsecurity.domain.product.Category;
import br.com.lowlevel.revisaospringsecurity.domain.product.CategoryRequestDTO;
import br.com.lowlevel.revisaospringsecurity.domain.product.CategoryResponseDTO;
import br.com.lowlevel.revisaospringsecurity.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid CategoryRequestDTO body){
        Category newCategory = new Category(body);

        this.repository.save(newCategory);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<CategoryResponseDTO> categoryList = this.repository.findAll().stream().map(CategoryResponseDTO::new).toList();

        return ResponseEntity.ok(categoryList);
    }
}