package co.edu.eam.sistemasdistribuidos.catalog.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name="product_stores")
public class ProductStores implements Serializable {

    @Id
    @Column(name="product_id")
    @Min(value = 1, message = "MÍNIMO DEBE HABER UN DÍGITO")
    @Max(value = 2147483647, message = "NO PUEDE SOBREPASAR LOS 2147483647 DÍGITOS")
    private int productId;


    @Column(name="product_price")
    private float productPrice;

    @Column(name="store_id")
    @NotNull
    private int storeId;

    public ProductStores() {
    }

    public ProductStores(int productId, float productPrice, int storeId) {
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

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
