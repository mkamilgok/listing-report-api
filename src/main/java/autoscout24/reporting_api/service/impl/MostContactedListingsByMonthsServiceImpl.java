package autoscout24.reporting_api.service.impl;

import autoscout24.reporting_api.repository.ContactRepository;
import autoscout24.reporting_api.service.MostContactedListingsByMonthsService;
import autoscout24.reporting_api.utils.ListingWithRanking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MostContactedListingsByMonthsServiceImpl implements MostContactedListingsByMonthsService {

    private final ContactRepository contactRepository;

    public MostContactedListingsByMonthsServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Map<String, List<ListingWithRanking>>> getMostContactedListingsByMonths() {

        List<Map<String, List<ListingWithRanking>>> mostContactedListingsByMonths = new ArrayList<>();

        LocalDateTime earliestContactDate = contactRepository.getEarliestContactDate();
        int startYear = earliestContactDate.getYear();
        Month startMonth = earliestContactDate.getMonth();

        LocalDateTime latestContactDate = contactRepository.getLatestContactDate();
        int lastYear = latestContactDate.getYear();
        Month lastMonth = latestContactDate.getMonth();

        int currentYear = startYear;
        Month currentMonth = startMonth;

        //Get monthly report for each month from the earliest date to the latest date
        while (currentYear < lastYear || (currentYear == lastYear && currentMonth.getValue() <= lastMonth.getValue())) {

            Map<String, List<ListingWithRanking>> mostContactedListingsOfCurrentMonth = new HashMap<>();

            List<ListingWithRanking> topListingsOfMonth = getTopListingsOfMonth(currentYear, currentMonth);

            mostContactedListingsOfCurrentMonth.put(currentYear + ", " + currentMonth, topListingsOfMonth);

            mostContactedListingsByMonths.add(mostContactedListingsOfCurrentMonth);

            if (currentMonth.getValue() < 12) {
                currentMonth = Month.of(currentMonth.getValue() + 1);
            } else {
                currentYear++;
                currentMonth = Month.JANUARY;
            }
        }

        return mostContactedListingsByMonths;
    }

    private List<ListingWithRanking> getTopListingsOfMonth(int currentYear, Month currentMonth) {

        List<ListingWithRanking> topListings = new ArrayList<>();

        LocalDateTime startDateTime = LocalDateTime.of(currentYear, currentMonth, 1, 0, 0);

        int lastDayOfMonth = (currentMonth == Month.FEBRUARY && currentYear % 4 != 0) ? 28 : currentMonth.maxLength();

        LocalDateTime endDateTime = LocalDateTime.of(currentYear, currentMonth, lastDayOfMonth, 23, 59);

        List<Object[]> top5Listings = contactRepository.findMostContactedListingsBetweenDates(startDateTime, endDateTime)
                                                        .subList(0, 5);

        for (int i = 0; i < top5Listings.size(); i++) {
            Object[] listing = top5Listings.get(i);

            int ranking = i + 1;
            int listingId = Integer.parseInt(listing[0].toString());
            String make = listing[1].toString();
            String sellingPrice = listing[2].toString();
            String mileage = listing[3].toString();
            int totalContractsInMonth = Integer.parseInt(listing[4].toString());

            ListingWithRanking listingWithRanking = new ListingWithRanking(ranking, listingId, make, sellingPrice, mileage, totalContractsInMonth);

            topListings.add(listingWithRanking);
        }

        return topListings;
    }
}
