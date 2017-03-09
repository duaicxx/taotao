package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by duai on 2017/3/8.
 */
public interface ItemParamService {
    EUDataGridResult getItemParamList(Integer pageSize,Integer pageNo);

    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(long cid ,String paramData);

    TaotaoResult deleteItemParam(String ids);


}
