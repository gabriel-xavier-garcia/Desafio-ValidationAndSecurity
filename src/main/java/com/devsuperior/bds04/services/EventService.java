package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable){
        Page<Event> result = repository.findAll(pageable);
        return result.map(EventDTO::new);
    }

    @Transactional
    public EventDTO insert(EventDTO dto){
        Event event = new Event();
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        City city = cityRepository.getReferenceById(dto.getCityId());
        event.setCity(city);
        event = repository.save(event);
        return new EventDTO(event);
    }
}
