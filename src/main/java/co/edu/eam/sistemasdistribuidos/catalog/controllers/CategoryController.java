package co.edu.eam.sistemasdistribuidos.catalog.controllers;

import co.edu.eam.sistemasdistribuidos.catalog.models.Category;
import co.edu.eam.sistemasdistribuidos.catalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public Category find(@PathVariable Integer id){
        return categoryService.find(id);
    }

    @PostMapping
    public void create(@RequestBody @Valid Category category){
        categoryService.create(category);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid Category c){
        categoryService.update(id,c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable  Integer id) {
        categoryService.delete(id);
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/by-name")
    public List<Category> findByName(@RequestParam (defaultValue = "a") String name){
        return categoryService.getByName(name);
    }

    @GetMapping("/page")
    public List<Category> getPageCategory(@RequestParam (defaultValue = "1") int page, @RequestParam (defaultValue = "4") int size){ return categoryService.getPageCategory(page, size);}
}
