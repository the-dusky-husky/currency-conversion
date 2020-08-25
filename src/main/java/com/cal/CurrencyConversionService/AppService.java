package com.cal.CurrencyConversionService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhisheks on 14-08-2020.
 */
@Service
public class AppService {

    @Autowired
    private FeignRepository feignRepository;

    /* @Autowired
     private KafkaService kafkaService;*/

    @HystrixCommand(fallbackMethod = "fallback")
    public Map<String, String> callCurrencyConversionService(String currency){
        Map<String, String> convert = feignRepository.convert(currency);
        System.out.println("response received feign : "+convert);
        return convert;
    }

    public Map<String, String> fallback(String currency){
        Map<String, String> convert = new HashMap<>();
        convert.put("rate","1");
        convert.put("port","0000");
        System.out.println("fallback : "+convert);
        return convert;
    }


}
