package autoscout24.reporting_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingWithRanking {
    private int ranking;
    private int listingId;
    private String make;
    private String sellingPrice;
    private String mileage;
    private int totalContractsInMonth;
}
