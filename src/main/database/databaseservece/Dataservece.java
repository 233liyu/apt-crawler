package main.database.databaseservece;

import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.DataInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dataservece implements DataInterface {

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
    public Data getDataByID(String ID) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        Data data=new Data();
        PreparedStatement sta=null;
        List<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info where id=?";
            sta=con.prepareStatement(sql);
            sta.setString(1,ID);
            res=sta.executeQuery();
            list=finddata(res);
            data=list.get(0);
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
        else return list.get(0);
    }

    @Override
    public List<Data> getDataByTag(String tag) throws SQLException {
        //还没实现
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        List<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info where id=?";
            sta=con.prepareStatement(sql);
            sta.setString(1,tag);
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
    public List<Data> getDataLimitBeforeDate(Date date, int limits) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        List<Data> list=new ArrayList<>();
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String time;
            time = DateFormat.getDateInstance().format(date);
            String sql="select * from data_info where Date(publishtime) > ? ORDER BY publishtime DESC limit 0,?";
            sta=con.prepareStatement(sql);
            sta.setString(1,time);
            sta.setString(2,String.valueOf(limits));
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
    public List<Data> getDataAccordToDateOffset(int Start, int end) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        List<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info ORDER BY publishtime DESC limit ?,?";
            sta=con.prepareStatement(sql);
            sta.setString(1,String.valueOf(Start));
            sta.setString(2,String.valueOf(end));
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
    public List<Data> searchData(String keyWord) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        List<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info where content like '%"+keyWord+"%' ";
            sta=con.prepareStatement(sql);
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
    public List<Data> searchDataByTag(Tag tag, String keyWord) {

        return null;
    }
}
