package co.edu.eam.sistemasdistribuidos.catalog.controllers;

import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import co.edu.eam.sistemasdistribuidos.catalog.services.DiscountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/by-date")
    public List<Discounts> getDiscountByDate() { return discountsService.getDiscountByDate(); }

    @GetMapping("/page")
    public List<Discounts> getAllDiscounts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "discountId") String sortBy)
    {
        List<Discounts> list = discountsService.getAllDiscounts(pageNo, pageSize, sortBy);

        return list;
    }

}
