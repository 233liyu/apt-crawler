package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.TagImp;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.DataTagInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "TagSearchServlet")
public class TagSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DataID = null;
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
            DataID = object.get("dataID").getAsString();
        } catch (Exception e) {

            object.addProperty("signal", "Tag Search Fail");
            retString = object.toString();
            e.printStackTrace();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }

        try {
            DataTagInterface dao = new TagImp();
            DataInterface dao1=new Dataservece();
            Data data =dao1.getDataByID(DataID);
            List<Tag> a = dao.getTagsOfData(data);
            if (a == null) {
                System.out.println("error, empty list a");
            }
            int i = 0;

            JsonArray array = new JsonArray();
            for (Tag ob : a) {
                JsonObject object1 = new JsonObject();
                object1.addProperty("no", i);
                object1.addProperty("Category", ob.getTagCategory());
                object1.addProperty("ID",ob.getTagID());
                object1.addProperty("Name",ob.getTagName());
                array.add(object1);
                i++;
            }
            object.add("jsonarray", array);
        } catch (Exception e) {
            object.addProperty("signal", "Tag Search Fail");
            e.printStackTrace();
        }
        object.addProperty("signal", "Tag Search Success");
        retString = object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}