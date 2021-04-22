package autoscout24.reporting_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Listing {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "mileage", nullable = false)
    private Integer mileage;

    @Column(name = "seller_type", nullable = false)
    private String sellerType;
}
