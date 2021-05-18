package com.haonguyen.CommodityService.repository;

import com.haonguyen.CommodityService.dto.WarehouseList;
import com.mini_project.CoreModule.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IWarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.WarehouseList(w.id,w.warehouseName," +
            " w.capacity)" +
            " FROM WarehouseEntity w")
    List<WarehouseList> getListWarehouse();

    @Query(value = "SELECT new com.haonguyen.CommodityService.dto.WarehouseList(w.id,w.warehouseName," +
            " w.capacity)" +
            " FROM WarehouseEntity w" +
            " Where w.capacity >= :quantity")
    List<WarehouseList> getListWarehouseCapacity(@Param("quantity") Double quantity);
}
