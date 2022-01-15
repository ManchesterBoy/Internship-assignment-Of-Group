package com.hao.controller;


import com.hao.dto.PageResoponse;
import com.hao.entity.DeptInf;
import com.hao.entity.DocumentInf;

import com.hao.service.impl.DocumentInfServiceImpl;
import com.hao.utils.StaticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄炎俊
 * @since 2022-01-06
 */
@Controller
@ResponseBody
@RequestMapping(value = "/documentInf")
public class DocumentInfController {
    //    @GetMapping(value = "all")
//    public ArrayList<DocumentInf> selectAllDocInf(int page,int size){
//        return documentInfService.selectAllDocInf(page,size);
//    }
    @Autowired
    DocumentInfServiceImpl documentInfService;



    @GetMapping
    public PageResoponse<ArrayList<DocumentInf>> selectDocInfByTitle(
            @RequestParam(required = false) String title,
            HttpServletRequest req,
            int page,
            int size
    ){
        PageResoponse<ArrayList<DocumentInf>> arrayListPageResoponse = documentInfService.selectDocInfByTitle(title, page, size);
        for(DocumentInf documentInf : arrayListPageResoponse.getData()){
            String filename = documentInf.getFilename();
            documentInf.setFilename(StaticData.local + "/download?filename=" + filename);
        }

        return arrayListPageResoponse;
    }


    @PostMapping
    public String addDocInf(
            @RequestBody HashMap<String,Object> map
    ){
        DocumentInf documentInf = new DocumentInf();
        documentInf.setTitle(map.get("title").toString());
        documentInf.setRemark(map.get("remark").toString());
        documentInf.setUserId((Integer) map.get("userId"));
        documentInf.setFilename(map.get("filename").toString());
        if(documentInf.getTitle() == null ||
                documentInf.getFilename() == null
        ) return "error";
        return documentInfService.addDocInf(documentInf) == 1 ? "success" : "error" ;
    }

    @PutMapping
    public String updateDoclnf(
            @RequestBody(required = false) DocumentInf documentInf
    ){
        if(documentInf.getId() == null ||
                documentInf.getTitle() == null
        ) return "error";
        return documentInfService.updateDocInf(documentInf) == 1 ? "success" : "error" ;
    }

    @DeleteMapping
    public String deleteDoclnf(
            @RequestBody(required = false) ArrayList<Integer> arrayList
    ){
        int state = 0;
        for(Integer id : arrayList){
            if (documentInfService.deleteDocInf(id) != 1) {
                state++;
            }
        }
        return state == 0 ? "success" : "error" ;
    }

}

