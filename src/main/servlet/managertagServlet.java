package main.servlet;

import com.google.gson.JsonObject;
import main.Beans.Data;
import main.Beans.SystemUser;
import main.Beans.Tag;
import main.database.databaseservece.Dataservece;
import main.database.databaseservece.TagImp;
import main.database.dbInterface.DataInterface;
import main.database.dbInterface.DataTagInterface;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "DataServlet")
public class managertagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String DataId = "";
        String retString = "";
        try {
            String data = JsonUtil.getPostBody(request);
            System.out.println(data);
            JsonObject object = JsonUtil.String2Json(data);
            DataId = object.get("data_id").getAsString();

            HttpSession session = request.getSession();
            if(session.getAttribute("user") == null){
                throw new Exception("not logged");
            }
        } catch (Exception e){
            e.printStackTrace();
            retString = JsonUtil.retDefaultJson(false,"error request", "",null);
        }

        Data data = null;
        if(retString.contentEquals("")){
            try {
                DataInterface dataDao = new Dataservece();
                data = dataDao.getDataByID(DataId);
                if (data == null){
                    throw new Exception("data is not existed");
                }
            } catch (Exception e){
                e.printStackTrace();
                retString = JsonUtil.retDefaultJson(false,"not_exist", "",null);
            }
        }

        if(data != null && retString.contentEquals("")) {

            List<Tag> list = null;
            try {
                DataTagInterface dataTagInterface = new TagImp();
                list = dataTagInterface.getTagsOfData(data);
                if (list == null){
                    throw new Exception("find Tag failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean like = false;
            try {
                HttpSession session = request.getSession();
                SystemUser user = (SystemUser) session.getAttribute("user");
                DataInterface dataInterface = new Dataservece();
                List<Data> list_like = dataInterface.searchLike(user);
                for (Data data1 : list_like){
                    if (data1.getDataID().contentEquals(data.getDataID())){
                        like = true;
                        break;
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                JsonObject data_json = JsonUtil.Data2Json(data,list);
                data_json.addProperty("like", like);
                retString = JsonUtil.retDefaultJson(true,"success","", data_json);
            } catch (Exception e){
                retString = JsonUtil.retDefaultJson(false,"to json error", "",null);
                e.printStackTrace();
            }
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
