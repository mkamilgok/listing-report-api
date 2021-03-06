package autoscout24.reporting_api.service.impl;

import autoscout24.reporting_api.repository.ListingRepository;
import autoscout24.reporting_api.service.MakePercentualDistributionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MakePercentualDistributionServiceImpl implements MakePercentualDistributionService {

    private final ListingRepository listingRepository;

    public MakePercentualDistributionServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }


    public Map<String, String> getMakePercentualDistribution() {

        Map<String, String> makePercentualDistributionMap = new LinkedHashMap<>();
        List<Object[]> makeCountList = listingRepository.findMakeCounts();//gets List of {make,countOfContact} pairs

        makeCountList.sort(Comparator.comparingInt(item -> Integer.parseInt(item[1].toString())));
        Collections.reverse(makeCountList);

        long totalListingCount = listingRepository.count();

        for (Object[] makeCount : makeCountList) {

            String make = makeCount[0].toString();

            int countOfMake = Integer.parseInt(makeCount[1].toString());

            int makePercentage = (int) ((countOfMake) * 100.0 / totalListingCount); //calculates the percentage of make

            makePercentualDistributionMap.put(make, makePercentage + "%");
        }

        return makePercentualDistributionMap;
    }
}
