package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseservece.Dataservece;
import main.database.dbInterface.DataInterface;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
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
        JsonArray array  = new JsonArray();
        try{
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -3);
            System.out.println(cal.getTime());

            DataInterface dao = new Dataservece();
            List<Data> a = dao.getDataLimitBeforeDate(cal.getTime(),10);

            for (Data ob : a){
                array.add(JsonUtil.Data2Json(ob,new ArrayList<Tag>()));
            }
            object.add("jsonarray",array);
        }catch(Exception e)
        {
            retString= JsonUtil.retDefaultJson(false,"search by date fail",null,null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            e.printStackTrace();
            return;
        }
        retString= JsonUtil.retDefaultJson(true,"search by date success",null,array);
        out.write(retString);
        out.flush();
        response.flushBuffer();
        return;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
