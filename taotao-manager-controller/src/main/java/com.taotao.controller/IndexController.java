package com.taotao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by duai on 2017/2/28.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("index")
    public String index(long id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(itemService.getItemById(id));
    }
}
