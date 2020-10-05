package co.edu.eam.sistemasdistribuidos.catalog.repositories;

import co.edu.eam.sistemasdistribuidos.catalog.models.ProductStores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStoreRepository extends JpaRepository<ProductStores, Integer> {

    List<ProductStores> findByStoreId(Integer id);
}
