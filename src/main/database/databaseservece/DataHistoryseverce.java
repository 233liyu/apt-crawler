package main.database.databaseservece;
import main.Beans.Data;
import main.Beans.SystemUser;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.DataHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataHistoryseverce implements DataHistory{

    public List<Data> finddata(ResultSet resultSet) throws Exception
    {
        List<Data> list= new ArrayList<>();
        while(resultSet.next()){
            Data data=new Data();
            data.setAuthor(resultSet.getString("author"));
            data.setContent(resultSet.getString("content"));
            data.setDataID(resultSet.getString("id"));
            data.setPublishDate(resultSet.getDate("publishtime"));
            data.setSites(resultSet.getString("site"));
            data.setSourceIntelID(resultSet.getString("sourceid"));
            data.setTitle(resultSet.getString("title"));
            data.setURL(resultSet.getString("url"));
            data.setCrawlDate(resultSet.getDate("crawltime"));
            list.add(data);
        }
        return list;
    }

    @Override
    public void AddHistory(SystemUser user, String dataID) throws Exception {
        Dataconnect connect=new Dataconnect();
        String id=user.getID();
        String username=user.getUserName();
        String email=user.getEmail();
        String password=user.getPassword();
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="insert into  history_info set userid = ? ,dataid = ?,time=TIMESTAMP(now()); ";
            sta=con.prepareStatement(sql);
            sta.setString(1,id);
            sta.setString(2,dataID);
            sta.execute();
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
    }

    @Override
    public List<Data> GetHistory(SystemUser user) throws SQLException {
        Dataconnect connect=new Dataconnect();
        String userid=user.getID();
        ResultSet res = null;
        PreparedStatement sta=null;
        List<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info where data_info.id=history_info.id and history_info.userid=?";
            sta=con.prepareStatement(sql);
            sta.setString(1,userid);
            res=sta.executeQuery();
            list=finddata(res);
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
    public void DeleteAllHistory(SystemUser user) throws Exception {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        String userID=user.getID();
        PreparedStatement sta=null;
        try
        {
            Connection con=null;
            con=connect.getConnection();
            String sql="delete  from history_info where history_info.userid = ?";
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
    }

    @Override
    public void DeleteSingleHistory(SystemUser user, String dataID) throws Exception {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        String userID=user.getID();
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="delete  from history_info where history_info.userid = ? and history_info.dataid=?";
            sta=con.prepareStatement(sql);
            sta.setString(1,userID);
            sta.setString(2,dataID);
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

    }
}
