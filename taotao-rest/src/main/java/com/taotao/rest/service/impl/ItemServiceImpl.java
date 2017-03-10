package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemResult;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duai on 2017/3/10.
 */
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public ItemResult getItemResult() {
        ItemResult itemResult = new ItemResult();
        itemResult.setData(getItemCatList(0));
        return itemResult;
    }
    private List getItemCatList(long id){
        List result = new ArrayList<>();
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        com.taotao.pojo.TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample) ;
        for (TbItemCat itemCat : list ){
           if (itemCat.getIsParent()){
               CatNode catNode = new CatNode();
               catNode.setUrl("/products/"+itemCat.getName()+".html");
               if (itemCat.getParentId()==0){
                   catNode.setName("节点"+itemCat.getName());
               }
               catNode.setItems(getItemCatList(itemCat.getId()));
               result.add(catNode);
           }else{
               result.add("不是叶子节点"+itemCat.getName());
            }
        }
        return result;
    }
}
