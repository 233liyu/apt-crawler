package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.Beans.Data;
import main.Beans.SystemUser;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.UserDao;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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
        JsonArray array  = new JsonArray();
        try {
            user = (SystemUser)request.getSession().getAttribute("user");
        }catch (Exception e) {
            retString= JsonUtil.retDefaultJson(false,"not log in","Log in first please",null);
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
            for (Data ob : a){
                array.add(JsonUtil.Data2Json(ob, new ArrayList<>()));
            }
        }catch(Exception e)
        {
            retString= JsonUtil.retDefaultJson(false,"user collection fail",null,null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        retString= JsonUtil.retDefaultJson(true,"user collection success",null,array);
        out.write(retString);
        out.flush();
        response.flushBuffer();
        return;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
