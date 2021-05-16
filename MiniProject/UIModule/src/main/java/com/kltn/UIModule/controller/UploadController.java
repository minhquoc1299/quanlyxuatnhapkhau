package com.kltn.UIModule.controller;

import com.ctc.wstx.util.StringUtil;
import com.kltn.UIModule.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {


   /* @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@ModelAttribute("fileImage") DocumentDTO documentDTO,
                         Model model){ System.out.println(imagesFolder);
        this.doUpload(model,documentDTO);
        return null;
    }
   */
    
}
