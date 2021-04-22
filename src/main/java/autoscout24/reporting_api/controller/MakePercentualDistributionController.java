package autoscout24.reporting_api.controller;

import autoscout24.reporting_api.service.MakePercentualDistributionService;
import autoscout24.reporting_api.utils.ApiPaths;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(ApiPaths.makePercentualDistributionControllerPath)
@Api(tags = "Distribution (in percent) of available cars by Make")
public class MakePercentualDistributionController {

    private final MakePercentualDistributionService makePercentualDistributionService;

    public MakePercentualDistributionController(MakePercentualDistributionService makePercentualDistributionService) {
        this.makePercentualDistributionService = makePercentualDistributionService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getMakePercentualDistribution() {
        Map<String, String> makePercentualDistribution = makePercentualDistributionService.getMakePercentualDistribution();
        return ResponseEntity.ok(makePercentualDistribution);
    }
}
