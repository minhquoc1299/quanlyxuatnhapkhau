package com.haonguyen.CommodityService.controller;


import com.haonguyen.CommodityService.apiExceptionHandler.SaveException;
import com.haonguyen.CommodityService.dto.*;
import com.haonguyen.CommodityService.iservice.ICommodityService;
import com.haonguyen.CommodityService.iservice.IWarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/api/commodity")
public class CommodityController {
    private final ICommodityService iCommodityService;
    private final IWarehouseService iWarehouseService;

    public CommodityController(ICommodityService iCommodityService, IWarehouseService iWarehouseService) {

        this.iCommodityService = iCommodityService;
        this.iWarehouseService = iWarehouseService;
    }
    /**
     * tim kiem da  dieu kien
     */
    @PostMapping("/search")
    public List<CommoditySearchDto> searchCommodity(@RequestBody keySearchDto keySearchDto) throws SaveException {
        List<CommoditySearchDto> commoditySearchDtos = iCommodityService.searchCommodity(keySearchDto.getKey());
        return commoditySearchDtos;
    }
    /**
     * xoa hang
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCommodity(@PathVariable("id") UUID id){
        iCommodityService.deleteCommodity(id);
        return ResponseEntity.ok().body("success");
    }


    /**
     * tim hang theo id loai hang
     */
    @GetMapping("/getIdTypeOfCommodity/{idTypeOfCommodity}")
    public List<TypeOfCommodityDto> getCommodityByIdTypeOfCommodity(@PathVariable("idTypeOfCommodity") UUID idTypeOfCommodity) {

        List<TypeOfCommodityDto> typeOfCommodityDto = iCommodityService.findCommodityByIdTypeOfCommodity(idTypeOfCommodity);
        return typeOfCommodityDto;
    }

    /**
     * them hang
     */
    @PostMapping("/")
    public CommodityCreateDto addCommodity(@RequestBody CommodityCreateDto commodityCreateDto) {
        CommodityCreateDto commodityCreateDto1 = iCommodityService.addCommodity(commodityCreateDto);
        return commodityCreateDto1;
    }

    /**
     * cap nhat
     */
    @PutMapping("/{id}")
    public CommodityUpdateDto updateCommodity(@RequestBody CommodityUpdateDto commodityUpdateDto,
                                              @PathVariable("id") UUID idCommodity){

        CommodityUpdateDto commodityUpdateDto1 = iCommodityService.updateCommodity(commodityUpdateDto, idCommodity);
        return commodityUpdateDto1;
    }

    /**
     * hang ton kho
     */
    @PostMapping("/check")
    public List<CommodityInWarehouseDto> checkCommodityInWarehouse() {
        List<CommodityInWarehouseDto> commodityInWarehouseDtos = iWarehouseService.checkCommodityInWarehouse();
        return commodityInWarehouseDtos;

    }

    /**
     * lay thong tin hang th eo id
     */
    @GetMapping("/{id}")
    public CommodityCreateDto getCommodityById(@PathVariable("id") UUID id) {
        CommodityCreateDto commodityCreateDto = iCommodityService.CommodityById(id);
        return commodityCreateDto;
    }

    /**
     * thong tin thue
     */
    @RequestMapping(value = "/getTypeTax/{id}", method = RequestMethod.GET)
    public TypeAndTaxCommodityAPI getTypeTaxCommodity(@PathVariable("id") UUID idCommodity) throws Exception {
        TypeAndTaxCommodityAPI typeAndTaxCommodityAPI = iCommodityService.getTypeTaxCommodity(idCommodity);
        return typeAndTaxCommodityAPI;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<CommodityListDTO> getListCommodity(){
        return iCommodityService.getListCommodity();
    }
    @RequestMapping(value = "/listtype",method = RequestMethod.GET)
    public List<TypeList> getListType(){
        return iCommodityService.getListType();
    }
    @RequestMapping(value = "/listwarehouse",method = RequestMethod.GET)
    public List<WarehouseList> getListWarehouse(){
        return iWarehouseService.getListWarehouse();
    }
    @RequestMapping(value = "/listcountry",method = RequestMethod.GET)
    public List<CountryList> getListCountry(){
        return iCommodityService.getListCountry();
    }
    @RequestMapping(value = "/listWarehouseCapacity/{quantity}",method = RequestMethod.GET)
    public List<WarehouseList> getListWarehouseCapacity(@PathVariable("quantity") Double quantity){
        List<WarehouseList> warehouseCapacity = iWarehouseService.getListWarehouseCapacity(quantity);
        return warehouseCapacity;
    }
}

