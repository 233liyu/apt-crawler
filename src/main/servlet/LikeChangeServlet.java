package main.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.SystemUser;
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
        try {

            String data = UserregisterServlet.getBody(request);
            JsonParser parser = new JsonParser();
            object = (JsonObject) parser.parse(data);
            LikeChange = object.get("LikeChange").getAsString();
            user =(SystemUser)request.getSession().getAttribute("user");
            DataID = object.get("DataID").getAsString();
        } catch (Exception e) {
            object.addProperty("signal", "Like Change Fail");
            retString = object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        if (LikeChange.equals("AddLike")) {
            try {
                DataInterface dao =new Dataservece();
               dao.addLike(user, DataID);
            } catch (Exception e) {
                object.addProperty("signal", "Add Like Fail");
                retString = object.toString();
                out.write(retString);
                out.flush();
                response.flushBuffer();
                return;
            }
            object.addProperty("signal", "Add Like Success");
            retString = object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        } else {
            try {
                DataInterface dao =new Dataservece();
                dao.deleteLike(user, DataID);
            } catch (Exception e) {
                object.addProperty("signal", "Delete Like Fail");
                retString = object.toString();
                out.write(retString);
                out.flush();
                response.flushBuffer();
                return;
            }
            object.addProperty("signal", "Delete Like Success");
            retString = object.toString();
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