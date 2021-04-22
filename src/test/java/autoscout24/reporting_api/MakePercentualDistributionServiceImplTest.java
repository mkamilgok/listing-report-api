package autoscout24.reporting_api;

import autoscout24.reporting_api.repository.ListingRepository;
import autoscout24.reporting_api.service.impl.MakePercentualDistributionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MakePercentualDistributionServiceImplTest {

    @InjectMocks
    MakePercentualDistributionServiceImpl makePercentualDistributionServiceImpl;

    @Mock
    ListingRepository listingRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMakePercentualDistributionTest() {

        Map<String, String> expectedResult = new LinkedHashMap<>();
        expectedResult.put("Mercedes", "33%");
        expectedResult.put("Ford", "29%");
        expectedResult.put("BMW", "23%");
        expectedResult.put("Audi", "15%");

        List<Object[]> makeCountlist = new ArrayList<>();
        makeCountlist.add(new Object[]{"BMW", 23});
        makeCountlist.add(new Object[]{"Mercedes", 33});
        makeCountlist.add(new Object[]{"Audi", 15});
        makeCountlist.add(new Object[]{"Ford", 29});

        given(listingRepository.findMakeCounts()).willReturn(makeCountlist);

        given(listingRepository.count()).willReturn(100L);

        Map<String, String> result = makePercentualDistributionServiceImpl.getMakePercentualDistribution();

        assertEquals(expectedResult, result);

        verify(listingRepository, times(1)).findMakeCounts();

        verify(listingRepository, times(1)).count();
    }

}
