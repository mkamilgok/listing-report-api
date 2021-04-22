package autoscout24.reporting_api.repository;

import autoscout24.reporting_api.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer> {

    @Query(value = "SELECT avg(price) FROM Listing WHERE seller_type = :sellerType", nativeQuery = true)
    Double findSellerAveragePrice(@Param("sellerType") String sellerType);

    @Query(value = "SELECT DISTINCT make, count(make) FROM Listing GROUP BY make", nativeQuery = true)
    List<Object[]> findMakeCounts();

    @Query(value = "SELECT avg(price) FROM Listing WHERE id IN :listingIds", nativeQuery = true)
    Integer findAverageOfListings(@Param("listingIds") List<Integer> listingIds);

}
