package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.exceptions.ExecutionResultException;
import co.edu.eam.sistemasdistribuidos.catalog.exceptions.NotFoundException;
import co.edu.eam.sistemasdistribuidos.catalog.models.product_stores_discounts;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.Product_stores_discountRepository;
import co.edu.eam.sistemasdistribuidos.catalog.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class product_stores_discountService {

    @Autowired
    private Product_stores_discountRepository Product_stores_discountRepository;

    @CachePut(value="product_store_discount", key = "#p.id")
    public product_stores_discounts create(product_stores_discounts p) {

        boolean producExist = Product_stores_discountRepository.existsById(p.getId());
        if (producExist) throw  new BusinessException ("ya existe el descuento en el producto", "Product_Discount_Exists");

        //guardo el descuento del producto si no existe
        Product_stores_discountRepository.save(p);
        return p;
    }


    @Cacheable(value = "product_store_discount", key = "#id")
    public product_stores_discounts buscar(Integer id) {

        boolean producExist = Product_stores_discountRepository.existsById(id);
        if (!producExist) throw  new NotFoundException("no existe el descuento en el producto", "Product_Discount_Not_Found");


        //busco los datos y con el get lo obtengo
        return Product_stores_discountRepository.findById(id).get();
    }


    @CachePut(value="product_store_discount", key = "#id", unless="#result == null")
    public product_stores_discounts update(Integer id, product_stores_discounts p) {
        boolean producExist = Product_stores_discountRepository.existsById(id);
        if (!producExist) throw  new NotFoundException("no existe el descuento en el producto", "Product_Discount_Not_Found");
        //guardo el id en el objecto producto y despues lo guardo o actualizo si existe
        p.setId(id);
        Product_stores_discountRepository.save(p);
        return p;
    }


    @CacheEvict(value="product_store_discount", key="#id")
    public void delete(Integer id){

        boolean producExist = Product_stores_discountRepository.existsById(id);
        if (!producExist) throw  new NotFoundException("no existe el descuento en el producto", "Product_Discount_Not_Found");
        Product_stores_discountRepository.deleteById(id);
    }



    public Page<product_stores_discounts> buscartodos(Pageable pageable){
        Page producExist = Product_stores_discountRepository.findAll(pageable);
        if (producExist.isEmpty()) throw new ExecutionResultException("se ejecuto pero no hay resultados", "ExecutionWithoutResults");
        return Product_stores_discountRepository.findAll(pageable);
    }


    //en ves de page va List
    public Page<product_stores_discounts> buscarStoreid(Integer id, Pageable pageable){

        Page<product_stores_discounts> producExist= Product_stores_discountRepository.buscarStore_id(id, pageable);
        if (producExist.isEmpty()) throw  new NotFoundException("no existe el descuento en el producto", "Product_Discount_Not_Found");
        return producExist;
    }



    //en vez de page iba List
    public Page<product_stores_discounts> buscarProductid(Integer id, Pageable pageable){

        Page<product_stores_discounts> producExist = Product_stores_discountRepository.buscarProduct_id(id, pageable);

        System.out.println("este es el id "+id+"y es estado : "+producExist);
        if (producExist.isEmpty()) throw  new NotFoundException("no existe el descuento en el producto", "Product_Discount_Not_Found");
        return producExist;

    }


}