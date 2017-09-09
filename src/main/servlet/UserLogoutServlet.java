package main.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "UserLogoutServlet")
public class UserLogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Logout =null;
        String retString="";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object=new JsonObject();
        try{
                String data = UserregisterServlet.getBody(request);

                JsonParser parser = new JsonParser();
                object = (JsonObject) parser.parse(data);

                Logout = object.get("logout").getAsString();
                if(Logout.equals("logout")){
            }
            HttpSession session = request.getSession();
            session.invalidate();
        }catch(Exception e)
        {
            object.addProperty("signal","Logout Fail");
            retString = object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        object.addProperty("signal","logout Success");
        retString = object.toString();
        out.write(retString);
        out.flush();
        response.flushBuffer();
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
