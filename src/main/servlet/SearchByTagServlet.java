package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.Data;
import main.database.databaseservece.Dataservece;
import main.database.dbInterface.DataInterface;

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
        try {
            String data = UserregisterServlet.getBody(request);
            System.out.println(data);
            JsonParser parser = new JsonParser();
            object = (JsonObject) parser.parse(data);
            Tag = object.get("tag").getAsString();
        } catch (Exception e) {

            object.addProperty("signal", "Search By Tag Fail");
            retString = object.toString();
            e.printStackTrace();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }

        try {
            DataInterface dao = new Dataservece();
            List<Data> a = dao.getDataByTag(Tag);
            if (a == null) {
                System.out.println("error, empty list a");
            }
            int i = 0;
            JsonArray array = new JsonArray();
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
            object.addProperty("signal", "Search By Tag Fail");
            e.printStackTrace();
        }
        object.addProperty("signal", "Search By Tag success");
        retString = object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
