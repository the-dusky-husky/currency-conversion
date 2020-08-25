package com.cal.CurrencyConversionService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhisheks on 24-07-2020.
 */
@RestController
public class RequestController {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppService appService;

    @GetMapping(value = "converttotal")
    public HashMap<String, Integer> convertTotal(@RequestParam int quantity, @RequestParam String currency){

        //String url = "http://localhost:8000/convert?currency={currency}";

        String url= "http://localhost:8000/convert";
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParam("currency",currency).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity(uriComponents.toUriString(), String.class);
        String body = stringResponseEntity.getBody();
        JSONObject jsonObject = new JSONObject(body);
        System.out.println("response from curency exchange :"+jsonObject);
        int rate = jsonObject.getInt("rate");
        int val = quantity*rate;
        HashMap<String, Integer> map= new HashMap<>();
        map.put("val", val);
        return map;
    }

    @GetMapping(value = "converttotalfeign")
    public HashMap<String, Integer> convertTotalfeign(@RequestParam int quantity, @RequestParam String currency){

        Map<String, String> convert = appService.callCurrencyConversionService(currency);

        int port = Integer.parseInt(convert.get("port"));
        int rate = Integer.parseInt(convert.get("rate"));

        int val = quantity*rate;

        HashMap<String, Integer> map= new HashMap<>();
        map.put("val", val);
        map.put("port",port );
        //kafkaService.sendMessage(map.toString());
        logger.info("response sent : "+map.toString());
        return map;

    }

}
