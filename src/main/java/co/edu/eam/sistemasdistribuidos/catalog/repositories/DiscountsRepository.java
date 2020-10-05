package co.edu.eam.sistemasdistribuidos.catalog.repositories;

import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountsRepository extends JpaRepository<Discounts, Integer> {

    @Query("SELECT d from Discounts d where CURRENT_DATE between d.startDate AND d.endDate")
    List<Discounts> getDiscountByDate();

}
