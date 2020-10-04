package co.edu.eam.sistemasdistribuidos.catalog.repositories;

import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountsRepository extends JpaRepository<Discounts, Integer> {
}
