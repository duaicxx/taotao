package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by duai on 2017/2/28.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Override
    public TbItem getItemById(long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        EUDataGridResult result = new EUDataGridResult();
        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        List list =  itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc,String paramData) {
        //生成商品id
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //商品状态1-正常 2-下架 3-删除
        item.setStatus((byte)1);
        //创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setUpdated(date);
        itemDesc.setCreated(date);
        itemDesc.setItemDesc(desc);
        itemDescMapper.insert(itemDesc);
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setUpdated(date);
        itemParamItem.setParamData(paramData);
        itemParamItem.setCreated(date);
        itemParamItem.setItemId(itemId);
        tbItemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();
    }

    @Override
    public String getItemParamHtml(long id) {
        TbItemParamItemExample tbItemParamItemExample= new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria  = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
        if (tbItemParamItems==null || tbItemParamItems.size()<=0 ){
            return "";
        }
        List<Map> list = JsonUtils.jsonToList(tbItemParamItems.get(0).getParamData(),Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table border= '1' width='100%'>\n");
        sb.append("	<tbody>\n");

        for(Map map:list){
            sb.append("		<tr colsan=\"2\">"+map.get("group")+"</tr>\n" );
            List<Map> mapParams = (List<Map> )map.get("params");
            for(Map mapParam:mapParams){
                sb.append("		<tr>\n");
                sb.append("			<td>"+mapParam.get("k")+"</td>\n");
                sb.append("			<td>"+mapParam.get("v")+"</td>\n" );
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
