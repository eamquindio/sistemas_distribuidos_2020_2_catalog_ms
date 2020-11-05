package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.exceptions.NotFoundException;
import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.DiscountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountsService {

    @Autowired
    private DiscountsRepository discountsRepository;


    @CachePut(value = "discounts", key = "#d.discountId")
    public Discounts create(Discounts d){
        boolean discountsExist = discountsRepository.existsById(d.getDiscountId());
        if(discountsExist) throw new RuntimeException("YA EXISTE EL DISCOUNT");
        discountsRepository.save(d);
        return d;
    }

    @Cacheable(value = "discounts", key = "#id")
    public Discounts find(Integer id){
        Discounts disc = discountsRepository.findById(id).get();
        if(disc == null) throw new NotFoundException("NO SE ENCONTRÓ NINGUNA ELEMENTO CON DICHO ID", "discount_id");

        return disc;
    }

    @CachePut(value = "discounts", key = "#id")
    public Discounts update(Integer id, Discounts d){
        boolean discountsExist = discountsRepository.existsById(id);
        if (!discountsExist) throw  new RuntimeException("NO EXISTE EL DISCOUNT ID");
        d.setDiscountId(id);
        discountsRepository.save(d);
        return d;
    }

    @CacheEvict(value = "discounts", key = "#id")
    public void delete(Integer id){
        Discounts discounts = discountsRepository.findById(id).get();
        if (discounts == null) throw new RuntimeException("NO EXISTE EL DISCOUNT");
        discountsRepository.deleteById(id);

    }

    public List<Discounts> getDiscountByDate(){
        return discountsRepository.getDiscountByDate();
    }

    public List<Discounts> getAllDiscounts(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Discounts> pagedResult = discountsRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Discounts>();
        }
    }

}
