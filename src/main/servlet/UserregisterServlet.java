package main.servlet;

import com.google.gson.JsonObject;
import main.Beans.SystemUser;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.UserDao;
import main.servlet.tool.apt_set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


public class UserregisterServlet extends HttpServlet {

    public static String getBody(HttpServletRequest request) throws IOException {
        // get the body of the request object to a string

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        JsonObject receive =new JsonObject();

        String user_name=request.getParameter("username");
        String user_password=request.getParameter("password");
        String user_mail=request.getParameter("mail");
        System.out.println(user_name);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        int signal = 1;
        try{
            UserDao dao=new Userseverce();
            SystemUser user = new SystemUser();

            apt_set.setob(user,user_name,user_password,user_mail);
            dao.CreateUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        } catch (Exception e)
        {
            signal=0;
            e.printStackTrace();
        }
        JsonObject object = new JsonObject();
        object.addProperty("signal",signal);
        Writer out = response.getWriter();
        out.write(object.toString());
        out.flush();
        response.flushBuffer();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//            doPost(request,response);
        response.setStatus(404);
    }

}
