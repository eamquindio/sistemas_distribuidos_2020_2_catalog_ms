package co.edu.eam.sistemasdistribuidos.catalog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="product_stores")
public class ProductStores implements Serializable {

    @Id
    @Column(name="product_id")
    private int productId;

    @Column(name="product_price")
    private String productPrice;

    @Column(name="store_id")
    private int storeId;

    public ProductStores() {
    }

    public ProductStores(int productId, String productPrice, int storeId) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.storeId = storeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
