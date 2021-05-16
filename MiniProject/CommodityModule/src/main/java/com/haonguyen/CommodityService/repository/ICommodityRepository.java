package com.haonguyen.CommodityService.repository;


import com.haonguyen.CommodityService.dto.*;
import com.mini_project.CoreModule.entity.CommodityEntity;
import com.mini_project.CoreModule.entity.WarehouseCommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ICommodityRepository extends JpaRepository<CommodityEntity, UUID> {
    @Query(value = "SELECT c FROM CommodityEntity c WHERE c.idTypeOfCommodity=?1")
    List<CommodityEntity> findCommodityByIdTypeOfCommodity(UUID idTypeOfCommodity);

    //    tim kiem da dieu kien
    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.CommoditySearchDto(c.commodityName,c.description,c.price,c.unit,c.idTypeOfCommodity) " +
            "FROM CommodityEntity c " +
            "INNER JOIN TypeOfCommodityEntity tc ON c.idTypeOfCommodity=tc.id " +
            "WHERE concat(c.commodityName,' ',tc.typeName,' ',c.price,' ') like trim('%',:key, '%') ")
    List<CommoditySearchDto> searchCommodity(String key);

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.CommoditySearchDto(c.commodityName,c.description,c.price,c.unit,c.idTypeOfCommodity) " +
            "from CommodityEntity c ")
    List<CommoditySearchDto> findAllSearchCommodity();
//

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.CommodityInWarehouseDto(co.commodityName,wa.warehouseName,wc.inventoryNumber) " +
            "from WarehouseCommodityEntity wc " +
            "INNER JOIN CommodityEntity co ON wc.idCommodity = co.id " +
            "INNER JOIN WarehouseEntity wa ON wc.idWarehouse = wa.id " +
            "WHERE wc.inventoryNumber > 0 " +
            "order by wa.warehouseName asc")
    List<CommodityInWarehouseDto> CommodityInWarehouse();

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.TypeAndTaxCommodityAPI(" +
            " ty.id,ty.typeName," +
            " co.id,co.commodityName,co.price,co.unit," +
            " tax.id,tax.taxBracketName,tax.coefficient) " +
            " FROM CommodityEntity co" +
            " INNER JOIN TypeOfCommodityEntity ty ON co.idTypeOfCommodity = ty.id" +
            " INNER JOIN TaxBracketEntity tax ON tax.id = ty.idTaxBracket" +
            " WHERE co.id = :idCommodity")
    TypeAndTaxCommodityAPI getTypeTaxCommodity(@Param(value = "idCommodity") UUID idCommodity);

    @Query(value = "SELECT w FROM WarehouseCommodityEntity w " +
            "WHERE w.idCommodity = :idCommodity")
    WarehouseCommodityEntity checkCommodityInWarehouseById(@Param("idCommodity") UUID idCommodity);

    @Query(value = "SELECT e FROM CommodityEntity e " +
            " WHERE e.id = :idCommodity ")
    CommodityEntity checkIdCommodity(@Param("idCommodity") UUID idCommodity);

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.CommodityListDTO(como.id," +
            " tyc.typeName," +
            " como.commodityName," +
            " como.price," +
            " como.unit," +
            " wc.inventoryNumber)" +
            " FROM CommodityEntity como" +
            " LEFT JOIN WarehouseCommodityEntity wc ON como.id = wc.idCommodity" +
            " INNER JOIN TypeOfCommodityEntity tyc ON como.idTypeOfCommodity = tyc.id")
    List<CommodityListDTO> getListCommodity();

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.TypeList(tyc.id,tyc.typeName)" +
            " FROM TypeOfCommodityEntity tyc")
    List<TypeList> getListType();

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.CountryList(cou.id,cou.countryName,cou.tax," +
            " cou.transportFee)" +
            " FROM CountryEntity cou")
    List<CountryList> getListCountry();
}
