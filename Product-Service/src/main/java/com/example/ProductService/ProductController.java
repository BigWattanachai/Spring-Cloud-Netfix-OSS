package com.example.ProductService;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final RestTemplate restTemplate;

    @Autowired
    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/search")
    public Product getProductsBySKU(@RequestParam("sku") final String sku) {
        log.info("get Product by SKU : {}" , sku);

        String url = "http://PRICING-SERVICE/products/price?sku=" + sku;
        return restTemplate.getForObject(url, Product.class);
    }
}

@Data
class Product {
    private String sku;
    private String price;
}


