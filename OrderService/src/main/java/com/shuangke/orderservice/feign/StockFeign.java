package com.shuangke.orderservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// This interface is used to communicate with the Stock Service using Feign

// FeignClient annotation specifies the name of the service(defined in application.yml of Stock service) to connect to
@FeignClient(name = "stock-service")
public interface StockFeign {
    @CircuitBreaker(name="stockServiceCircuitBreaker", fallbackMethod = "fallBackForStockService") // This enables circuit breaker for the method
    @GetMapping("/stock/status") // This maps to the endpoint in the Stock Service. must add "/stock"
    String getStockStatus(@RequestParam String productName);

    default String fallBackForStockService(@RequestParam String productName, Exception e) {
        System.out.println("fallBackForStockService: Stock Service is currently unavailable. Falling back to default response.");
        return "fallBackForStockService: Stock service is down, please try again later. The error was: " + e.getMessage();
    }
}
