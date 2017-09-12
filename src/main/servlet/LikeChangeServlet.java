package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

@WebServlet(name = "LikeChangeServlet")
public class LikeChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemUser user = new SystemUser();
        String LikeChange = null;
        String UserID = null;
        String DataID = null;
        String retString = "";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object = new JsonObject();
        UserDao dao1 = new Userseverce();
        DataInterface dao2 = new Dataservece();
        JsonArray array = new JsonArray();
        try {

            String data = JsonUtil.getPostBody(request);
            System.out.println(data);
            object = JsonUtil.String2Json(data);
            LikeChange = object.get("LikeChange").getAsString();
            user=(SystemUser)request.getSession().getAttribute("user");
            DataID = object.get("DataTitle").getAsString();
        } catch (Exception e) {
            retString= JsonUtil.retDefaultJson(false,"not log in","Log in first please",null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            e.printStackTrace();
            return;
        }
        if (LikeChange.equals("AddLike")) {
            try {
                DataInterface dao =new Dataservece();
               dao.addLike(user, DataID);
            } catch (Exception e) {
                retString= JsonUtil.retDefaultJson(false,"add like fail",null,null);
                out.write(retString);
                out.flush();
                response.flushBuffer();
                return;
            }
            retString= JsonUtil.retDefaultJson(true,"add like success",null,null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        } else if(LikeChange.equals("DeleteLike")){
            try {
                DataInterface dao =new Dataservece();
                dao.deleteLike(user, DataID);
            } catch (Exception e) {
                retString= JsonUtil.retDefaultJson(false,"delete like fail",null,null);
                out.write(retString);
                out.flush();
                response.flushBuffer();
                return;
            }
            retString= JsonUtil.retDefaultJson(true,"delete like success",null,null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}