package com.cal.CurrencyConversionService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhisheks on 24-07-2020.
 */

//@FeignClient(name = "currencyexchange", url = "http://localhost:8002")
@FeignClient(name = "currencyexchange")
@RibbonClient(name = "currencyexchange")
public interface FeignRepository {

    @GetMapping(value = "/convert")
    public Map<String, String> convert(@RequestParam("currency") String currency);

}
