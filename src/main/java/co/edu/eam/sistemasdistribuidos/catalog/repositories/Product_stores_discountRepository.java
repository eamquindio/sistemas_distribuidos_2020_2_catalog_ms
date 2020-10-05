package co.edu.eam.sistemasdistribuidos.catalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import co.edu.eam.sistemasdistribuidos.catalog.models.product_stores_discounts;

import java.util.List;

@Repository
public interface Product_stores_discountRepository extends JpaRepository<product_stores_discounts, Integer> {

    //en vez de Page estaba el List
    @Query("SELECT p FROM product_stores_discounts p WHERE p.store_id = :storeid")
    Page<product_stores_discounts> buscarStore_id(Integer storeid, Pageable pageable);

    //en vez de Page estaba el List
    @Query("SELECT p FROM product_stores_discounts p WHERE p.product_id = :productid")
    Page<product_stores_discounts> buscarProduct_id(Integer productid, Pageable pageable);
}
