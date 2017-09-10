package main.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.SystemUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "UserInformationServlet")
public class UserInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject object =new JsonObject();
        Writer out =response.getWriter();
        String retString ="";
        String user_name=null;
        String user_passwordd=null;
        String user_ID=null;
        String demand=null;
        int user_power;
        String user_email=null;
        System.out.println("UserInformation代码执行");
         try {
             String data = UserregisterServlet.getBody(request);
             System.out.print(data);
             JsonParser parser = new JsonParser();
             object = (JsonObject) parser.parse(data);
             demand=object.get("demand").getAsString();
             demand.equals("User Info");
             SystemUser user = (SystemUser) request.getSession().getAttribute("user");
              user_name =user.getUserName();
              user_passwordd =user.getPassword();
              user_ID=user.getID();
              user_power=user.getPower();
              user_email=user.getEmail();
         }catch (Exception e)
         {
             object.addProperty("signal", "Session Not Find");
             retString=object.toString();
             out.write(retString);
             out.flush();
             response.flushBuffer();
             return;
         }
         try {
             object.addProperty("username", user_name);
             object.addProperty("userpassword", user_passwordd);
             object.addProperty("userID", user_ID);
             object.addProperty("userpower", user_power);
             object.addProperty("useremail", user_email);
         }catch (Exception e)
         {
             object.addProperty("signal", "User Informartion Fail");
             retString=object.toString();
             out.write(retString);
             out.flush();
             response.flushBuffer();
             return;
         }

        object.addProperty("signal", "User Informartion Success");
        retString=object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
