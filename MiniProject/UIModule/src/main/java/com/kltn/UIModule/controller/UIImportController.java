package com.kltn.UIModule.controller;

import com.kltn.UIModule.dto.*;
import com.kltn.UIModule.service.CoreService;
import com.kltn.UIModule.service.UIImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/import")
public class UIImportController {
    @Autowired
    private UIImportService uiImportService;
    @Autowired
    private CoreService coreService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<ImportDTO> importList = uiImportService.getListImport();
        model.addAttribute("importList", importList);
        return "importList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        List<WarehouseList> warehouseList = uiImportService.getWarehouses();
        List<CountryList> countryList = uiImportService.getCountryList();
        List<CommodityListDTO> commodityList = uiImportService.getCommodityList();
        model.addAttribute("warehouseList",warehouseList);
        model.addAttribute("countryList",countryList);
        model.addAttribute("commodityList",commodityList);
        model.addAttribute("fileImage", new DocumentDTO());
        return "importAdd";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(Model model) {
        /*List<CommodityListDTO> commodityListDTOList = uiCommodityService.getCommodities();
        model.addAttribute("commodityList",commodityListDTOList);*/
        return "importDetails";
    }
    @RequestMapping(value = "/modal" , method = RequestMethod.GET)
    public String modal(Model model){
        return "redirect:/import/add";
    }

}
