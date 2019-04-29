package com.chasegarsee.javacountriesapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/data")

public class CountryController
{
    //localhost:1992/data/allcountries
    @RequestMapping(value = "/allcountries")
    public ResponseEntity<?> getAllCountries()
    {
        JavacountriesappApplication.ourCountryList.countryList.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        return new ResponseEntity<>(JavacountriesappApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    //localhost:1992/data/countries/a
    @GetMapping(value = "/countries/{letter")
    public ResponseEntity<?> getCountriesLetter(@PathVariable char letter)
    {
        ArrayList<Country> rtnCountries = JavacountriesappApplication.ourCountryList
                .findCountries(e -> e.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }
}