package main.database.databaseservece;

import main.Beans.Data;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.DataDaoAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;

public class DataDaoAdminservece implements DataDaoAdmin {

    @Override
    public void UpdateDataByID(Data data) throws Exception {
        Dataconnect connect=new Dataconnect();
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="UPDATE into  data_info set title = ? ,author = ? ,content = ? ,url = ?, site = ?,sourceid = ?,publishtime = ?,crawtime = ? WHERE id = ?; ";
            sta=con.prepareStatement(sql);
            sta.setString(1,data.getTitle());
            sta.setString(2,data.getAuthor());
            sta.setString(3,data.getContent());
            sta.setString(4,data.getURL());
            sta.setString(5,data.getSites());
            sta.setString(6,data.getSourceIntelID());
            sta.setString(7, DateFormat.getDateInstance().format(data.getPublishDate()));
            sta.setString(7, DateFormat.getDateInstance().format(data.getCrawlDate()));
            sta.setInt(8,Integer.parseInt(data.getDataID()));

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
    public void DeleteData(String DataID) throws Exception {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="delete  from data_info WHERE  data_info.id= ? ";
            sta=con.prepareStatement(sql);
            sta.setInt(1, Integer.parseInt(DataID));
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
    public void CreateData(Data data) throws Exception {
        Dataconnect connect=new Dataconnect();
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="insert into  data_info set title = ? ,author = ? ,content = ? ,url = ?, site = ?,sourceid = ?,publishtime = ?,crawtime = ? ; ";
            sta=con.prepareStatement(sql);
            sta.setString(1,data.getTitle());
            sta.setString(2,data.getAuthor());
            sta.setString(3,data.getContent());
            sta.setString(4,data.getURL());
            sta.setString(5,data.getSites());
            sta.setString(6,data.getSourceIntelID());
            sta.setString(7, DateFormat.getDateInstance().format(data.getPublishDate()));
            sta.setString(7, DateFormat.getDateInstance().format(data.getCrawlDate()));

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
}
