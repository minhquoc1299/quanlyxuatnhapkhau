package com.haonguyen.ServiceImport.controller;

import com.haonguyen.ServiceImport.CustomErrorMessage.CommodityException;
import com.haonguyen.ServiceImport.CustomErrorMessage.GlobalRestExceptionHandler;
import com.haonguyen.ServiceImport.CustomErrorMessage.ReceiptImportNotFoundException;
import com.haonguyen.ServiceImport.CustomErrorMessage.SaveException;
import com.haonguyen.ServiceImport.dto.ExcelReceiptImportDTO;
import com.haonguyen.ServiceImport.dto.ImportDTO;
import com.haonguyen.ServiceImport.dto.ImportReceiptDTO;
import com.haonguyen.ServiceImport.dto.KeySearchDTO;
import com.haonguyen.ServiceImport.mapper.ExcelReceiptImportMapper;
import com.haonguyen.ServiceImport.service.IImportService;
import com.haonguyen.ServiceImport.service.ReceiptService;
import com.mini_project.CoreModule.entity.ImportExportEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/import")

public class ImportController {

    private final IImportService iImportService;
    private final ReceiptService receiptService;
    private final GlobalRestExceptionHandler globalRestExceptionHandler;
    private final ExcelReceiptImportMapper excelReceiptImportMapper;

    public ImportController(IImportService iImportService, ReceiptService receiptService,
                            GlobalRestExceptionHandler globalRestExceptionHandler, ExcelReceiptImportMapper excelReceiptImportMapper) {
        this.iImportService = iImportService;
        this.receiptService = receiptService;
        this.globalRestExceptionHandler = globalRestExceptionHandler;
        this.excelReceiptImportMapper = excelReceiptImportMapper;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ImportReceiptDTO importReceiptDTO) throws SaveException, CommodityException {
        return receiptService.addImport(importReceiptDTO);
    }

    @GetMapping("/listdetails")
    public List<ImportExportEntity> getListDetails() {
        List<ImportExportEntity> iExportEntityList = iImportService.getAllReceipt();
        return iExportEntityList;
    }
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public List<ImportDTO> getListImport(){
        List<ImportDTO> importList = iImportService.getListImport();
        return importList;
    }

    @PostMapping("/search")
    public List<ImportExportEntity> search(@RequestBody KeySearchDTO keySearchDTO) throws Exception {
        List<ImportExportEntity> iExportEntityList = receiptService.searchReceiptImportExport(keySearchDTO);
        return iExportEntityList;
    }

    @GetMapping("/{id}")
    public ResponseEntity getReceiptById(@PathVariable(name = "idReceipt") String idReceipt) throws ReceiptImportNotFoundException {

        ImportExportEntity importExportEntity = iImportService.getByIdImportExport(UUID.fromString(idReceipt));
        return ResponseEntity.ok().body(importExportEntity);
    }

    @GetMapping("/excel/{id}")
    public ExcelReceiptImportDTO getExcel(@PathVariable(name = "idReceipt") String idReceipt) throws ReceiptImportNotFoundException {
        ImportExportEntity importExportEntity = iImportService.getByIdImportExport(UUID.fromString(idReceipt));
        ExcelReceiptImportDTO excelReceiptImportDTO = excelReceiptImportMapper.toExcelReceiptImportDTO(importExportEntity);
        return excelReceiptImportDTO;
    }


}
