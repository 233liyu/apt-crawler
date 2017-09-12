package main.database.databaseservece;

import main.Beans.SystemUser;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Userseverce implements UserDao{
    public List<SystemUser> finduser(ResultSet res) throws Exception
    {
        List<SystemUser> list=new ArrayList<>();
        while(res.next())
        {
            SystemUser systemUser=new SystemUser();
            systemUser.setPassword(res.getString("password"));
            systemUser.setID(res.getString("id"));
            systemUser.setEmail(res.getString("email"));
            systemUser.setPower(res.getInt("power"));
            systemUser.setUserName(res.getString("username"));
            list.add(systemUser);
        }

        return list;
    }

    @Override
    public SystemUser findUserByID(String ID) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        SystemUser systemUser=new SystemUser();
        PreparedStatement sta = null;
        List<SystemUser> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql = String.format("select * from %s where id = ?;","user_info");
            sta=con.prepareStatement(sql);
            sta.setInt(1,Integer.parseInt(ID));
            res=sta.executeQuery();
            list=finduser(res);
            systemUser=list.get(0);
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        } finally {
            sta.close();
        }
        if(list.isEmpty())
        {
            return null;
        }
        else return list.get(0);
    }

    @Override
    public SystemUser findUserByName(String userName) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        SystemUser systemUser=new SystemUser();
        PreparedStatement sta=null;
        List<SystemUser> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from user_info where username = ?";
            sta=con.prepareStatement(sql);
            sta.setString(1,userName);
            res=sta.executeQuery();
            list=finduser(res);
            systemUser=list.get(0);
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }finally {
            sta.close();
        }
        if(list.isEmpty())
        {
            return null;
        }
        else return list.get(0);
    }

    @Override
    public void UpdateUser(SystemUser user) throws Exception{
        String id=user.getID();
        String username=user.getUserName();
        String email=user.getEmail();
        String password=user.getPassword();
        Dataconnect connect=new Dataconnect();
        PreparedStatement sta=null;
        try{
            Connection con=connect.getConnection();
            String sql="UPDATE user_info set username=?,password=MD5(?),email=? where id=? ";
            sta=con.prepareStatement(sql);
            sta.setString(1,username);
            sta.setString(2,password);
            sta.setString(3,email);
            sta.execute();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("插入失败");
        }
        finally {
            sta.close();
        }


    }

    @Override
    public String CreateUser(SystemUser user) throws Exception{
        String s="ok";
        Dataconnect connect=new Dataconnect();
        String id=user.getID();
        String username=user.getUserName();
        String email=user.getEmail();
        String password=user.getPassword();
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="insert into user_info (username,password,email)VALUES ('"+username+"',MD5('"+password+"'),'"+email+"')";
            sta=con.prepareStatement(sql);
            sta.execute();
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
            throw new Exception("插入失败");
        }
        finally {
            sta.close();
        }

        return s;
    }

    @Override
    public String DeleteUser(SystemUser user) throws Exception{
        return DeleteUser(user.getID());
    }

    @Override
    public String DeleteUser(String userID) throws Exception {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        String s="ok";
        PreparedStatement sta=null;
        SystemUser systemUser=new SystemUser();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="delete  from user_info where id = ?";
            sta=con.prepareStatement(sql);
            sta.setString(1,userID);
            sta.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
            throw new Exception("123");
        }
        finally {
            sta.close();
        }
        return s;
    }

    @Override
    public List<SystemUser> searchUserByName(String name) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        java.sql.Statement sta = null;
        List<SystemUser> list=new ArrayList<>();
        try {

            Connection con=connect.getConnection();
            sta=con.createStatement();
            String sql="select * from user_info where user_info.username like '%"+name+"%'";
            res=sta.executeQuery(sql);
            list=finduser(res);
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }
        finally {
            sta.close();
        }
        if(list.isEmpty())
        {
            return null;
        }
        else return list;
    }

    @Override
    public List<SystemUser> searchUserByEmail(String email) throws SQLException {
        Dataconnect connect=new Dataconnect();
        java.sql.Statement sta = null;
        ResultSet res = null;
        List<SystemUser> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            sta=con.createStatement();
            String sql="select * from user_info where email like '%"+email+"%'";
            res=sta.executeQuery(sql);
            list=finduser(res);
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }
        finally {
            sta.close();
        }
        if(list.isEmpty())
        {
            return null;
        }
        else return list;
    }
}
