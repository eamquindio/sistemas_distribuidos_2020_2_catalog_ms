package co.edu.eam.sistemasdistribuidos.catalog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @Column(name = "product_id")
    @Min(value = 1, message = "Mínimo 1 dígito")
    @Max(value = 2147483647, message = "Máximo 2147483647 dígitos")
    private Integer productId;

    @Column(name = "product_name")
    @NotEmpty(message = "El nombre del producto es obligatorio")
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 3, max = 250, message = "Mínimo 3 y máximo 250 caracteres")
    private String productName;

    @Column(name = "category_id")
    @NotNull
    @Min(value = 1, message = "Mínimo 1 dígito")
    @Max(value = 2147483647, message = "Máximo 2147483647 dígitos")
    private Integer categoryId;

    public Product() {
    }

    public Product(Integer productId, String productName, Integer categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
