package main.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Beans.Data;
import main.Beans.SystemUser;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.DataHistory;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.UserDao;
import main.database.databaseservece.DataHistoryseverce;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "DeleteHistoryServlet")
public class DeleteHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String DeleteHistory =null;
        SystemUser user =new SystemUser();
        Data now =new Data();
        String UserID =null;
        String DataID=null;
        String retString="";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object = new JsonObject();
        UserDao dao1 = new Userseverce();
        DataInterface dao2=new Dataservece();
        String data = UserregisterServlet.getBody(request);
        JsonParser parser = new JsonParser();
        object = (JsonObject) parser.parse(data);
        try{
            DeleteHistory =object.get("DeleteHistory").getAsString();
        }catch (Exception e)
        {
            object.addProperty("signal","Delete History Fail");
            retString=object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        if(DeleteHistory.equals("Delete All History"))
        {
            try{
                user =(SystemUser)request.getSession().getAttribute("user");
                DataHistory history =new DataHistoryseverce();
                history.DeleteAllHistory(user);
            }catch (Exception e)
            {
                object.addProperty("signal","Delete All History Fail");
                retString=object.toString();
                out.write(retString);
                out.flush();
                response.flushBuffer();
                return;
            }
            object.addProperty("signal","Delete All History Success");
            retString=object.toString();
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        if(DeleteHistory.equals("Delete Single History"))
        {
            try{
                user =(SystemUser)request.getSession().getAttribute("user");
                DataHistory history =new DataHistoryseverce();
                history.DeleteSingleHistory(user,DataID);
            }catch (Exception e)
            {
                object.addProperty("signal","Delete Single History Fail");
                retString=object.toString();
                out.write(retString);
                out.flush();
                response.flushBuffer();
                return;
            }
            object.addProperty("signal","Delete Single History Success");
            retString=object.toString();
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
