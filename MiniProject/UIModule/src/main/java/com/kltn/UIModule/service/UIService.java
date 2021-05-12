package com.kltn.UIModule.service;


import com.kltn.UIModule.dto.CommodityCreateDto;
import com.kltn.UIModule.dto.CommodityListDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UIService {
    private final RestTemplate restTemplate;

    public UIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getCommodity(){
         CommodityCreateDto commodityCreateDto
                 = restTemplate.getForObject("http://localhost:9000/v1/api/commodity/5cb443a1-8d21-11eb-908e-5404a6a1dcfe",
               CommodityCreateDto.class);
    }

    public List<CommodityListDTO> getCommodities(){
        ResponseEntity<CommodityListDTO[]> responseEntity
                = restTemplate.getForEntity("http://localhost:9000/v1/api/commodity/list",
                CommodityListDTO[].class);
        CommodityListDTO[] commodityListDTOS = responseEntity.getBody();
        return Arrays.stream(commodityListDTOS).collect(Collectors.toList());

    }

    public void deleteCommodity(UUID idCommodity) {
        try {
            restTemplate.delete("http://localhost:9000/v1/api/commodity/"+idCommodity);
        } catch (HttpClientErrorException ex) {
            String message = ex.getResponseBodyAsString();
        }
    }
}
