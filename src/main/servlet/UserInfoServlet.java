package main.servlet;

import com.google.gson.JsonObject;
import main.Beans.SystemUser;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.UserDao;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean finish = false;
        String retString = "";
        try {
            // check json data

            HttpSession session = request.getSession();
            SystemUser user = (SystemUser)session.getAttribute("user");

            if(user == null){
                retString = JsonUtil.retDefaultJson(false, "not logged", "",null);
            } else {

                JsonObject object = new JsonObject();
                object.addProperty("user_name", user.getUserName());
                object.addProperty("email", user.getEmail());

                retString = JsonUtil.retDefaultJson(true,"success", "", object);
            }

        } catch (Exception e){
            e.printStackTrace();
            finish = true;
            retString = JsonUtil.retDefaultJson(false,"inner error", "error request", null);
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
