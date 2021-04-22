package autoscout24.reporting_api;

import autoscout24.reporting_api.repository.ContactRepository;
import autoscout24.reporting_api.repository.ListingRepository;
import autoscout24.reporting_api.service.MostContactedAveragePriceService;
import autoscout24.reporting_api.service.impl.MostContactedAveragePriceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class MostContractedAveragePriceServiceImplTest {

    @InjectMocks
    MostContactedAveragePriceServiceImpl mostContactedAveragePriceServiceImpl;

    @Mock
    ContactRepository contactRepository;

    @Mock
    ListingRepository listingRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMostContactedAveragePriceShouldReturnCorrectValue(){

        given(listingRepository.count()).willReturn(10L);

        List<Integer> testList = new ArrayList<>();
        testList.add(5);
        testList.add(15);
        testList.add(10);

        given(contactRepository.findMostContactedListings()).willReturn(testList);

        given(listingRepository.findAverageOfListings(testList.subList(0, 3))).willReturn(10);

        Map<String, String> expectedResult = mostContactedAveragePriceServiceImpl.getMostContactedAveragePrice();

        assertEquals(expectedResult.size(), 1);
        assertEquals(expectedResult.entrySet().iterator().next().getValue(), "€10,-");

        verify(listingRepository, times(1)).count();
        verify(contactRepository, times(1)).findMostContactedListings();
        verify(listingRepository, times(1)).findAverageOfListings(testList.subList(0, 3));
    }

}
