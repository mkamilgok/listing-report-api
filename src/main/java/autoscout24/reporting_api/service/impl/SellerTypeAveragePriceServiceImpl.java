package autoscout24.reporting_api.service.impl;

import autoscout24.reporting_api.repository.ListingRepository;
import autoscout24.reporting_api.service.SellerTypeAveragePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SellerTypeAveragePriceServiceImpl implements SellerTypeAveragePriceService {

    @Autowired
    private final ListingRepository listingRepository;

    public SellerTypeAveragePriceServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Map<String, String> getSellerTypeAveragePrice() {
        Map<String, String> sellerTypeAveragePriceMap = new HashMap<>();

        int averageOfPrivate = listingRepository.findSellerAveragePrice("private").intValue();
        int averageOfDealer = listingRepository.findSellerAveragePrice("dealer").intValue();
        int averageOfOther = listingRepository.findSellerAveragePrice("other").intValue();

        sellerTypeAveragePriceMap.put("private", "€" + averageOfPrivate + ",-");
        sellerTypeAveragePriceMap.put("dealer", "€" + averageOfDealer + ",-");
        sellerTypeAveragePriceMap.put("other", "€" + averageOfOther + ",-");

        return sellerTypeAveragePriceMap;
    }
}
