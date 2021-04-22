package autoscout24.reporting_api.service;

import autoscout24.reporting_api.utils.ListingWithRanking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MostContactedListingsByMonthsService {

    List<Map<String, List<ListingWithRanking>>> getMostContactedListingsByMonths();

}
