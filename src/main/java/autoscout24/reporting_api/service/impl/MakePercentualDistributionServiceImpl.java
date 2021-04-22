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
        List<Object[]> makeCountList = listingRepository.findMakeCounts();

        makeCountList.sort(Comparator.comparingInt(item -> Integer.parseInt(item[1].toString())));
        Collections.reverse(makeCountList);

        long totalListingCount = listingRepository.count();

        for (Object[] makeCount : makeCountList) {
            int makePercentage = (int) (Integer.parseInt(makeCount[1].toString()) * 100.0 / totalListingCount);
            makePercentualDistributionMap.put(makeCount[0].toString(), makePercentage + "%");
        }

        return makePercentualDistributionMap;
    }
}
