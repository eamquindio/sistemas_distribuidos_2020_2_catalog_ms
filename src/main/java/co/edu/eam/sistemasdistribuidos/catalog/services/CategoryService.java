package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.exceptions.BusinessException;
import co.edu.eam.sistemasdistribuidos.catalog.exceptions.NotFoundException;
import co.edu.eam.sistemasdistribuidos.catalog.models.Category;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import javax.xml.transform.sax.SAXSource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;



    @CachePut(value = "category", key = "#c.id")
    public Category create(Category c){

        System.out.println("este es el id entran para crear"+c.getId());
        System.out.println("este es el nombre entran para crear"+c.getName());

        boolean categoryExist = categoryRepository.existsById(c.getId());
        if(categoryExist){
            throw new BusinessException("You've already used this id.", "category_already_exists");
        }

        List<Category> categoriesByName = categoryRepository.getCategoryByName(c.getName());

        if(!categoriesByName.isEmpty()){
            throw new BusinessException("You've already used this name.", "name_already_exists");
        }

        System.out.println("este es el id entran para crear "+c.getId());
        System.out.println("este es el nombre entran para crear "+c.getName());
      categoryRepository.save(c);
        return c;

    }

    @Cacheable(value = "category", key = "#id")

    public Category find(Integer id){
       // Category c = categoryRepository.findById(id).get();

        //return categoryRepository.findById(id).get();
        //try {
          //  redisTemplate.opsForValue().set(id.toString(), objectMapper.writeValueAsString(c));
        //} catch (JsonProcessingException e) {
          //  e.printStackTrace();
        //}
        return categoryRepository.findById(id).get();

    }

    @CachePut(value = "category", key = "#id")
    public Category update(Integer id, Category c){

        boolean categoryExist = categoryRepository.existsById(id);
        if(!categoryExist){
            throw new NotFoundException("This category doesn't exists", "not_found_for_update");
        }
        List<Category> categoriesByName = categoryRepository.getCategoryByName(c.getName());

        if(!categoriesByName.isEmpty()){
            throw new BusinessException("You've already used this name.", "name_already_exists");
        }

        c.setId(id);
        categoryRepository.save(c);
        return c;

    }

    @CacheEvict(value = "category", key = "#id")
    public void delete (Integer id){

        boolean categoryExist = categoryRepository.existsById(id);
        if(!categoryExist){
            throw new NotFoundException("This category doesn't exists", "not_found_for_delete");
        }

        categoryRepository.deleteById(id);

    }


    public List<Category> getAll(){

        List<Category> allCategories = categoryRepository.findAll();

        if(allCategories.isEmpty()){
            throw new NotFoundException("There isn't any categories :(", "no_records");
        }

        return allCategories;

    }

    public List<Category> getByName(String name){

        List<Category> categoriesByName =  categoryRepository.getCategoryByName("%"+name+"%");
        if(categoriesByName.isEmpty()){
            throw new NotFoundException("There isn't any categories :(", "no_records");
        }
        return categoriesByName;

    }

    public List<Category> getPageCategory(int page, int size){
        Pageable pages = PageRequest.of(page, size);
        return categoryRepository.findAll(pages).getContent();
    }
}
