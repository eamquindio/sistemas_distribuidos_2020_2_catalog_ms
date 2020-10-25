package co.edu.eam.sistemasdistribuidos.catalog.controllers;

import co.edu.eam.sistemasdistribuidos.catalog.models.Product;
import co.edu.eam.sistemasdistribuidos.catalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void create(@RequestBody @Valid Product product){
        productService.create(product);
    }

    @GetMapping("/{product_id}")
    public Product find(@PathVariable("product_id") Integer id){
        return productService.find(id);
    }

    @PutMapping("/{product_id}")
    public void update(@RequestBody @Valid Product product, @PathVariable("product_id") Integer id){
        productService.update(id, product);
    }

    @DeleteMapping("/{product_id}")
    public void delete(@PathVariable("product_id") Integer id){
        productService.delete(id);
    }

    @GetMapping
    public List<Product> getAll(@PageableDefault(size=10, page=0)Pageable pageable){
        return productService.getAll(pageable);
    }

    @GetMapping("/findByName/{product_name}")
    public List<Product> findByName(@PathVariable("product_name") String product_name,
                                    @PageableDefault(size=10, page=0)Pageable pageable){
        return productService.findByName(product_name, pageable);
    }
}
