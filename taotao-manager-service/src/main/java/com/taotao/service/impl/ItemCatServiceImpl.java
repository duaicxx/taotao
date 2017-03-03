package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duai on 2017/3/3.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper mapper;
    @Override
    public List<EUTreeNode> getItemCatList(long parentId) {
        TbItemCatExample itemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria =  itemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = mapper.selectByExample(itemCatExample);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : list){
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbItemCat.getId());
            euTreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            euTreeNode.setText(tbItemCat.getName());
            resultList.add(euTreeNode);
        }
        return resultList;
    }
}
