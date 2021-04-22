package autoscout24.reporting_api.repository;

import autoscout24.reporting_api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    //JPQL CANNOT LIMIT MAXIMUM RESULT SIZE
    @Query(value = "SELECT listing_id FROM Contact GROUP BY listing_id ORDER BY COUNT(contact_date_time) DESC", nativeQuery = true)
    List<Integer> findMostContactedListings();

    @Query(value = "SELECT MIN(contact_date_time) FROM Contact", nativeQuery = true)
    LocalDateTime getEarliestContactDate();

    @Query(value = "SELECT MAX(contact_date_time) FROM Contact", nativeQuery = true)
    LocalDateTime getLatestContactDate();

    @Query(value = "SELECT listing_id, make, price, mileage, COUNT(contact_date_time) " +
            "FROM Contact INNER JOIN Listing ON Contact.listing_id = Listing.id " +
            "WHERE contact_date_time BETWEEN :dateTime1 AND :dateTime2 " +
            "GROUP BY listing_id " +
            "ORDER BY COUNT(contact_date_time) DESC"
            , nativeQuery = true)
    List<Object[]> findMostContactedListingsBetweenDates(@Param("dateTime1") LocalDateTime dateTime1,
                                                         @Param("dateTime2") LocalDateTime dateTime2);
}
