package com.chasegarsee.javacountriesapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/data")

public class CountryNamesController
{
    //localhost:1992/data/allcountries
    @RequestMapping(value = "/allcountries")
    public ResponseEntity<?> getAllCountries()
    {
        JavacountriesappApplication.ourCountryList.countryList.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        return new ResponseEntity<>(JavacountriesappApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    //localhost:1992/data/countries/b
    @GetMapping(value = "/countries/{letter}")
    public ResponseEntity<?> getCountriesLetter(@PathVariable char letter)
    {
        ArrayList<Country> rtnCountries = JavacountriesappApplication.ourCountryList
                .findCountries(e -> e.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //localhost:1992/names/size/
    @GetMapping("/size/{number}")
    public ResponseEntity<?> getCountriesNamesLargerThanOrEqualTo(@PathVariable int number)
    {
        CountryList countries = JavacountriesappApplication.ourCountryList;
        ArrayList<Country> rtnCountries = countries.findCountries(c -> c.getName().length() >= number);
        rtnCountries.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    @GetMapping("/population/{people}")
    public ResponseEntity<?> getCountriesWithGreaterThanPop(@PathVariable int people)
    {
        CountryList countries = JavacountriesappApplication.ourCountryList;
        ArrayList<Country> rtnCountries = countries.findCountries(c -> c.getPopulation() >= people);
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    @GetMapping("/population/min")
    public ResponseEntity<?> getCountryWithMinPop()
    {
        CountryList countries = JavacountriesappApplication.ourCountryList;
        countries.countryList.sort((c1, c2) -> c1.getPopulation() - c2.getPopulation());
        return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
    }

    @GetMapping("/population/max")
    public ResponseEntity<?> getCountryWithMaxPop()
    {
        CountryList countries = JavacountriesappApplication.ourCountryList;
        countries.countryList.sort((c1, c2) -> c1.getPopulation() + c2.getPopulation());
        return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
    }



}