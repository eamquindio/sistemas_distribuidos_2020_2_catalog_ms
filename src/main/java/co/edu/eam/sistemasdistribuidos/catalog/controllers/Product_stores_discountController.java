package co.edu.eam.sistemasdistribuidos.catalog.controllers;


import co.edu.eam.sistemasdistribuidos.catalog.models.product_stores_discounts;
import co.edu.eam.sistemasdistribuidos.catalog.services.product_stores_discountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Product_stores_discount")
public class Product_stores_discountController {

    @Autowired
    private product_stores_discountService product_stores_discountService;


    @GetMapping("/{id}")
    public product_stores_discounts buscar(@PathVariable Integer id){
        return product_stores_discountService.buscar(id);
    }

    @PostMapping
    public void crear(@RequestBody product_stores_discounts p){
        product_stores_discountService.create(p);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody product_stores_discounts p){
        product_stores_discountService.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        product_stores_discountService.delete(id);
    }

    @GetMapping
    public Page<product_stores_discounts> buscartodo(@PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<product_stores_discounts> result = product_stores_discountService.buscartodos(pageable);

        return result;
    }

    @GetMapping("/by-storeid")
    public Page<product_stores_discounts> buscarStoreid(@RequestParam Integer storeid,@PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<product_stores_discounts> result = product_stores_discountService.buscarStoreid(storeid,pageable);

        return result;
    }

    @GetMapping("/by-productid")
    public Page<product_stores_discounts> buscarProductid(@RequestParam Integer productid, @PageableDefault(page = 0, size = 5) Pageable pageable){
        System.out.println("este es el id entrante  "+productid);
        Page<product_stores_discounts> result = product_stores_discountService.buscarProductid(productid, pageable);

        return result;
    }
}
