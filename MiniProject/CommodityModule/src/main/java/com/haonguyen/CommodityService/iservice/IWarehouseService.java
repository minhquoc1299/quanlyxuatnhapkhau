package com.haonguyen.CommodityService.iservice;

import com.haonguyen.CommodityService.dto.CommodityInWarehouseDto;
import com.haonguyen.CommodityService.dto.WarehouseList;

import java.util.List;

public interface IWarehouseService {
    List<CommodityInWarehouseDto> checkCommodityInWarehouse();

    List<WarehouseList> getListWarehouse();

    List<WarehouseList> getListWarehouseCapacity(Double quantity);
}
