package com.kltn.UIModule.controller;

import com.kltn.UIModule.dto.*;
import com.kltn.UIModule.service.CoreService;
import com.kltn.UIModule.service.UIImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

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
        List<WarehouseList> warehouseList = uiImportService.getWarehouseCapacity();
        List<CountryList> countryList = uiImportService.getCountryList();
        List<FormCommodityImport> formCommodityImport = uiImportService.getCommodityImportList();

        model.addAttribute("warehouseList",warehouseList);
        model.addAttribute("countryList",countryList);
        model.addAttribute("formCommodityImport", formCommodityImport);

        model.addAttribute("formImportCreate", new FormImportCreate());
        model.addAttribute("commodity",new CommodityDTO());

        return "importAdd";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("importCreateDTO") FormImportCreate formImportCreate, Model model){
        uiImportService.add(formImportCreate);
        return "redirect:/import/list";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") UUID idImport, Model model) {
        InfoDetailsImportDTO infoDetailsImportDTO = uiImportService.getDetailsByIdImport(idImport);
        model.addAttribute("infoDetailsImport",infoDetailsImportDTO);
        return "importDetails";
    }

    @RequestMapping(value = "/getCommodity" , method = RequestMethod.GET)
    public String modal(@ModelAttribute("commodity") CommodityDTO commodityDTO){
        uiImportService.addCommodityImportList(commodityDTO);
        return "redirect:/import/add";
    }
    @RequestMapping(value = "/deleteCommodity/{id}" , method = RequestMethod.GET)
    public String modal(@PathVariable("id") UUID idCommodity){
        uiImportService.deleteCommodityImportList(idCommodity);
        return "redirect:/import/add";
    }


}
