package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by duai on 2017/3/8.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public EUDataGridResult getItemParamList(Integer pageSize, Integer pageNo) {
        EUDataGridResult result = new EUDataGridResult();
        PageHelper.startPage(pageNo,pageSize);
        TbItemParamExample example = new TbItemParamExample();
        List<Map<String,String>> list =  tbItemParamMapper.selectParamItemByExample(example);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample paramExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria =  paramExample.createCriteria().andItemCatIdEqualTo(cid);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(paramExample);
        if (list != null && list.size()>0){
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }
}
