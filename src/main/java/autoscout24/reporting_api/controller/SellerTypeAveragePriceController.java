package autoscout24.reporting_api.controller;

import autoscout24.reporting_api.service.SellerTypeAveragePriceService;
import autoscout24.reporting_api.service.impl.SellerTypeAveragePriceServiceImpl;
import autoscout24.reporting_api.utils.ApiPaths;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(ApiPaths.sellerTypeAveragePriceControllerPath)
@Api(tags = "Average Listing Selling Price per Seller Type")
public class SellerTypeAveragePriceController {

    private final SellerTypeAveragePriceService sellerTypeAveragePriceService;

    @Autowired
    public SellerTypeAveragePriceController(SellerTypeAveragePriceServiceImpl sellerTypeAveragePriceService) {
        this.sellerTypeAveragePriceService = sellerTypeAveragePriceService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getSellerTypeAveragePrice() {

        Map<String, String> sellerTypeAveragePriceList = sellerTypeAveragePriceService.getSellerTypeAveragePrice();

        return ResponseEntity.ok(sellerTypeAveragePriceList);
    }

}
