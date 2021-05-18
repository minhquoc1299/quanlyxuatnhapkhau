package com.haonguyen.ServiceImport.service;


import com.haonguyen.ServiceImport.CustomErrorMessage.ReceiptImportNotFoundException;
import com.haonguyen.ServiceImport.CustomErrorMessage.SaveException;
import com.haonguyen.ServiceImport.dto.ImportCreate;
import com.haonguyen.ServiceImport.dto.ImportDTO;
import com.haonguyen.ServiceImport.dto.ImportReceiptDTO;
import com.haonguyen.ServiceImport.dto.InfoDetailsImportDTO;
import com.mini_project.CoreModule.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public interface IImportService {

    ImportExportEntity saveImportExportEntity(ImportExportEntity iExportEntity, ImportReceiptDTO importReceiptDTO) throws SaveException;

    List<ImportExportEntity> getAllReceipt();


    List<ImportExportEntity> searchImportExport(String key, Date date);

    WarehouseEntity findWarehouseById(UUID id);

    CountryEntity findCountryById(UUID id);

    List<WarehouseEntity> findAllWarehouse();

    List<ImportExportEntity> findAllByDate(Date date);

    List<WarehouseCommodityEntity> findWarehouseCommodityByIdWarehouseIdCommodity(UUID idWarehouse, UUID idCommodity);

    List<WarehouseEntity> getWarehouseEntityList(int Max);

    void setInfoImportExport(CountryEntity countryEntity, WarehouseEntity warehouseEntity, ImportExportEntity iExportEntity, List<DocumentEntity> documentEntityList, List<DetailsImportExportEntity> detailsIExportEntityList);

    List<ImportDTO> getListImport();

    ResponseEntity saveImport(ImportCreate importCreate);

    InfoDetailsImportDTO getDetailImportById(UUID idImport);
}
