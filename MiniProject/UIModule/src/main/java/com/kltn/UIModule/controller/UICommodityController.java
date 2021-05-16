package com.kltn.UIModule.controller;

import com.kltn.UIModule.dto.CommodityCreateDto;
import com.kltn.UIModule.dto.CommodityListDTO;
import com.kltn.UIModule.dto.TypeList;
import com.kltn.UIModule.service.UICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/commodity")
public class UICommodityController{
    @Autowired
    private UICommodityService uiCommodityService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String commodityList(Model model){
        List<CommodityListDTO> commodityListDTOList = uiCommodityService.getCommodities();
        model.addAttribute("commodityList",commodityListDTOList);
        return "commodityList";
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteCommodity(@PathVariable("id") UUID idCommodity){
        uiCommodityService.deleteCommodity(idCommodity);
        return "redirect:/commodity/list";
    }
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    public String formAddCommodity(Model model){
       List<TypeList> typeLists =  uiCommodityService.listTypeOfCommodity();
       model.addAttribute("create", new CommodityCreateDto());
       model.addAttribute("typeList",typeLists);
       return "commodityAdd";
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createCommodity(@ModelAttribute("create") CommodityCreateDto commodityCreateDto){
        uiCommodityService.createCommodity(commodityCreateDto);
        return "redirect:/commodity/list";
    }
    @RequestMapping(value = "/update/{id}" , method = RequestMethod.GET)
    public String formUpdateCommodity(@PathVariable("id") UUID idCommodity,Model model){
        List<TypeList> typeLists =  uiCommodityService.listTypeOfCommodity();
        CommodityCreateDto commodity = uiCommodityService.getCommodity(idCommodity);
        model.addAttribute("update", commodity);
        model.addAttribute("typeList",typeLists);
        return "commodityUpdate";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateCommodity(@ModelAttribute("update") CommodityCreateDto commodityCreateDto){
        uiCommodityService.updateCommodity(commodityCreateDto);
        return "redirect:/commodity/list/3";
    }


}
