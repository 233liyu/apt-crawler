package test;


import main.Beans.Intel;
import main.Beans.Tag;
import main.Computering.MovingPart.MovingManager;
import main.Computering.MovingPart.Tools.AntTool;
import main.database.databaseservece.Intelseverce;
import main.database.databaseservece.TagImp;
import main.database.dbInterface.DataTagInterface;
import main.database.dbInterface.IntelDao;
import main.database.dbInterface.IntelTagInterface;
import main.servlet.tool.JsonUtil;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class AntTest {
    public static void main(String args[]) throws Exception {
//        DataInterface dao = new Dataservece();
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.DATE, -2);
//        System.out.println(cal.getTime());
//        List<Data> a = dao.getDataLimitBeforeDate(cal.getTime(),10);
//
//        for (Data intel : a){
//            System.out.println(intel.getDataID());
//        }

//        DataTagInterface dataTagInterface = new TagImp();
//        List<Tag> list = dataTagInterface.getAllTags();
//        for (Tag tag : list){
//            System.out.println(tag.getTagName());
////            System.out.println(new TagImp().findByID(Integer.parseInt(tag.getTagID())));
//            DataInterface dataInterface = new Dataservece();
////            List<Data> dataList = dataInterface.getDataByTag(tag.getTagName());
//        }


//
//        wordCloudHandler handler = new wordCloudHandler();
//        wordCloudHandler.toJsonArray(handler.getResult());

//        IntelTagInterface intelTagInterface = new TagImp();
//        Intel intel = new Intel();
//        intel.setIntelID("494");
//        List<Tag>list = intelTagInterface.getTagsOfIntel(intel);
//
//        for (Tag tag : list){
//            System.out.println(tag.getTagName());
//        }


//        IntelDao dao = new Intelseverce();
//        List<Intel> list = dao.findIntelBefore(new Date(0));
//        System.out.println(list.size());
//
//
//        IntelTagInterface intelTagInterface = new TagImp();
//        DataTagInterface dataTagInterface = new TagImp();
//        for (Intel intel : list){
////            List<Tag> list1 = intelTagInterface.getTagsOfIntel(intel);
//            AntTool.AddToData(intel);
//            System.out.println(intel.getIntelID());
//        }

        MovingManager manager = MovingManager.getInstance();
        manager.startThread();

//        Intel data = new Intelseverce().findIntelByID(503);
//        Data data1 = new Data();
//        data1.setAuthor("李瑜");
//        data.setContent("");
//        System.out.println(data.Content);
//        System.out.println(new String(data.Content.getBytes(),"utf-8"));
//        AntTool.AddToData(data);
    }



}
