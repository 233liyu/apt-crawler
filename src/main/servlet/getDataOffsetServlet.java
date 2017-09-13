package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseservece.Dataservece;
import main.database.dbInterface.DataInterface;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "getDataOffsetServlet")
public class getDataOffsetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int start = 0;
        int ends = 0;
        String retString = "";
        try {
            String data = JsonUtil.getPostBody(request);
            System.out.println(data);
            JsonObject object = JsonUtil.String2Json(data);

            HttpSession session = request.getSession();
            if(session.getAttribute("user") == null){
                throw new Exception("not logged");
            }

            start = object.get("start").getAsInt();
            ends = object.get("end").getAsInt();
        } catch (Exception e){
            e.printStackTrace();
            retString = JsonUtil.retDefaultJson(false,"error request", "",null);
        }

        if (retString.contentEquals("")){
            try {
                DataInterface dataInterface = new Dataservece();
                List<Data> list = dataInterface.getDataAccordToDateOffset(start,ends);
                JsonArray array = new JsonArray();
                for (Data data : list){
                    JsonObject object = JsonUtil.Data2Json(data,new ArrayList<Tag>());
                    array.add(object);
                }
                retString = JsonUtil.retDefaultJson(true, "success","", array);
            } catch (Exception e){
                e.printStackTrace();
                retString = JsonUtil.retDefaultJson(false, "inner error","", null);
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        response.setContentType("application/json");

        Writer out = response.getWriter();
        out.write(retString);
        out.flush();
        out.close();
        response.flushBuffer();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(404);
    }
}
