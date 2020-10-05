package co.edu.eam.sistemasdistribuidos.catalog.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="discounts")
public class Discounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="discount_id")
    private int discountId;

    @Column(name="percentage")
    @Min(value = 1, message = "MÍNIMO DEBE HABER UN DÍGITO")
    @Max(value = 100, message = "NO PUEDE SOBREPASAR LOS 100")
    private int percentage;

    @Column(name="discount_type")
    @NotEmpty(message = "LA RAZÓN DE DESCUENTO ES NECESARIA")
    @NotBlank(message = "EL CAMPO NO PUEDE QUEDAR EN BLANCO")
    @Size(min = 3, max = 250, message = "MÍNIMO DEBEN HABER 3 Y MÁXIMO 25 CARÁCTERES")
    private String discountType;

    @Column(name="start_date")
    @NotNull
    private Date startDate;

    @Column(name="end_date")
    @NotNull
    private Date endDate;

    public Discounts() {
    }

    public Discounts(int discountId, int percentage, String discountType, Date startDate, Date endDate) {
        this.discountId = discountId;
        this.percentage = percentage;
        this.discountType = discountType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
