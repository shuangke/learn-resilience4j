package com.shuangke.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// This interface is used to communicate with the Stock Service using Feign

// FeignClient annotation specifies the name of the service(defined in application.yml of Stock service) to connect to
@FeignClient(name = "stock-service")
public interface StockFeign {
    @GetMapping("/stock/status") // This maps to the endpoint in the Stock Service. must add "/stock"
    String getStockStatus(@RequestParam String productName);
}
