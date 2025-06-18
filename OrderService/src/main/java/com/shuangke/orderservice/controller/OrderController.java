package com.shuangke.orderservice.controller;

import com.shuangke.orderservice.feign.StockFeign;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Resource
    private StockFeign stockFeign;

    @GetMapping("/order")
    public String order(@RequestParam String productName) {
        // Call the StockFeign to get stock status
        String stockStatus = stockFeign.getStockStatus(productName);

        // Return the stock status as part of the order response
        return "Order placed for " + productName + ". Stock status: " + stockStatus;
    }
}
