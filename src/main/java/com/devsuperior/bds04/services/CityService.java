package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll(){
        List<City> result = repository.findAll(Sort.by("name"));
        return result.stream().map(CityDTO::new).toList();
    }

    @Transactional
    public CityDTO insert(CityDTO dto){
        City city = new City();
        city.setName(dto.getName());

        city = repository.save(city);
        return new CityDTO(city);
    }
}
