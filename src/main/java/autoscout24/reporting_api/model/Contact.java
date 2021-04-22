package autoscout24.reporting_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "listing_id", nullable = false)
    private Integer listingId;

    @Column(name = "contact_date_time", nullable = false)
    private LocalDateTime contactDateTime;

    public Contact(Integer listingId, LocalDateTime contactDateTime) {
        this.listingId = listingId;
        this.contactDateTime = contactDateTime;
    }
}
