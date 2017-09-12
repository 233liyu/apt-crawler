package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.Data;
import main.database.databaseservece.Dataservece;
import main.database.dbInterface.DataInterface;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "SearchByTagServlet")
public class SearchByTagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Tag =null;
        String retString = "";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        try {
            String data = JsonUtil.getPostBody(request);
            System.out.println(data);
            object = JsonUtil.String2Json(data);
            Tag = object.get("tag").getAsString();
        } catch (Exception e) {

            retString= JsonUtil.retDefaultJson(false,"search by tag fail",null,null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            e.printStackTrace();
            return;
        }

        try {
            DataInterface dao = new Dataservece();
            List<Data> a = dao.getDataByTag(Tag);
            if (a == null) {
                System.out.println("error, empty list a");
            }
            int i = 0;
            for (Data ob : a) {
                JsonObject object1 = new JsonObject();
                object1.addProperty("no", i);
                object1.addProperty("ID", ob.getDataID());
                object1.addProperty("Author", ob.getAuthor());
                object1.addProperty("Content", ob.getContent());
                object1.addProperty("Sites", ob.getSites());
                object1.addProperty("SourceIntelID", ob.getSourceIntelID());
                object1.addProperty("Title", ob.getTitle());
                object1.addProperty("URL", ob.getURL());
                String PublishDate = ob.getPublishDate().toString();
                String CrawlDate = ob.getCrawlDate().toString();
                object1.addProperty("PublishDate", PublishDate);
                object1.addProperty("CrawlDate", CrawlDate);
                array.add(object1);
                i++;
            }
            object.add("jsonarray", array);
        } catch (Exception e) {
            retString= JsonUtil.retDefaultJson(false,"search by tag fail",null,null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            e.printStackTrace();
            return;
        }
        retString= JsonUtil.retDefaultJson(true,"search by info success",null,array);
        out.write(retString);
        out.flush();
        response.flushBuffer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
