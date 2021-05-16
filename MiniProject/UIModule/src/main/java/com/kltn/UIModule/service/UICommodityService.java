package com.kltn.UIModule.service;


import com.kltn.UIModule.dto.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UICommodityService {
    private String urlCommodity = "http://localhost:9000/v1/api/commodity/";
    private final RestTemplate restTemplate;
    public UICommodityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CommodityCreateDto getCommodity(UUID idCommodity){
         CommodityCreateDto commodityCreateDto
                 = restTemplate.getForObject(urlCommodity + idCommodity,
               CommodityCreateDto.class);
         return commodityCreateDto;
    }

    public List<CommodityListDTO> getCommodities(){
        ResponseEntity<CommodityListDTO[]> responseEntity
                = restTemplate.getForEntity(urlCommodity + "list",
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

    public void createCommodity(CommodityCreateDto commodityCreateDto){
        CommodityCreateDto result
                = restTemplate.postForObject(urlCommodity,commodityCreateDto,CommodityCreateDto.class);
    }
    public void updateCommodity(CommodityCreateDto commodityCreateDto) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CommodityCreateDto> requestUpdate = new HttpEntity<>(commodityCreateDto,headers);
         restTemplate.exchange(urlCommodity+commodityCreateDto.getId(),
                 HttpMethod.PUT,
                 requestUpdate,
                 CommodityCreateDto.class);
    }

    public List<TypeList> listTypeOfCommodity() {
        ResponseEntity<TypeList[]> responseEntity
                = restTemplate.getForEntity(urlCommodity + "listtype",
                TypeList[].class);
        TypeList[] typeLists = responseEntity.getBody();
        return Arrays.stream(typeLists).collect(Collectors.toList());
    }

    public List<WarehouseList> listWarehouse(){
        ResponseEntity<WarehouseList[]> responseEntity
                = restTemplate.getForEntity(urlCommodity + "listwarehouse",
                WarehouseList[].class);
        WarehouseList[] warehouseList = responseEntity.getBody();
        return Arrays.stream(warehouseList).collect(Collectors.toList());
    }
    public List<CountryList> listCountry(){
        ResponseEntity<CountryList[]> responseEntity
                = restTemplate.getForEntity(urlCommodity + "listcountry",
                CountryList[].class);
        CountryList[] countryList = responseEntity.getBody();
        return Arrays.stream(countryList).collect(Collectors.toList());
    }


}
