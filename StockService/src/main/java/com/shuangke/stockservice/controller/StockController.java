package com.shuangke.stockservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @GetMapping("/status")
    public String getStockStatus(@RequestParam String productName) {//must use @RequestParam to bind the request parameter
       if (productName.equals("iphone")) {
           throw new RuntimeException("The StockService is down");
       }
        return "Stock service is running";
    }
}
