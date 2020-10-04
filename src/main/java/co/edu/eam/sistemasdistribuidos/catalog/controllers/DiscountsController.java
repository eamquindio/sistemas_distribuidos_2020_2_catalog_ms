package co.edu.eam.sistemasdistribuidos.catalog.controllers;

import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import co.edu.eam.sistemasdistribuidos.catalog.services.DiscountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {

    @Autowired
    private DiscountsService discountsService;

    @GetMapping("/{id}")
    public Discounts find(@PathVariable Integer id){
        return discountsService.find(id);
    }

    @PostMapping
    public void create(@RequestBody Discounts d){
        discountsService.create(d);
    }

    @PutMapping
    public void update(@PathVariable Integer id, @RequestBody Discounts d){
        discountsService.update(id, d);
    }

    @DeleteMapping
    public void delete(@PathVariable Integer id){
        discountsService.delete(id);
    }
}
