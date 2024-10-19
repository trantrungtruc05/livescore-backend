package com.livescore.service.impl;

import com.livescore.dto.response.CountryDTO;
import com.livescore.entity.Country;
import com.livescore.repository.CountryRepository;
import com.livescore.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryDTO> getAllCountry() {
        List<CountryDTO> lsCountryDto = new ArrayList<>();
        List<Country> getAllCountry = countryRepository.findAll();
        for(Country country : getAllCountry){
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(country.getName());
            countryDTO.setCode(country.getCode());
            countryDTO.setFlag(country.getFlag());

            lsCountryDto.add(countryDTO);
        }

        return lsCountryDto;
    }
}
