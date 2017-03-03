package com.taotao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by duai on 2017-03-02.
 */
@Controller
public class PageController {
    @Autowired
    private ItemService itemService;
    /**
     * 打开首页
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemPage(Integer page, Integer rows){
         return  itemService.getItemList(page,rows);
    }

}
