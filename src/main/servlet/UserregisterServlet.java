package main.servlet;

import com.google.gson.JsonObject;
import main.Beans.SystemUser;
import main.database.dbInterface.UserDao;
import main.database.fd;
import main.servlet.tool.apt_set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UserregisterServlet extends  javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String user_name=request.getParameter("username");
        String user_password=request.getParameter("password");
        String user_mail=request.getParameter("mail");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String signal = "success";
        try{
            UserDao dao=new fd();
            SystemUser user=dao.findUserByName(user_name);
            apt_set tmp=new apt_set();
            apt_set.setob(user,user_name,user_password,user_mail);
            dao.CreateUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        } catch (Exception e)
        {

                e.printStackTrace();
                signal="fail";
        }
        JsonObject object = new JsonObject();
        object.addProperty("signal",signal);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            doPost(request,response);
    }
}
