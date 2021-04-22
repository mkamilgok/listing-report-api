package autoscout24.reporting_api.service.impl;

import autoscout24.reporting_api.repository.ContactRepository;
import autoscout24.reporting_api.repository.ListingRepository;
import autoscout24.reporting_api.service.MostContactedAveragePriceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MostContactedAveragePriceServiceImpl implements MostContactedAveragePriceService {

    private final ContactRepository contactRepository;
    private final ListingRepository listingRepository;

    public MostContactedAveragePriceServiceImpl(ContactRepository contactRepository, ListingRepository listingRepository) {
        this.contactRepository = contactRepository;
        this.listingRepository = listingRepository;
    }

    public Map<String, String> getMostContactedAveragePrice() {

        Map<String, String> averagePriceMap = new HashMap<>();

        int countOf30Percent = (int) listingRepository.count() * 3 / 10;

        //gets listing ids sorted descending with respect to their contact counts
        List<Integer> mostContactedListingsAll = contactRepository.findMostContactedListings();

        //if the count of distinct listings in contactRepository less than countOf30Percent, update countOf30Percent
        if (mostContactedListingsAll.size() < countOf30Percent) {
            countOf30Percent = mostContactedListingsAll.size();
        }

        //JPQL CANNOT LIMIT MAXIMUM RESULT SIZE(ROW NUMBER), that's why .subList() is used.
        List<Integer> mostContactedListings = mostContactedListingsAll.subList(0, countOf30Percent);

        Integer averagePrice = listingRepository.findAverageOfListings(mostContactedListings);

        averagePriceMap.put("Average price", "€" + averagePrice + ",-");

        return averagePriceMap;
    }
}
