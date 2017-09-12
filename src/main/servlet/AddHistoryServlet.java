package  main.servlet;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.servlet.tool.JsonUtil;
import main.Beans.SystemUser;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.DataHistory;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.UserDao;
import main.servlet.UserregisterServlet;
import main.database.databaseservece.DataHistoryseverce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "UserCollectionServlet")
public  class AddHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemUser user =new SystemUser();
        String UserID =null;
        String DataID=null;
        String retString="";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Writer out = response.getWriter();
        JsonObject object = new JsonObject();
        UserDao dao1 = new Userseverce();
        DataInterface dao2=new Dataservece();
        try {

            String data = JsonUtil.getPostBody(request);
            object = JsonUtil.String2Json(data);
            user = (SystemUser)request.getSession().getAttribute("user");
            DataID =object.get("DataID").getAsString();
        }catch (Exception e) {
            retString=JsonUtil.retDefaultJson(false,"not log in","Log in first please",null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        try{
            DataHistory History =new DataHistoryseverce();
            History.AddHistory(user,DataID);
        }catch (Exception e)
        {
            retString=JsonUtil.retDefaultJson(false,"add history fail",null,null);
            out.write(retString);
            out.flush();
            response.flushBuffer();
            return;
        }
        retString=JsonUtil.retDefaultJson(true,"add history sucess",null,null);
        out.write(retString);
        out.flush();
        response.flushBuffer();
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


