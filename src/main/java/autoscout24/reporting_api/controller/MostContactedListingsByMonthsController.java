package autoscout24.reporting_api.controller;

import autoscout24.reporting_api.service.MostContactedListingsByMonthsService;
import autoscout24.reporting_api.utils.ApiPaths;
import autoscout24.reporting_api.utils.ListingWithRanking;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPaths.mostContactedListingsByMonths)
@Api(tags = "The Top 5 most contacted listings per Month")
public class MostContactedListingsByMonthsController {

    private final MostContactedListingsByMonthsService mostContactedListingsByMonthsService;

    public MostContactedListingsByMonthsController(MostContactedListingsByMonthsService mostContactedListingsByMonthsService) {
        this.mostContactedListingsByMonthsService = mostContactedListingsByMonthsService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, List<ListingWithRanking>>>> getMostContactedListingsByMonths() {
        return ResponseEntity.ok(mostContactedListingsByMonthsService.getMostContactedListingsByMonths());
    }
}
