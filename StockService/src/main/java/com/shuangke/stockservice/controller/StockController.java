package com.shuangke.stockservice.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @RateLimiter(name = "queryStockLimiter", fallbackMethod = "queryStockLimiterFallBack") // Use the rate limiter defined in application.yml
    @GetMapping("/status")
    public String getStockStatus(@RequestParam String productName) {//must use @RequestParam to bind the request parameter
       if (productName.equals("iphone")) {
           throw new RuntimeException("The StockService is down");
       }
        return "Stock service is running";
    }

    public String queryStockLimiterFallBack(@RequestParam String productName, Exception e) {
        return "{Stock Service FallBack Method: too many requests. {" + e.getMessage() + "}}";
    }
}
