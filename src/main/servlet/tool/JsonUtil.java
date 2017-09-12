package main.servlet.tool;

import com.google.gson.*;
import main.Beans.Data;
import main.Beans.Tag;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class JsonUtil {

    public static JsonObject String2Json(String data) {
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(data);
    }

    public static String getPostBody(HttpServletRequest request) throws IOException {
        // get the body of the request object to a string

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    public static String retDefaultJson(boolean state, String errMsg, String out_put, JsonElement element){
        JsonObject object = new JsonObject();

        object.addProperty("state",state);
        object.addProperty("errMsg", errMsg);
        object.addProperty("out_put", out_put);
        object.add("element", element);

        return object.toString();
    }

    public static String toDateFormat(Date date){
        Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }


    public static JsonObject Data2Json(Data data, List<Tag> list){
        JsonObject object = new JsonObject();
        object.addProperty("title",data.Title== null ? "No title" : data.getTitle());
        object.addProperty("author",data.Author == null ? "No Author" : data.getAuthor());
        object.addProperty("content",data.Content == null ? "No Content" : data.getContent());
        object.addProperty("data_id",data.DataID == null ? "no" : data.getDataID() );
        object.addProperty("sites",data.sites == null ? "No source sites" : data.sites);
        object.addProperty("url",data.URL == null ? "no url" : data.getURL());
        object.addProperty("c_time",data.CrawlDate == null ? "no crawl time" : toDateFormat(data.getCrawlDate()));
        object.addProperty("p_time",data.CrawlDate == null ? "no publish time" : toDateFormat(data.getPublishDate()));

        JsonArray tag_array = new JsonArray();
        if (list != null){
            for (Tag tag : list){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("tag_name",tag.getTagName());
                jsonObject.addProperty("tag_id",tag.getTagID());
                jsonObject.addProperty("category",tag.getTagCategory());
                tag_array.add(jsonObject);
            }

        }

        object.add("tags",tag_array);

        return object;
    }

}
