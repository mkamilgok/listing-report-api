package autoscout24.reporting_api;

import autoscout24.reporting_api.repository.ContactRepository;
import autoscout24.reporting_api.repository.ListingRepository;
import autoscout24.reporting_api.service.MostContactedAveragePriceService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MostContractedAveragePriceServiceTest {

    @InjectMocks
    MostContactedAveragePriceService mostContactedAveragePriceService;

    @Mock
    ContactRepository contactRepository;

    @Mock
    ListingRepository listingRepository;

}
