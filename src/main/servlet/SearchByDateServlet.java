package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.Data;
import main.database.databaseservece.Dataservece;
import main.database.dbInterface.DataInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

public class SearchByDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date now =new Date();
        String retString="";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object = new JsonObject();
        try{

            String data = UserregisterServlet.getBody(request);
            JsonParser parser = new JsonParser();
            object = (JsonObject) parser.parse(data);
            DataInterface dao = new Dataservece();
            List<Data> a = dao.getDataLimitBeforeDate(now,3);
            Data arr[] =a.toArray(new Data[a.size()]);
            int i = 0;
            JsonArray array  = new JsonArray();
            while (arr[i].getDataID() != null) {
                JsonObject object1 = new JsonObject();
                object1.addProperty("no", i);
                object1.addProperty("ID", arr[i].getDataID());
                object1.addProperty("Author", arr[i].getAuthor());
                object1.addProperty("Content", arr[i].getContent());
                object1.addProperty("Sites", arr[i].getSites());
                object1.addProperty("SourceIntelID", arr[i].getSourceIntelID());
                object1.addProperty("Title", arr[i].getTitle());
                object1.addProperty("URL", arr[i].getURL());
                array.add(object1);
                i++;
                
            }
        }catch(Exception e)
        {
            object.addProperty("signal","Output Fail");
            retString = object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        object.addProperty("signal","Output Success");
        retString = object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
        return;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
