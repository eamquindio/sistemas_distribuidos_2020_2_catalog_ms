package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.exceptions.BusinessException;
import co.edu.eam.sistemasdistribuidos.catalog.exceptions.NotFoundException;
import co.edu.eam.sistemasdistribuidos.catalog.models.Product;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //Cuando se haga el rebase, descomentar para hacer uso del categoryRepository de otro estudiante
    /*@Autowired
    private CategoryRepository categoryRepository;*/

    public void create(Product p){
        boolean product = productRepository.existsById(p.getProductId());
        if(product)
            throw new BusinessException("Ya existe un producto con el ID: "+p.getProductId(), "product_exist");

        //Cuando se haga el rebase, descomentar para hacer uso del categoryRepository de otro estudiante
        /*boolean category = categoryRepository.existsById(p.getCategoryId());
        if(!category)
            throw new NotFoundException("No existe una categoria con el ID: "+p.getCategoryId(), "categoryFK_doesnt_exist");
        */

        productRepository.save(p);
    }

    public Product find(Integer id){
        boolean product = productRepository.existsById(id);
        if(!product)
            throw new NotFoundException("No existe un producto con ID: "+id, "product_doesnt_exist");

        return productRepository.findById(id).get();
    }

    public void update(Integer id, Product p){
        boolean product = productRepository.existsById(id);
        if(!product)
            throw new NotFoundException("No existe un producto con ID: "+id, "product_doesnt_exist");

        //Cuando se haga el rebase, descomentar para hacer uso del categoryRepository de otro estudiante
        /*boolean category = categoryRepository.existsById(p.getCategoryId());
        if(!category)
            throw new BusinessException("No existe una categoria con el ID: "+p.getCategoryId(), "categoryFK_doesnt_exist");
        */

        p.setProductId(id);
        productRepository.save(p);
    }

    public void delete(Integer id){
        boolean product = productRepository.existsById(id);
        if(!product)
            throw new NotFoundException("No existe un producto con ID: "+id, "product_doesnt_exist");

        productRepository.deleteById(id);
    }

    public List<Product> getAll(Pageable pageable){
        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> findByName(String name, Pageable pageable){
        return productRepository.findByName("%"+name+"%", pageable);
    }
}
