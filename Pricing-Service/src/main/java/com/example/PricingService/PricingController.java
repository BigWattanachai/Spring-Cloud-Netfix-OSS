package com.example.PricingService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products/price")
@RefreshScope
public class PricingController {
    @Value("${product.price}")
    String productPrice;

    @GetMapping
    public Price getPrice(@RequestParam(value = "sku") final String sku) {
        Price price = new Price();
        price.setSku(sku);
        price.setPrice(productPrice);
        return price;
    }
}

@Data
class Price {
    private String sku;
    private String price;
}
