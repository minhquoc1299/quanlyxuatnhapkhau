package com.haonguyen.ExportService.service;


import com.haonguyen.ExportService.dto.*;
import com.haonguyen.ExportService.dto.excel.ExcelDetailsExportDTO;
import com.haonguyen.ExportService.dto.excel.ExcelDocumentDTO;
import com.haonguyen.ExportService.dto.excel.ExcelExportDTO;
import com.haonguyen.ExportService.dto.excel.ReturnInfoExportAPI;
import com.haonguyen.ExportService.mapper.IImportExportMapper;
import com.haonguyen.ExportService.repository.IImportExportRepository;
import com.mini_project.CoreModule.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImportExportService implements IImportExportService {

    private final IImportExportRepository iImportExportRepository;
    private final IImportExportMapper iImportExportMapper;
    private final IDocumentService iDocumentService;
    private final RestTemplate restTemplate;
    private final IDetailsImportExportService iDetailsImportExportService;

    public ImportExportService(IImportExportRepository iImportExportRepository, IImportExportMapper iImportExportMapper, IDocumentService iDocumentService, RestTemplate restTemplate, IDetailsImportExportService iDetailsImportExportService) {
        this.iImportExportRepository = iImportExportRepository;
        this.iImportExportMapper = iImportExportMapper;
        this.iDocumentService = iDocumentService;
        this.restTemplate = restTemplate;
        this.iDetailsImportExportService = iDetailsImportExportService;
    }

    @Override
    public List<ImportExportEntity> getExport() {
        return iImportExportRepository.findAll();
    }

    @Override
    public List<ImportExportEntity> getExportByCountryId(UUID countryId) {
        return iImportExportRepository.getExportByCountry(countryId);
    }

    @Override
    public ImportExportEntity updateExport(ImportExportEntity importExportEntity) {
        return iImportExportRepository.save(importExportEntity);
    }

    @Override
    public ExportFindByIdDTO findByIdExport(UUID idExport) {
        return iImportExportMapper.toExportFindByIdDTO(iImportExportRepository.getById(idExport));
    }

    @Override
    public ImportExportEntity findByIdExportExcel(UUID idExport) {
        return iImportExportRepository.getById(idExport);
    }

    @Override
    public List<ExcelExportDTO> findAllExport() {
        return iImportExportMapper.toExcelExportDTOs(iImportExportRepository.findAll());
    }

    /**
     * Save v??o b???ng ImportExportEntity
     * @param importExportEntity
     * @return ExportDTO ch???a d??? li???u map t??? ImportExportEntity
     */
    public ExportDTO addExport(ImportExportEntity importExportEntity){
        return iImportExportMapper.toExportDTO(iImportExportRepository.save(importExportEntity));
    }

    /**
     * L???y th??ng tin li??n quan ?????n h??ng h??a th??ng qua API g???i ?????n service CommodityModule
     * @param idCommodity
     * @return
     */
    public ApiInfoCommodity getApiAllCommodityInfo(UUID idCommodity){
       return restTemplate
               .getForObject("http://localhost:9002/v1/api/commodity/getTypeTax/" + idCommodity
               , ApiInfoCommodity.class);
    }

    /**
     * T??ch d??? li???u t??? form nh???p ng?????i d??ng t??nh to??n v?? ?????y v??o csdl
     * @param insertDataExportDTO
     * @return return l???i all th??ng tin ra postman
     */
    @Override
    public ShowAddExportDTO infoExport(InsertDataExportDTO insertDataExportDTO) {

        ExportDTO exportDTO = addExport(ImportExportEntity
                .builder()
                .idCountry(insertDataExportDTO.getCountryId())
                .idWarehouse(insertDataExportDTO.getWarehouseId())
                .date(insertDataExportDTO.getDate())
                .type(0)
                .build());

        insertDataExportDTO.setId(exportDTO.getId());
        List<DocumentDTO> documentDTOList = iDocumentService.infoDocument(insertDataExportDTO);

        List<DetailsExportDTO> detailsExportDTOList = iDetailsImportExportService.infoDetailsExport(insertDataExportDTO);
        double sumTotal = 0.0;
        for(DetailsExportDTO temp: detailsExportDTOList){
            sumTotal = sumTotal + temp.getTotal();
        }

        WarehouseEntity warehouseEntity = iImportExportRepository.getWarehouseByID(insertDataExportDTO.getWarehouseId());
        CountryEntity countryEntity = iImportExportRepository.getCountryById(insertDataExportDTO.getCountryId());
        Double transportationCosts = 1000000.0;

        return ShowAddExportDTO.builder()
                        .idImportExport(exportDTO.getId())
                        .idWarehouse(warehouseEntity.getId())
                        .idCountry(countryEntity.getId())
                        .warehouseName(warehouseEntity.getWarehouseName())
                        .countryName(countryEntity.getCountryName())
                        .documentDTOList(documentDTOList)
                        .detailsExportDTOList(detailsExportDTOList)
                        .transportationCosts(transportationCosts)
                        .subTotal(sumTotal + (sumTotal * (countryEntity.getTax()%100))+ transportationCosts)
                        .build();
    }

    /**
     * Method d??ng ????? l???y th??ng tin xu???t ra Excel th??ng qua API
     * @param monthAndYear
     * @return Th??ng tin cho service Statement
     */
    @Override
    public List<ReturnInfoExportAPI> getExcel(String monthAndYear) {

        String[] temp = monthAndYear.split("&");
        Integer month = Integer.parseInt(temp[0]);
        Integer year = Integer.parseInt(temp[1]);

        List<ImportExportEntity> importExportEntityList
                = iImportExportRepository.getByMonthAndYear(month,year);

        List<ReturnInfoExportAPI> returnInfoExportAPIS = new ArrayList<>();
        List<ExcelDocumentDTO> excelDocumentDTOS;
        List<ExcelDetailsExportDTO> excelDetailsExportDTOS;
        for(ImportExportEntity tempFor: importExportEntityList){

            ExcelExportDTO excelExportDTO = iImportExportMapper.toExcelExportDTO(tempFor);

            excelDocumentDTOS = iDocumentService.findByIdImportExport(tempFor.getId());

            excelDetailsExportDTOS =  iDetailsImportExportService.findByIdImportExport(tempFor.getId());

            returnInfoExportAPIS.add(
                    ReturnInfoExportAPI
                            .builder()
                            .excelExportDTO(excelExportDTO)
                            .excelDetailsExportDTOS(excelDetailsExportDTOS)
                            .excelDocumentDTOS(excelDocumentDTOS)
                            .build());
        }
        return returnInfoExportAPIS;
    }
}
