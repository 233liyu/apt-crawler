package main.servlet;

import com.google.gson.JsonObject;
import main.Beans.SystemUser;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String user_name = request.getParameter("username");
        String user_password=request.getParameter("userpassword");
        System.out.println(user_name+"hahahahahahahah");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        int signal=1;


        try {
            UserDao dao = new Userseverce();
            SystemUser user = dao.findUserByName("user_name");
            user_password.equals(user.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        } catch (Exception e){
            signal=0;
            e.printStackTrace();
        }
        JsonObject data = new JsonObject();

        data.addProperty("signal",signal);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
