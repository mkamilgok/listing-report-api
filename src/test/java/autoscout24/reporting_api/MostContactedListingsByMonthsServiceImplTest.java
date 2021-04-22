package autoscout24.reporting_api;

import autoscout24.reporting_api.repository.ContactRepository;
import autoscout24.reporting_api.service.impl.MostContactedListingsByMonthsServiceImpl;
import autoscout24.reporting_api.utils.ListingWithRanking;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MostContactedListingsByMonthsServiceImplTest {

    @InjectMocks
    MostContactedListingsByMonthsServiceImpl mostContactedListingsByMonthsServiceImpl;

    @Mock
    ContactRepository contactRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMostContactedListingsByMonthsTest() {
        List<Object[]> topListingsTest = new ArrayList<>();
        topListingsTest.add(new Object[]{"1000", "BMW", "20000", "55000", "20"});
        topListingsTest.add(new Object[]{"1212", "Audi", "33000", "55000", "20"});
        topListingsTest.add(new Object[]{"1010", "Ford", "10000", "55000", "20"});
        topListingsTest.add(new Object[]{"1070", "Mercedes", "40000", "55000", "20"});
        topListingsTest.add(new Object[]{"1001", "Ferrari", "50000", "55000", "20"});

        LocalDateTime earliestDate = LocalDateTime.of(2020, 1, 1, 1, 1);
        LocalDateTime latestDate = LocalDateTime.of(2021, 4, 22, 1, 1);

        given(contactRepository.getEarliestContactDate()).willReturn(earliestDate);

        given(contactRepository.getLatestContactDate()).willReturn(latestDate);

        given(contactRepository.findMostContactedListingsBetweenDates(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(topListingsTest);

        List<Map<String, List<ListingWithRanking>>> result = mostContactedListingsByMonthsServiceImpl
                .getMostContactedListingsByMonths();

        assertEquals(16, result.size());

        verify(contactRepository, times(1)).getEarliestContactDate();

        verify(contactRepository, times(1)).getLatestContactDate();

        verify(contactRepository, times(16))
                .findMostContactedListingsBetweenDates(any(LocalDateTime.class), any(LocalDateTime.class));
    }
}
