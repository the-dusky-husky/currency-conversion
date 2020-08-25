package com.cal.CurrencyConversionService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by abhisheks on 15-08-2020.
 */
@Service
public class TestClass {

    @HystrixCommand(fallbackMethod = "defaultDoSomething")
    public void doSomething(int input) {
        System.out.println("normal : " + input);
        System.out.println("output: " + input / 0);
    }

    @HystrixCommand(fallbackMethod = "defaultDoSomething")
    public void doSomethingSlow(int input) throws Exception{
        System.out.println("normal delay : " + input);
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("output: " + input / 1);
    }
    public void defaultDoSomething(int input) {
        System.out.println("fallback : " + input);
    }
}