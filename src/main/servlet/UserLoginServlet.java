package main.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.SystemUser;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.UserDao;
import main.servlet.tool.Encrypy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String user_name = null;
        String user_password = null;

        String retString = "";

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();

        try{
            String data = UserregisterServlet.getBody(request);

            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(data);

            user_name = object.get("username").getAsString();
            user_password = object.get("password").getAsString();

        } catch (Exception e){

            JsonObject object = new JsonObject();
            object.addProperty("signal",0);

            retString = object.toString();
            e.printStackTrace();

            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }


        int signal = 0;
        try{
            UserDao dao=new Userseverce();
//            SystemUser user = new SystemUser();
//            apt_set.setob(user,user_name,user_password,user_mail);
            SystemUser user = dao.findUserByName(user_name);
            if(user == null){
                // not exist
                System.out.println("not such user");

            } else {
                // exist
                System.out.println("user : " + user_name);
                System.out.println(user.getPassword());
                System.out.println(Encrypy.getMD5(user_password));
                System.out.println("user : " + user_name);


                if(user.isPasswordMatch(user_password)){
                    // match
                    System.out.println("match");

                    signal = 1;
                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);

                }

            }
//            TODO: jump to login
//            HttpSession session = request.getSession();
//            session.setAttribute("user",user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject object = new JsonObject();
        object.addProperty("signal",signal);

        retString = object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
