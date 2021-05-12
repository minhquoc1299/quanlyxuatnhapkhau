package com.kltn.UIModule.controller;

import com.kltn.UIModule.dto.CommodityCreateDto;
import com.kltn.UIModule.dto.CommodityListDTO;
import com.kltn.UIModule.service.UIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/v1/")
public class UIController {
    @Autowired
    private UIService uiService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/commodity",method = RequestMethod.GET)
    public String commodityList(Model model){
        List<CommodityListDTO> commodityListDTOList = uiService.getCommodities();
        model.addAttribute("commodityList",commodityListDTOList);
        return "commodityList";
    }

    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
    public String deleteCommodity(@PathVariable("id") UUID idCommodity){
        uiService.deleteCommodity(idCommodity);
        return "commodityList";
    }




}
