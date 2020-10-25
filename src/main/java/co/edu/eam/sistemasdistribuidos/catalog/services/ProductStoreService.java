package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.exceptions.NotFoundException;
import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import co.edu.eam.sistemasdistribuidos.catalog.models.ProductStores;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.ProductStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductStoreService {

    @Autowired
    private ProductStoreRepository productStoreRepository;

    public void create(ProductStores ps){
        boolean productoStoreExist = productStoreRepository.existsById(ps.getProductId());
        if(productoStoreExist) throw new RuntimeException("YA EXISTE EL PRODUCT STORE");
        productStoreRepository.save(ps);
    }

    public ProductStores find(Integer id){
        ProductStores produStore = productStoreRepository.findById(id).get();
        if (produStore == null) throw new NotFoundException("NO SE ENCONTRÓ NINGUNA ELEMENTO CON DICHO ID", "productor_store_doesnt_exist");
        return produStore;
    }

    public void update(Integer id, ProductStores ps){
        boolean productoStoreExist = productStoreRepository.existsById(id);
        if(!productoStoreExist) throw new RuntimeException("NO EXISTE EL PRODUCT STORE");
        ps.setProductId(id);
        productStoreRepository.save(ps);
    }

    public void delete(Integer id){
        ProductStores productoStore = productStoreRepository.findById(id).get();
        if(productoStore == null) throw new RuntimeException("NO EXISTE EL PRODUCT STORE");
        productStoreRepository.deleteById(id);
    }

    public List<ProductStores> findByStoreId(Integer id){

        return productStoreRepository.findByStoreId(id);
    }

    public List<ProductStores> getAllDiscounts(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<ProductStores> pagedResult = productStoreRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<ProductStores>();
        }
    }

}
