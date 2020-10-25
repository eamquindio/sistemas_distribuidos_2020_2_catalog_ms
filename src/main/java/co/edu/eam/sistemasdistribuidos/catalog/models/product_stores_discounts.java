package co.edu.eam.sistemasdistribuidos.catalog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="product_stores_discount")
public class product_stores_discounts implements Serializable {

    @Id
    private Integer id;
    @Column
    private Integer discount_id;
    @Column
    private Integer product_id;
    @Column
    private Integer store_id;

    public product_stores_discounts() { }

    public product_stores_discounts(Integer id, Integer discount_id, Integer product_id, Integer store_id) {

        this.id = id;
        this.discount_id = discount_id;
        this.product_id = product_id;
        this.store_id = store_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(Integer discount_id) {
        this.discount_id = discount_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }
}
