package com.haonguyen.ServiceImport.serviceimpl;

import com.haonguyen.ServiceImport.CustomErrorMessage.ReceiptImportNotFoundException;
import com.haonguyen.ServiceImport.CustomErrorMessage.SaveException;
import com.haonguyen.ServiceImport.dto.*;
import com.haonguyen.ServiceImport.mapper.DetailsImportExportMapper;
import com.haonguyen.ServiceImport.mapper.DocumentMapper;
import com.haonguyen.ServiceImport.mapper.ImportExportMapper;
import com.haonguyen.ServiceImport.repository.DetailsImportExportRepository;
import com.haonguyen.ServiceImport.repository.DocumentRepository;
import com.haonguyen.ServiceImport.repository.ImportExportRepository;
import com.mini_project.CoreModule.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IImportService implements com.haonguyen.ServiceImport.service.IImportService {

    @Autowired
    private RestTemplate restTemplate;
    private final ImportExportRepository importExportRepository;
    private final DocumentRepository documentRepository;
    private final DetailsImportExportRepository detailsImportExportRepository;
    private final ImportExportMapper importExportMapper;
    private final DetailsImportExportMapper detailsImportExportMapper;
    private final DocumentMapper documentMapper;


    public IImportService(ImportExportRepository importExportRepository, DocumentRepository documentRepository,
                          DetailsImportExportRepository detailsImportExportRepository, ImportExportMapper importExportMapper,
                          DetailsImportExportMapper detailsImportExportMapper, DocumentMapper documentMapper) {
        this.importExportRepository = importExportRepository;
        this.documentRepository = documentRepository;
        this.detailsImportExportRepository = detailsImportExportRepository;
        this.importExportMapper = importExportMapper;
        this.detailsImportExportMapper = detailsImportExportMapper;
        this.documentMapper = documentMapper;
    }

    /**
     * method save importExportEntity mapper from ImportReceiptDTO set New id to iExportEntity
     *
     * @param iExportEntity
     * @param importReceiptDTO
     * @return iExportEntity set new idImportExport
     */
    @Override
    public ImportExportEntity saveImportExportEntity(ImportExportEntity iExportEntity, ImportReceiptDTO importReceiptDTO) throws SaveException {
        try {
            ImportExportEntity importExportEntity
                    = importExportMapper.importReceiptDTOToImportExportEntity(importReceiptDTO);

            ImportExportEntity importExportEntityNew
                    = importExportRepository.save(importExportEntity);

            iExportEntity.setId(importExportEntityNew.getId());
            return iExportEntity;
        } catch (Exception exception) {
            throw new SaveException("Save Error At ImportExportEntity Please Try Again");
        }
    }

    @Override
    public List<ImportExportEntity> getAllReceipt() {
        return importExportRepository.findAll();
    }

    @Override
    public List<ImportDTO> getListImport() {
        List<ImportDTO> listImport = importExportRepository.getListImport();
        return listImport;
    }

    @Override
    public InfoDetailsImportDTO getDetailImportById(UUID idImport) {
        ImportExportEntity importEntity = importExportRepository.getOne(idImport);

        InfoDetailsImportDTO info = new InfoDetailsImportDTO();
        info.setIdImport(importEntity.getId());
        info.setDateImport(importEntity.getDate());
        info.setWarehouseName(importEntity.getWarehouseEntity().getWarehouseName());
        info.setCountryName(importEntity.getCountryEntity().getCountryName());

        List<String> urls = new ArrayList<>();
        for(DocumentEntity temp: importEntity.getDocumentEntities()){
            urls.add(temp.getImageUrl());
        }

        info.setUrlImages(urls);

        List<InfoDetailsCommodityImport> listCommodities = new ArrayList<>();
        for(DetailsImportExportEntity temp: importEntity.getDetailsImportExportEntities()){
            InfoDetailsCommodityImport commodity = new InfoDetailsCommodityImport();

            commodity.setIdCommodity(temp.getIdCommodity());
            commodity.setPrice(temp.getCommodityEntity().getPrice());
            commodity.setQuantity(temp.getQuantity());
            commodity.setTotal(temp.getTotal());

            String sourceCommodityURL = "http://COMMODITY-SERVICE/v1/api/commodity/";
            CommodityDTO resultCommodityDto = restTemplate.getForObject(sourceCommodityURL + temp.getIdCommodity(), CommodityDTO.class);

            commodity.setCommodityName(resultCommodityDto.getCommodityName());

            listCommodities.add(commodity);
        }

        info.setCommodityImportList(listCommodities);

        return info;
    }

    @Override
    public List<ImportExportEntity> searchImportExport(String key, Date date) {
        return importExportRepository.searchImportExportQueryIgnoreCase(key, date);
    }

    @Override
    public WarehouseEntity findWarehouseById(UUID id) {
        return importExportRepository.findByIdWarehouse(id);
    }

    @Override
    public CountryEntity findCountryById(UUID id) {
        return importExportRepository.findByIdCountry(id);
    }


    @Override
    public List<WarehouseEntity> findAllWarehouse() {

        return importExportRepository.findAllWarehouse();
    }

    @Override
    public List<ImportExportEntity> findAllByDate(Date date) {
        return importExportRepository.findAllByDate(date);
    }

    @Override
    public List<WarehouseCommodityEntity> findWarehouseCommodityByIdWarehouseIdCommodity(UUID idWarehouse, UUID idCommodity) {
        return importExportRepository.findWarehouseCommodityByIdWarehouseIdCommodity(idWarehouse, idCommodity);
    }

    @Override
    public List<WarehouseEntity> getWarehouseEntityList(int Max) {
        List<WarehouseEntity> warehouseEntityList = findAllWarehouse();
        List<WarehouseEntity> recommendWarehouse = new ArrayList<>();
        for (WarehouseEntity list : warehouseEntityList) {
            if (list.getCapacity() > Max)
                recommendWarehouse.add(list);
        }
        return recommendWarehouse;
    }

    @Override
    public void setInfoImportExport(CountryEntity countryEntity,
                                    WarehouseEntity warehouseEntity,
                                    ImportExportEntity iExportEntity,
                                    List<DocumentEntity> documentEntityList,
                                    List<DetailsImportExportEntity> detailsIExportEntityList) {
        iExportEntity.setCountryEntity(countryEntity);
        iExportEntity.setWarehouseEntity(warehouseEntity);
        iExportEntity.setDocumentEntities(documentEntityList);
        iExportEntity.setDetailsImportExportEntities(detailsIExportEntityList);
    }
    @Override
    public ResponseEntity saveImport(ImportCreate importCreate) {
        ImportExportEntity importEntity = importExportMapper.importCreateToImportExportEntity(importCreate);
        List<DetailsImportExportEntity> detailsImportEntity = new ArrayList<>();

        ImportExportEntity resultImportEntity = importExportRepository.save(importEntity);


        for(String info : importCreate.getListDocument()){
            DocumentEntity docEntity = new DocumentEntity();
            docEntity.setIdImportExport(resultImportEntity.getId());
            docEntity.setImageUrl(info);
            documentRepository.save(docEntity);
        }

        for(ItemReceiptDTO info : importCreate.getListCommodity()){
            DetailsImportExportEntity detailsEntity = new DetailsImportExportEntity();
            detailsEntity.setIdImportExport(resultImportEntity.getId());
            detailsEntity.setIdCommodity(info.getIdCommodity());
            detailsEntity.setQuantity(info.getQuantity());
            detailsEntity.setTotal(info.getTotal());
            detailsImportExportRepository.save(detailsEntity);
        }
        return ResponseEntity.ok().body(resultImportEntity);
    }
}
