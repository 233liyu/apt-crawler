package main.servlet;

import com.google.gson.JsonObject;
import main.Beans.SystemUser;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.UserDao;
import main.servlet.tool.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet(name = "UserActionServlet")
public class UserActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = null;
        String action = null;
        String user_name = null;
        String pass_word = null;
        String email = null;

        boolean finish = false;

        String retString = "";

        try {
            // check json data
            data = JsonUtil.getPostBody(request);
            JsonObject object = JsonUtil.String2Json(data);

            System.out.println(data);
            action = object.get("action").getAsString();
            user_name = object.get("user_name").getAsString();

            if (action.contentEquals("login")){
                // login need password
                pass_word = object.get("pass_word").getAsString();
            } else if (action.contentEquals("sign_up")){
                // sign up need password and email
                pass_word = object.get("pass_word").getAsString();
                email = object.get("email").getAsString();
            } else if (action.contentEquals("log_out")) {
                // log out need name
            } else {
                throw new Exception("error request");
            }

        } catch (Exception e){
            e.printStackTrace();
            finish = true;
            retString = JsonUtil.retDefaultJson(false,"error request", "error request", null);
        }


        if (!finish){
            // take actions
            if (action.contentEquals("login")){
                // login action
                try {
                    UserDao dao = new Userseverce();
                    SystemUser user = dao.findUserByName(user_name);
                    if (user.isPasswordMatch(pass_word)){
                        // password match and user existed

                        HttpSession session = request.getSession();
                        session.setAttribute("user",user);

                        retString = JsonUtil.retDefaultJson(true,"login_success","登录成功！",null);

                    } else {
                        throw new Exception("error account or password");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    retString = JsonUtil.retDefaultJson(false, "password error", "用户名或密码错", null);
                }

            } else if (action.contentEquals("sign_up")){

                try {
                    UserDao dao = new Userseverce();

                    SystemUser user = new SystemUser();
                    user.setEmail(email);
                    user.setPassword(pass_word);
                    user.setUserName(user_name);
                    dao.CreateUser(user);
                    retString = JsonUtil.retDefaultJson(true,"sign up success","注册成功",null);

                } catch (Exception e) {
                    e.printStackTrace();
                    retString = JsonUtil.retDefaultJson(false, "name existed", "用户名已存在",null);
                }

            } else if (action.contentEquals("log_out")){
                try {

                    HttpSession session = request.getSession();
                    SystemUser user = (SystemUser)session.getAttribute("user");
                    if(user == null){
                        throw new Exception("already logged_out");
                    }
                    retString = JsonUtil.retDefaultJson(true, "log out success", "登出成功",null);
                } catch (Exception e){
                    e.printStackTrace();
                    retString = JsonUtil.retDefaultJson(false, "log out failed", "登出失败",null);
                }
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
