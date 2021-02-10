package com.controller;

import com.controller.service.kingbase.KingbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class KingbaseController {

    @Autowired
    private KingbaseService kingbaseService;


    @RequestMapping(value = "/testKingbase")
    public String testPgsql(){
        System.out.println("this is java testKingbase");
        try {
            kingbaseService.runCRUD();
        } catch (Exception e) {
            System.out.println("current is exception"+e);
        }
        return "current java is testKingbase";
    }
}
