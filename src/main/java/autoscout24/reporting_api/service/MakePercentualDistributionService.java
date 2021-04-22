package autoscout24.reporting_api.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MakePercentualDistributionService {

    Map<String, String> getMakePercentualDistribution();

}
