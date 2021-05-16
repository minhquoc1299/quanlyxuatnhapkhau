package com.kltn.UIModule.service;

import com.kltn.UIModule.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UIImportService {
    @Autowired
    private UICommodityService uiCommodityService;
    @Autowired
    private RestTemplate restTemplate;
    private String urlImport = "http://localhost:9000/v1/api/import/";

    public List<TypeList> getTypes(){
        List<TypeList> typeLists = uiCommodityService.listTypeOfCommodity();
        return typeLists;
    }

    public List<WarehouseList> getWarehouses(){
        List<WarehouseList> warehouseList = uiCommodityService.listWarehouse();
        return warehouseList;
    }

    public List<ImportDTO> getListImport(){
        ResponseEntity<ImportDTO[]> response = restTemplate.getForEntity(
                urlImport + "list",ImportDTO[].class);
        ImportDTO[] importList = response.getBody();
        return Arrays.stream(importList).collect(Collectors.toList());
    }
    public List<CountryList> getCountryList(){
        List<CountryList> countryList = uiCommodityService.listCountry();
        return countryList;
    }
    public List<CommodityListDTO> getCommodityList(){
        List<CommodityListDTO> commodityList = uiCommodityService.getCommodities();
        return commodityList;
    }

}
