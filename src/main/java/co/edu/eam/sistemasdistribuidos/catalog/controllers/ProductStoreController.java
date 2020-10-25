package co.edu.eam.sistemasdistribuidos.catalog.controllers;

import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import co.edu.eam.sistemasdistribuidos.catalog.models.ProductStores;
import co.edu.eam.sistemasdistribuidos.catalog.services.ProductStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product_store")
public class ProductStoreController {

    @Autowired
    private ProductStoreService productStoreService;

    @GetMapping("/{id}")
    public ProductStores find(@PathVariable Integer id){
        return productStoreService.find(id);
    }

    @PostMapping
    public void create(@RequestBody ProductStores ps){
        productStoreService.create(ps);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody ProductStores ps){
        productStoreService.update(id, ps);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        productStoreService.delete(id);
    }

    @GetMapping("/findByStoreId/{id}")
    public List<ProductStores> findByStoreId(@PathVariable Integer id){
        return productStoreService.findByStoreId(id);
    }

    @GetMapping("/page")
    public List<ProductStores> getAllDiscounts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "productId") String sortBy)
    {
        List<ProductStores> list = productStoreService.getAllDiscounts(pageNo, pageSize, sortBy);
        return list;
    }
}
