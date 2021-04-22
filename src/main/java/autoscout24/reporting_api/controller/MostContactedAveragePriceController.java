package autoscout24.reporting_api.controller;


import autoscout24.reporting_api.service.MostContactedAveragePriceService;
import autoscout24.reporting_api.utils.ApiPaths;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(ApiPaths.mostContactedAveragePrice)
@Api(tags = "Average price of the 30% most contacted listings")
public class MostContactedAveragePriceController {

    private final MostContactedAveragePriceService mostContactedAveragePriceService;

    public MostContactedAveragePriceController(MostContactedAveragePriceService mostContactedAveragePriceService) {
        this.mostContactedAveragePriceService = mostContactedAveragePriceService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getMostContactedAveragePrice() {
        Map<String, String> mostContactedAveragePrice = mostContactedAveragePriceService.getMostContactedAveragePrice();
        return ResponseEntity.ok(mostContactedAveragePrice);
    }
}
