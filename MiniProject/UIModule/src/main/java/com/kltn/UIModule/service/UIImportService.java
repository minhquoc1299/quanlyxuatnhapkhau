package com.kltn.UIModule.service;

import com.kltn.UIModule.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UIImportService {
    @Autowired
    private UICommodityService uiCommodityService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CoreService coreService;
    private String urlImport = "http://localhost:9000/v1/api/import/";
    public List<FormCommodityImport> listCommodityImport = new ArrayList<>();

    public List<TypeList> getTypes() {
        List<TypeList> typeLists = uiCommodityService.listTypeOfCommodity();
        return typeLists;
    }

    public List<WarehouseList> getWarehouses() {
        List<WarehouseList> warehouseList = uiCommodityService.listWarehouse();
        return warehouseList;
    }

    public List<ImportDTO> getListImport() {
        ResponseEntity<ImportDTO[]> response = restTemplate.getForEntity(
                urlImport + "list", ImportDTO[].class);
        ImportDTO[] importList = response.getBody();
        return Arrays.stream(importList).collect(Collectors.toList());
    }

    public List<CountryList> getCountryList() {
        List<CountryList> countryList = uiCommodityService.listCountry();
        return countryList;
    }

    public List<CommodityListDTO> getCommodityList() {
        List<CommodityListDTO> commodityList = uiCommodityService.getCommodities();
        return commodityList;
    }

    public Double sumQuantity() {
        double sumQuantity = 0;
        for (FormCommodityImport info : listCommodityImport) {
            sumQuantity = sumQuantity + info.getQuantity();
        }
        return sumQuantity;
    }

    public List<WarehouseList> getWarehouseCapacity() {
        List<WarehouseList> warehouseList = uiCommodityService.listWarehouseCapacity(sumQuantity());
        return warehouseList;
    }

    public void addCommodityImportList(CommodityDTO commodityDTO) {
        for (FormCommodityImport info : listCommodityImport) {
            if (info.getIdCommodity().equals(commodityDTO.getIdCommodity())) {
                info.setQuantity(commodityDTO.getQuantity() + info.getQuantity());
                info.setTotal(info.getQuantity() * info.getPrice());
                return;
            }
        }

        CommodityCreateDto commodity = uiCommodityService.getCommodity(commodityDTO.getIdCommodity());

        FormCommodityImport commodityImport = new FormCommodityImport();
        commodityImport.setIdCommodity(commodity.getId());
        commodityImport.setCommodityName(commodity.getCommodityName());
        commodityImport.setPrice(commodity.getPrice());
        commodityImport.setQuantity(commodityDTO.getQuantity());
        commodityImport.setTotal(commodityImport.getQuantity() * commodityImport.getPrice());
        listCommodityImport.add(commodityImport);

    }

    public List<FormCommodityImport> getCommodityImportList() {
        return listCommodityImport;
    }

    public void deleteCommodityImportList(UUID idCommodity) {
        for (FormCommodityImport info : listCommodityImport) {
            if (info.getIdCommodity().equals(idCommodity)) {
                listCommodityImport.remove(info);
                return;
            }
        }
    }

    public void add(FormImportCreate formImportCreate) {
        formImportCreate.setListCommodity(listCommodityImport);

        ImportCreatePOST data = new ImportCreatePOST();
        data.setId(formImportCreate.getIdImport());
        data.setIdCountry(formImportCreate.getIdCountry());
        data.setIdWarehouse(formImportCreate.getIdWarehouse());
        data.setDate(formImportCreate.getDateImport());
        data.setType(1);


        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setUrls(formImportCreate.getListDocument());
        coreService.upLoadImage(documentDTO);
        List<String> urlImages = new ArrayList<>();
        for (MultipartFile info : formImportCreate.getListDocument()) {
            urlImages.add("/images/" + info.getOriginalFilename());
        }

        data.setListDocument(urlImages);

        List<CommodityImportPOST> commodityImportPOSTS = new ArrayList<>();
        for (FormCommodityImport info : formImportCreate.getListCommodity()) {
            CommodityImportPOST commodityImportPOST = new CommodityImportPOST();
            commodityImportPOST.setIdCommodity(info.getIdCommodity());
            commodityImportPOST.setQuantity(info.getQuantity());
            commodityImportPOST.setTotal(info.getTotal());
            commodityImportPOSTS.add(commodityImportPOST);
        }
        data.setListCommodity(commodityImportPOSTS);

        this.createCommodity(data);

    }
    public void createCommodity(ImportCreatePOST importCreatePOST){
        ImportCreatePOST result
                = restTemplate.postForObject(urlImport + "add",importCreatePOST,ImportCreatePOST.class);
        result.getId();
        listCommodityImport.clear();
    }

    public Double sumTotal(InfoDetailsImportDTO infoDetailsImportDTO){
        double sumTotal = 0;
        for(InfoDetailsCommodityImport info: infoDetailsImportDTO.getCommodityImportList()){
            sumTotal = sumTotal + info.getTotal();
        }
        return sumTotal;
    }
    public InfoDetailsImportDTO getDetailsByIdImport(UUID idImport) {
        InfoDetailsImportDTO result = restTemplate.getForObject(urlImport + idImport , InfoDetailsImportDTO.class);
        result.setSumTotal(sumTotal(result));
        return result;
    }
}
