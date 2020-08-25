
package com.cal.CurrencyConversionService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


@Component
public class Runner implements CommandLineRunner{

    @Autowired
    private TestClass myService;

    @Override
    public void run(String... strings) throws Exception {

        for (int i = 0; i < 50; i++) {
            myService.doSomething(i);
            TimeUnit.MILLISECONDS.sleep(200);
        }
        TimeUnit.MILLISECONDS.sleep(3000);
        myService.doSomething(0);

        TimeUnit.MILLISECONDS.sleep(4000);
        myService.doSomething(0);
    }
}
