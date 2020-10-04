package co.edu.eam.sistemasdistribuidos.catalog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="discounts")
public class Discounts implements Serializable {

    @Id
    @Column(name="discount_id")
    private int discountId;

    @Column(name="percentage")
    private int percentage;

    @Column(name="discount_type")
    private String discountType;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    public Discounts() {
    }

    public Discounts(int discountId, int percentage, String discountType, String startDate, String endDate) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
