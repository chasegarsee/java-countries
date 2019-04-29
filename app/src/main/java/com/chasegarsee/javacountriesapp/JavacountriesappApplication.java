package com.chasegarsee.javacountriesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavacountriesappApplication
{

    public static CountryList ourCountryList;

    public static void main(String[] args)
    {
        ourCountryList = new CountryList();
        SpringApplication.run(JavacountriesappApplication.class, args);
    }

}
