package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.Beans.Data;
import main.database.databaseservece.APTDao;
import main.database.dbInterface.APTDaoInterface;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AptGetServlet")
public class AptGetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String retString = "";
        int start = 0;
        int end = 0;
        try {
            String data = JsonUtil.getPostBody(request);
            System.out.println(data);
            JsonObject object = JsonUtil.String2Json(data);

            start = object.get("start").getAsInt();
            end = object.get("end").getAsInt();

            HttpSession session = request.getSession();
            if(session.getAttribute("user") == null){
                throw new Exception("not logged");
            }
        } catch (Exception e){
            e.printStackTrace();
            retString = JsonUtil.retDefaultJson(false,"error request", "",null);
        }

        if (retString.contentEquals("")){
            try {
                JsonArray array = new JsonArray();
                APTDaoInterface aptDaoInterface = new APTDao();
                List<String> list = aptDaoInterface.getAPTDataAccordToDateOffset(start, end);

                for (String st : list){
                    array.add(st);
                }

                retString = JsonUtil.retDefaultJson(true,"success","",array);
            } catch (SQLException e) {
                e.printStackTrace();
                retString = JsonUtil.retDefaultJson(false,"error finding", "",null);
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
