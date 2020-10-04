package co.edu.eam.sistemasdistribuidos.catalog.repositories;

import co.edu.eam.sistemasdistribuidos.catalog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c From Category c where lower(c.name) like lower(:name)")
    List<Category> getCategoryByName(String name);
}
