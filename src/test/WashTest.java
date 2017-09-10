package test;

import main.Beans.Data;
import main.Beans.Intel;
import main.Beans.Tag;
import main.database.databaseservece.Intelseverce;
import main.database.databaseservece.TagImp;
import main.database.databaseservece.WasherDataDao;
import main.database.dbInterface.DataTagInterface;
import main.database.dbInterface.IntelDao;
import main.database.dbInterface.WasherData;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WashTest {
    public static void main(String args[]){
        WasherData data = new WasherDataDao();
        Data data1 = new Data();

        data1.setAuthor("liyu");
        data.createData(data1);

        DataTagInterface dataTagInterface = new TagImp();
        Tag tag = new Tag();
        tag.setTagName("wahaha");
        tag.setTagCategory("name");

        try {
            dataTagInterface.createTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
//
        Tag tag1 = dataTagInterface.findByName("wahaha");
        System.out.println(tag1.getTagName());

        try {
            dataTagInterface.addTagToData(data1,tag1);
        } catch (Exception e) {
            e.printStackTrace();
        }

//
//
        try {
            List<Tag> list = dataTagInterface.getTagsOfData(data1);
            System.out.println("--------------tag name of data---------------");

            for (Tag tag2 : list){
                System.out.println(tag2.getTagName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            IntelDao intelDao = new Intelseverce();



            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -30);
            System.out.println(cal.getTime());
            List<Intel> list = intelDao.findIntelBefore(new java.sql.Date(cal.getTime().getTime()));
            List<Intel> list1 = intelDao.findIntelbetween(new java.sql.Date(cal.getTime().getTime()), new java.sql.Date(new Date().getTime()));
            System.out.println("----------------------------");
            if(list != null){
                for (Intel intel : list){
                    System.out.println(intel.getIntelID());
                }
            } else {
                System.out.println("hahhahaha");
            }


            System.out.println("----------------------------");

            if(list != null){
                for (Intel intel : list1){
                    System.out.println(intel.getIntelID());
                }
            } else {
                System.out.println("hahhahaha");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
