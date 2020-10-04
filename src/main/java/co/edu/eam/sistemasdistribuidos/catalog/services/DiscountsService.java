package co.edu.eam.sistemasdistribuidos.catalog.services;

import co.edu.eam.sistemasdistribuidos.catalog.models.Discounts;
import co.edu.eam.sistemasdistribuidos.catalog.repositories.DiscountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountsService {

    @Autowired
    private DiscountsRepository discountsRepository;

    public void create(Discounts d){
        boolean discountsExist = discountsRepository.existsById(d.getDiscountId());
        if(discountsExist) throw new RuntimeException("YA EXISTE EL DISCOUNT");
        discountsRepository.save(d);
    }


    public Discounts find(Integer id){
        return discountsRepository.findById(id).get();
    }

    public void update(Integer id, Discounts d){
        boolean discountsExist = discountsRepository.existsById(id);
        if (!discountsExist) throw  new RuntimeException("NO EXISTE EL DISCOUNT ID");
        d.setDiscountId(id);
        discountsRepository.save(d);
    }

    public void delete(Integer id){
        Discounts discounts = discountsRepository.findById(id).get();
        if (discounts == null) throw new RuntimeException("NO EXISTE EL DISCOUNT");
        discountsRepository.deleteById(id);
    }

}
