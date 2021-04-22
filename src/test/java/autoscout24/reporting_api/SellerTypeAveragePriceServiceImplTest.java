package autoscout24.reporting_api;

import autoscout24.reporting_api.repository.ListingRepository;
import autoscout24.reporting_api.service.impl.SellerTypeAveragePriceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class SellerTypeAveragePriceServiceImplTest {

    @InjectMocks
    SellerTypeAveragePriceServiceImpl sellerTypeAveragePriceServiceImpl;

    @Mock
    ListingRepository listingRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSellerTypeAveragePriceShouldReturnCorrectResultSize() {

        given(listingRepository.findSellerAveragePrice(any(String.class))).willReturn(any(double.class));

        Map<String, String> expectedResult = sellerTypeAveragePriceServiceImpl.getSellerTypeAveragePrice();

        assertEquals(expectedResult.size(), 3);

        verify(listingRepository, times(3)).findSellerAveragePrice(any(String.class));
    }

}
