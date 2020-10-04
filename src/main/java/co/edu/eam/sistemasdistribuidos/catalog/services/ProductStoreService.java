package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.models.ProductStores;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.ProductStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStoreService {

    @Autowired
    private ProductStoreRepository productStoreRepository;

    public void create(ProductStores ps){
        boolean productoStoreExist = productStoreRepository.existsById(ps.getProductId());
        if(productoStoreExist) throw new RuntimeException("YA EXISTE EL PRODUC STORE");
        productStoreRepository.save(ps);
    }

    public ProductStores find(Integer id){
        return productStoreRepository.findById(id).get();
    }

    public void update(Integer id, ProductStores ps){
        boolean productoStoreExist = productStoreRepository.existsById(id);
        if(!productoStoreExist) throw new RuntimeException("NO EXISTE EL PRODUC STORE");
        ps.setProductId(id);
        productStoreRepository.save(ps);
    }

    public void delete(Integer id){
        ProductStores productoStore = productStoreRepository.findById(id).get();
        if(productoStore == null) throw new RuntimeException("NO EXISTE EL PRODUC STORE");
        productStoreRepository.deleteById(id);
    }

    public ProductStores findByStoreId(Integer id){

        return null;
    }
}
