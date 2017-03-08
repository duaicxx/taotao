package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by duai on 2017/3/3.
 */
@RequestMapping("/item/cat")
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parendId){

        return  itemCatService.getItemCatList(parendId);
    }
  /*  @Test
    public void upload(){
        try {
            ClientGlobal.init("E:\\ideaPorject\\taotao\\taotao-manager-controller\\src\\main\\resources\\resource\\client.conf");
            TrackerClient client = new TrackerClient();
            TrackerServer server = client.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(server,storageServer);
            String [] result = storageClient.upload_appender_file("D:\\hexo\\hexo\\public\\images\\Monkey-King-Hero-is-Back-fight-dragon_1920x1440.jpg","jpg",null);
            for (String str : result){
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void uploadClient(){
        try {
            FastDFSClient client = new FastDFSClient("E:\\ideaPorject\\taotao\\taotao-manager-controller\\src\\main\\resources\\resource\\client.conf");
            String path =client.uploadFile("D:\\hexo\\hexo\\public\\images\\Monkey-King-Hero-is-Back-fight-dragon_1920x1440.jpg","jpg");
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
