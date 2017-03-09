package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by duai on 2017/3/8.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    ItemParamService itemParamService;
    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemParamList(Integer page, Integer rows){
        return itemParamService.getItemParamList(rows,page);
    }
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult getItemParamByCatId(@PathVariable long cid){
        return itemParamService.getItemParamByCid(cid);
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult save(@PathVariable("cid") long cid,String paramData){
        return itemParamService.insertItemParam(cid,paramData);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult delete(String ids){
        return itemParamService.deleteItemParam(ids);
    }
}
