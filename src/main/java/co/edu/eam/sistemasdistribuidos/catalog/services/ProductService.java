package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.exceptions.BusinessException;
import co.edu.eam.sistemasdistribuidos.catalog.exceptions.NotFoundException;
import co.edu.eam.sistemasdistribuidos.catalog.models.Product;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    //Cuando se haga el rebase, descomentar para hacer uso del categoryRepository de otro estudiante
    /*@Autowired
    private CategoryRepository categoryRepository;*/

    @CachePut(value="product", key = "#p.productId")
    public Product create(Product p){
        boolean product = productRepository.existsById(p.getProductId());
        if(product)
            throw new BusinessException("Ya existe un producto con el ID: "+p.getProductId(), "product_exist");
        productRepository.save(p);
        return p;
    }

    @Cacheable(value = "product", key="#id")
    public Product find(Integer id){
        boolean product = productRepository.existsById(id);
        if(!product)
            throw new NotFoundException("No existe un producto con ID: "+id, "product_doesnt_exist");
        return productRepository.findById(id).get();
    }

    @CachePut(value="product", key="#id")
    public Product update(Integer id, Product p){
        boolean product = productRepository.existsById(id);
        //redisTemplate.opsForValue().set(id.toString(), null);
        if(!product) throw new NotFoundException("No existe un producto con ID: "+id, "product_doesnt_exist");
        p.setProductId(id);
        productRepository.save(p);
        return p;
    }

    @CacheEvict(value="product", key="#id")
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
