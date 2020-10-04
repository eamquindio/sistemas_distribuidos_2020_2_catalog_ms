package co.edu.eam.sistemasdistribuidos.catalog.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcategoria")
    private Integer id;

    @Column(name = "nombrecategoria")
    @NotBlank(message="This is not a valid name")
    @NotEmpty(message="Name is obligatory")
    @NotNull(message="Name is obligatory")
    @Size(min = 4, max = 50, message = "More than 4 characters but less than 45.")
    private String name;

    public Category() {
        this.setId(0);
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
