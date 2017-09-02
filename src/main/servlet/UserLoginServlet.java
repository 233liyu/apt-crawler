package main.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.Beans.SystemUser;
import main.database.dbInterface.UserDao;
import main.database.databaseservece.Userseverce;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String user_name = request.getParameter("username");
        String user_password=request.getParameter("userpassword");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String signal="success";


        try {

            UserDao dao = new Userseverce();
            SystemUser user = dao.findUserByName("user_name");
            user_password.equals(user.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        } catch (Exception e){
            signal="fail";
            e.printStackTrace();
        }
        JsonObject object = new JsonObject();

        object.addProperty("signal",signal);


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
