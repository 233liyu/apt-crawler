package test;

import main.Beans.Data;
import main.Beans.Intel;
import main.Beans.Tag;
import main.Computering.DataCal.wordCloud.wordCloudHandler;
import main.Computering.MovingPart.MovingManager;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.TagImp;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.DataTagInterface;
import main.database.dbInterface.IntelTagInterface;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AntTest {
    public static void main(String args[]) throws SQLException {
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



        wordCloudHandler handler = new wordCloudHandler();
        wordCloudHandler.toJsonArray(handler.getResult());

        IntelTagInterface intelTagInterface = new TagImp();
        Intel intel = new Intel();
        intel.setIntelID("494");
        List<Tag>list = intelTagInterface.getTagsOfIntel(intel);

        for (Tag tag : list){
            System.out.println(tag.getTagName());
        }
        MovingManager manager = MovingManager.getInstance();
        manager.startThread();
    }

}
