package test;

import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseservece.TagImp;
import main.database.databaseservece.WasherDataDao;
import main.database.dbInterface.DataTagInterface;
import main.database.dbInterface.WasherData;

import java.sql.SQLException;
import java.util.List;

public class WashTest {
    public static void main(String args[]) {
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

        Tag tag1 = dataTagInterface.findByName("wahaha");
        System.out.println(tag1.getTagName());

        try {
            dataTagInterface.addTagToData(data1, tag1);

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            List<Tag> list = dataTagInterface.getTagsOfData(data1);

            for (Tag tag2 : list) {
                System.out.println(tag2.getTagName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//            IntelDao intelDao = new Intelseverce();
//
//            Calendar calendar = new Calendar.Builder().build();
////            calendar.add(Calendar.DATE, );
//
////            Intel intel = intelDao.findIntelBefore();
////
////            IntelTagInterface intelTagInterface = new TagImp();
////            List<Tag> list = intelTagInterface.getTagsOfIntel(intel);
////            System.out.println("-----------------------------");
////            for (Tag tag2 : list){
////                System.out.println(tag2.getTagName());
////            }
////
////            intelTagInterface.copyTagToData(data1,intel);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}