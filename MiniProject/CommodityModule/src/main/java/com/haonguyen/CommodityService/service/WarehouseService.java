package com.haonguyen.CommodityService.service;

import com.haonguyen.CommodityService.dto.CommodityInWarehouseDto;
import com.haonguyen.CommodityService.dto.TypeList;
import com.haonguyen.CommodityService.dto.WarehouseList;
import com.haonguyen.CommodityService.iservice.IWarehouseService;
import com.haonguyen.CommodityService.mapper.ICommodityMapper;
import com.haonguyen.CommodityService.repository.ICommodityRepository;
import com.haonguyen.CommodityService.repository.IWarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService implements IWarehouseService {
    private final ICommodityRepository iCommodityRepository;
    private final IWarehouseRepository iWarehouseRepository;
    private final ICommodityMapper iCommodityMapper;

    public WarehouseService(ICommodityRepository iCommodityRepository,
                            IWarehouseRepository iWarehouseRepository,
                            ICommodityMapper iCommodityMapper) {
        this.iCommodityRepository = iCommodityRepository;
        this.iWarehouseRepository = iWarehouseRepository;
        this.iCommodityMapper = iCommodityMapper;
    }

    @Override
    public List<CommodityInWarehouseDto> checkCommodityInWarehouse() {
        List<CommodityInWarehouseDto> commodityInWarehouseDtos = iCommodityRepository.CommodityInWarehouse();
        return commodityInWarehouseDtos;
    }

    @Override
    public List<WarehouseList> getListWarehouse() {
        List<WarehouseList> typeLists = iWarehouseRepository.getListWarehouse();
        return typeLists;
    }

    @Override
    public List<WarehouseList> getListWarehouseCapacity(Double quantity) {
        List<WarehouseList> warehouseCapacity = iWarehouseRepository.getListWarehouseCapacity(quantity);
        return warehouseCapacity;
    }
}
