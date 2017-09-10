package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.SystemUser;
import main.Beans.Data;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "UserCollectionServlet")
public class UserCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemUser user =new SystemUser();
        Data like =new Data();
        String UserID =null;
        String retString="";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object = new JsonObject();
        UserDao dao1 = new Userseverce();
        try {

            String data = UserregisterServlet.getBody(request);
            JsonParser parser = new JsonParser();
            object = (JsonObject) parser.parse(data);
            user = (SystemUser)request.getSession().getAttribute("user");
        }catch (Exception e) {
            object.addProperty("signal","Search User Fail");
            retString=object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        try{
            DataInterface dao=new Dataservece();
            List<Data> a = dao.searchLike(user);
            int i = 0;
            JsonArray array  = new JsonArray();
            for (Data ob : a){
                JsonObject object1 = new JsonObject();
                object1.addProperty("no", i);
                object1.addProperty("ID", ob.getDataID());
                object1.addProperty("Author", ob.getAuthor());
                object1.addProperty("Content", ob.getContent());
                object1.addProperty("Sites", ob.getSites());
                object1.addProperty("SourceIntelID", ob.getSourceIntelID());
                object1.addProperty("Title", ob.getTitle());
                object1.addProperty("URL", ob.getURL());
                String PublishDate =ob.getPublishDate().toString();
                String CrawlDate =ob.getCrawlDate().toString();
                object1.addProperty("PublishDate",PublishDate);
                object1.addProperty("CrawlDate",CrawlDate);
                array.add(object1);
                i++;
            }
            object.add("jsonarray",array);
        }catch(Exception e)
        {
            object.addProperty("signal","SearchLike Fail");
            retString = object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        object.addProperty("signal","SearchLike Success");

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
