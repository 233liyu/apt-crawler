package main.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.Data;
import main.Beans.SystemUser;
import main.database.databaseservece.Dataservece;
import main.database.dbInterface.DataInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "UserImformationServlet")
public class DataInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject object =new JsonObject();
        Writer out =response.getWriter();
        String retString ="";
        String data_id =null;
        String data_title=null;
        String data_url=null;
        String data_author=null;
        String data_crawldate=null;
        String data_publishdate=null;
        String data_content=null;
        String data_sites=null;
        String data = UserregisterServlet.getBody(request);
        JsonParser parser = new JsonParser();
        object = (JsonObject) parser.parse(data);
        Data a =new Data();
        DataInterface dao =new Dataservece();
        try {
            data_id=object.get("dataId").getAsString();
            a=dao.getDataByID(data_id);
            data_author =a.getAuthor();
            data_content=a.getContent();
            data_crawldate=a.getCrawlDate().toString();
            data_publishdate=a.getPublishDate().toString();
            data_sites=a.getSites();
            data_url=a.getURL();
        }catch (Exception e)
        {
            object.addProperty("signal", "Data Not Find");
            retString=object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        try {
            object.addProperty("dataAuthor", data_author);
            object.addProperty("dataContent", data_content);
            object.addProperty("dataCrawldate", data_crawldate);
            object.addProperty("dataPublishdate", data_publishdate);
            object.addProperty("dataSites", data_sites);
            object.addProperty("dataUrl", data_url);
        }catch (Exception e)
        {
            object.addProperty("signal", "Session Not Find");
            retString=object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        object.addProperty("signal", "Session Find");
        retString=object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}