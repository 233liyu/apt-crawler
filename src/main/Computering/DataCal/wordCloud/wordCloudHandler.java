package main.Computering.DataCal.wordCloud;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.TagImp;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.DataTagInterface;

import javax.net.ssl.SSLContext;
import java.sql.SQLException;
import java.util.*;

public class wordCloudHandler {

    private Map<String,Integer> map = null;
    private Date last_update;

    private void executeUpdate(){
        Map<String, Integer> map = new HashMap();

        DataTagInterface dataTagInterface = new TagImp();
        List<Tag> list = dataTagInterface.getAllTags();
        for (Tag tag : list){
            System.out.println(tag.getTagName());
            System.out.println(new TagImp().findByName(tag.getTagName()));

            try {
                List<Data> dataList = dataTagInterface.getDataByTag(tag);
                System.out.println(dataList.size());
                map.put(tag.getTagName(),dataList.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (this.map != null){
            this.map.clear();
        }
        this.map = map;
        last_update = new Date();
    }

    public Map<String,Integer> getResult(){
        if (this.map == null){
            executeUpdate();
        }
        return this.map;
    }

    public static JsonArray toJsonArray(Map<String,Integer> jMap){
        JsonArray array = new JsonArray();

        Iterator<Map.Entry<String, Integer>> iterator = jMap.entrySet().iterator();

        while (iterator.hasNext()){

            Map.Entry<String, Integer> entry = iterator.next();

            JsonObject object = new JsonObject();
            object.addProperty("name", entry.getKey());
            object.addProperty("value", entry.getValue());
            array.add(object);
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        return array;

    }

}
