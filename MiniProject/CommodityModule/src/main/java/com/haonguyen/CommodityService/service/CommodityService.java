package com.haonguyen.CommodityService.service;

import com.haonguyen.CommodityService.apiExceptionHandler.SaveException;
import com.haonguyen.CommodityService.dto.*;
import com.haonguyen.CommodityService.iservice.ICommodityService;
import com.haonguyen.CommodityService.mapper.ICommodityMapper;
import com.haonguyen.CommodityService.mapper.ICommodityMapperImpl;
import com.haonguyen.CommodityService.repository.ICommodityRepository;
import com.mini_project.CoreModule.entity.CommodityEntity;
import com.mini_project.CoreModule.entity.WarehouseCommodityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.UUID;


@Service
public class CommodityService implements ICommodityService {
    private final ICommodityRepository iCommodityRepository;
    private final ICommodityMapper iCommodityMapper;

    public CommodityService(ICommodityRepository iCommodityRepository, ICommodityMapper iCommodityMapper) {
        this.iCommodityRepository = iCommodityRepository;
        this.iCommodityMapper = iCommodityMapper;
    }


    /**
     * xoa hang theo dieu kien,ton kho,khong co trong phieu nhap
     */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void deleteCommodity(UUID id){
        WarehouseCommodityEntity temp = iCommodityRepository.checkCommodityInWarehouseById(id);
        if(temp != null){
            if(temp.getInventoryNumber() == 0)
                iCommodityRepository.deleteById(id);
        }else{
            iCommodityRepository.deleteById(id);
        }

        //String checkIdCommodityURL = "http://localhost:8112/v1/api/export/checkIdCommodity/";
       // String sourceCheck = restTemplate.getForObject(checkIdCommodityURL + id, String.class);

    }

    /**
     * tim kiem da dieu kien
     */
    @Override
    public List<CommoditySearchDto> searchCommodity(String key) throws SaveException {
        if (key == null) {

            List<CommoditySearchDto> commoditySearchDtos = iCommodityRepository.findAllSearchCommodity();
            return commoditySearchDtos;
        } else {
            List<CommoditySearchDto> commoditySearchDtos = iCommodityRepository.searchCommodity(key);
            if (commoditySearchDtos.size() == 0)
                throw new SaveException("Không tìm thấy hàng cùng loại");
            return commoditySearchDtos;
        }
    }

    /**
     * Them hang
     */
    @Override
    public CommodityCreateDto addCommodity(CommodityCreateDto commodityCreateDto) {
        if (commodityCreateDto == null) {
            return null;
        }

        CommodityEntity commodityEntity = iCommodityMapper.fromCreateToEntity(commodityCreateDto);
        iCommodityRepository.saveAndFlush(commodityEntity);
        CommodityCreateDto commodityCreateDto1 = iCommodityMapper.toCreateDto(commodityEntity);
        return commodityCreateDto1;
    }

    /**
     * Cap nhat hang
     */
    @Override
    public CommodityUpdateDto updateCommodity(CommodityUpdateDto commodityUpdateDto, UUID idCommodity){
        if (commodityUpdateDto == null) {
            return null;
        }
        commodityUpdateDto.setId(idCommodity);
        CommodityEntity commodityEntities = iCommodityRepository.checkIdCommodity(idCommodity);
        CommodityEntity commodityEntity = iCommodityMapper.fromUpdateToEntity(commodityUpdateDto);
        iCommodityRepository.save(commodityEntity);
        CommodityUpdateDto commodityUpdateDto1 = iCommodityMapper.toUpdateDto(commodityEntity);
        return commodityUpdateDto1;

    }

    /**
     * Lay thong tin theo loai hang
     */
    @Override
    public List<TypeOfCommodityDto> findCommodityByIdTypeOfCommodity(UUID idTypeOfCommodity) {
        List<TypeOfCommodityDto> typeOfCommodityDto = iCommodityMapper
                .toFindSectorId(iCommodityRepository.findCommodityByIdTypeOfCommodity(idTypeOfCommodity));
        return typeOfCommodityDto;
        /**
         * lay thong tin thue
         */
    }

    @Override
    public TypeAndTaxCommodityAPI getTypeTaxCommodity(UUID idCommodity) throws Exception {

        TypeAndTaxCommodityAPI typeAndTaxCommodityAPI = null;
        if (idCommodity != null) {
            typeAndTaxCommodityAPI = iCommodityRepository.getTypeTaxCommodity(idCommodity);
        }
        if (typeAndTaxCommodityAPI == null) {
            throw new Exception("khong");
        }
        return typeAndTaxCommodityAPI;

    }

    /**
     * Lay thong tin theo id
     */
    @Override
    public CommodityCreateDto CommodityById(UUID id) {

        CommodityCreateDto commodityCreateDto = iCommodityMapper.toCreateDto(iCommodityRepository.findById(id).get());
        iCommodityRepository.findById(id).get();
        return commodityCreateDto;
    }

    @Override
    public List<CommodityListDTO> getListCommodity() {
        List<CommodityListDTO> listDTOS = iCommodityRepository.getListCommodity();
        return listDTOS;
    }

    @Override
    public List<TypeList> getListType() {
        List<TypeList> typeLists = iCommodityRepository.getListType();
        return typeLists;
    }

    @Override
    public List<CountryList> getListCountry() {
        List<CountryList> countryLists = iCommodityRepository.getListCountry();
        return countryLists;
    }
}
