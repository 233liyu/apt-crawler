package main.servlet;

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

@WebServlet(name = "SearchByInfoServlet")

public class SearchByInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String demand = null;
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
            demand = object.get("demand").getAsString();
        } catch (Exception e) {

            object.addProperty("signal", "Demand Fail");
            retString = object.toString();
            e.printStackTrace();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }

        try {
            DataInterface dao = new Dataservece();
            List<Data> a = dao.searchData(demand);
//            Data arr[] = a.toArray(new Data[a.size()]);
            if(a == null){
                System.out.println("error, empty list a");
            }
            int i = 0;
            object.addProperty("signal", "Demand success");
            for (Data ob : a){
                object.addProperty("no", i);
                object.addProperty("ID", ob.getDataID());
                object.addProperty("Author", ob.getAuthor());
                object.addProperty("Content", ob.getContent());
                object.addProperty("Sites", ob.getSites());
                object.addProperty("SourceIntelID", ob.getSourceIntelID());
                object.addProperty("Title", ob.getTitle());
                object.addProperty("URL", ob.getURL());
                i++;
            }
//            while (arr[i].getDataID() != null) {
//                object.addProperty("no", i);
//                object.addProperty("ID", arr[i].getDataID());
//                object.addProperty("Author", arr[i].getAuthor());
//                object.addProperty("Content", arr[i].getContent());
//                object.addProperty("Sites", arr[i].getSites());
//                object.addProperty("SourceIntelID", arr[i].getSourceIntelID());
//                object.addProperty("Title", arr[i].getTitle());
//                object.addProperty("URL", arr[i].getURL());
//                i++;
//            }
        } catch (Exception e) {
            object.addProperty("signal", "Demand Fail");
            e.printStackTrace();
        }
        retString = object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
