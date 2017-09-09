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

@WebServlet(name = "DataModifyServlet")
public class DataModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modify_DataID = null;
        String modify_Title = null;
        String modify_Author = null;
        String modify_Content = null;
        String retString = "";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object = new JsonObject();
        try {
            String data = UserregisterServlet.getBody(request);
            System.out.print(data);
            JsonParser parser = new JsonParser();
            object = (JsonObject) parser.parse(data);
            modify_Title = object.get("Tile").getAsString();
            modify_Author = object.get("Author").getAsString();
            modify_Content = object.get("Content").getAsString();
            modify_DataID=object.get("DataID").getAsString();
        } catch (Exception e) {
            object.addProperty("signal", "Modify fail");
            retString = object.toString();
            e.printStackTrace();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        try{
            DataInterface dao =new Dataservece();
            Data data=dao.getDataByID(modify_DataID);
            data.setAuthor(modify_Author);
            data.setContent(modify_Content);
            data.setTitle(modify_Title);
        }catch (Exception e)
        {
            object.addProperty("signal","Modify Fail");
            retString =object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        object.addProperty("signal","Modify Sucess");
        retString=object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
        return;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
