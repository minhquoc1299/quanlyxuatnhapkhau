package com.haonguyen.ServiceImport.serviceimpl;

import com.haonguyen.ServiceImport.CustomErrorMessage.CommodityException;
import com.haonguyen.ServiceImport.CustomErrorMessage.SaveException;
import com.haonguyen.ServiceImport.dto.*;
import com.haonguyen.ServiceImport.mapper.*;
import com.haonguyen.ServiceImport.service.*;
import com.mini_project.CoreModule.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final IImportService IImportService;
    private final WarehouseCommodityService warehouseCommodityService;
    private final DetailsImportExportService detailsImportExportService;
    private final DocumentService documentService;

    private final RestTemplate restTemplate;

    private final ItemReceiptMapper itemReceiptMapper;
    private final ImportExportMapper importExportMapper;
    private final DetailsImportExportMapper detailsImportExportMapper;
    private final WarehouseCommodityMapper warehouseCommodityMapper;
    private final CommodityDTOMapper commodityDTOMapper;

    public ReceiptServiceImpl(IImportService IImportService, WarehouseCommodityService warehouseCommodityService,
                              DetailsImportExportService detailsImportExportService,
                              DocumentService documentService, RestTemplate restTemplate, ItemReceiptMapper itemReceiptMapper,
                              ImportExportMapper importExportMapper, DetailsImportExportMapper detailsImportExportMapper,
                              WarehouseCommodityMapper warehouseCommodityMapper, CommodityDTOMapper commodityDTOMapper) {
        this.IImportService = IImportService;
        this.warehouseCommodityService = warehouseCommodityService;
        this.detailsImportExportService = detailsImportExportService;
        this.documentService = documentService;
        this.restTemplate = restTemplate;
        this.itemReceiptMapper = itemReceiptMapper;
        this.importExportMapper = importExportMapper;
        this.detailsImportExportMapper = detailsImportExportMapper;
        this.warehouseCommodityMapper = warehouseCommodityMapper;
        this.commodityDTOMapper = commodityDTOMapper;
    }

    /**
     * method to save Receipt Import
     *
     * @param importCreate
     * @return iExportEntityNew if Max(amount in receipt) < warehouse capacity
     * recommendWarehouse if Max > warehouse capacity
     */
    @Override
    public ResponseEntity addImport(ImportCreate importCreate){
        if (importCreate == null) {
            return null;
        }
        return null;


    }

    /**
     * method get CommodityEntity from CommodityModule
     *
     * @param listItemDto
     * @return commodityEntity
     */
    @Override
    public CommodityEntity getCommodityEntityFromCommodityModule(ItemReceiptDTO listItemDto) throws CommodityException {
        try {
            String sourceCommodityURL = "http://COMMODITY-SERVICE/v1/api/commodity/";
            CommodityDTO resultCommodityDto = restTemplate.getForObject(sourceCommodityURL + listItemDto.getIdCommodity(), CommodityDTO.class);
            CommodityEntity commodityEntity = commodityDTOMapper.toCommodityEntity(resultCommodityDto);
            return commodityEntity;
        } catch (Exception exception) {
            throw new CommodityException("No response from CommodityService please try again later");
        }
    }

    /**
     * method search Receipt ImportExportEntity
     *
     * @param keySearchDTO
     * @return iExportEntityList
     * @throws ParseException
     */
    @Override
    public List<ImportExportEntity> searchReceiptImportExport(KeySearchDTO keySearchDTO) throws Exception {
        try {
            List<ImportExportEntity> iExportEntityList = new ArrayList<>();
            if (keySearchDTO.getDate() != null) {
                if (keySearchDTO.getKey() == null) {
                    iExportEntityList.addAll(IImportService.findAllByDate(keySearchDTO.getDate()));
                } else {
                    iExportEntityList.addAll(IImportService.searchImportExport(keySearchDTO.getKey(), keySearchDTO.getDate()));
                }
            } else {
                iExportEntityList.addAll(IImportService.searchImportExport(keySearchDTO.getKey(), keySearchDTO.getDate()));
            }
            return iExportEntityList;
        } catch (Exception exception) {
            throw new Exception();
        }
    }
}