package main.database.databaseservece;

import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.DataInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dataservece implements DataInterface {

    public Data finddata(Data data,ResultSet resultSet) throws Exception
    {

        data.setAuthor(resultSet.getString("author"));
        data.setContent(resultSet.getString("content"));
        data.setDataID(resultSet.getString("id"));
        data.setPublishDate(resultSet.getDate("publishtime"));
        data.setSites(resultSet.getString("site"));
        data.setSourceIntelID(resultSet.getString("sourceid"));
        data.setTitle(resultSet.getString("title"));
        data.setURL(resultSet.getString("url"));
        data.setCrawlDate(resultSet.getDate("crawltime"));
        return data;
    }

    @Override
    public Data getDataByID(String ID) {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        Data data=new Data();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info where id=?";
            PreparedStatement sta=con.prepareStatement(sql);
            sta.setString(1,ID);
            res=sta.executeQuery(sql);
            data=finddata(data,res);
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }

        return data;
    }

    @Override
    public List<Data> getDataByTag(String tag) {
        //还没实现
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        ArrayList<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info where id=?";
            PreparedStatement sta=con.prepareStatement(sql);
            sta.setString(1,tag);
            res=sta.executeQuery(sql);
            while(res.next())
            {
                Data data=new Data();
                data=finddata(data,res);
                list.add(data);
            }
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }
        return list;
    }

    @Override
    public List<Data> getDataLimitBeforeDate(Date date, int limits) {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        ArrayList<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String time;
            time = DateFormat.getDateInstance().format(date);
            String sql="select * from data_info where Date(publishtime) > ? ORDER BY publishtime DESC limit 0,?";
            PreparedStatement sta=con.prepareStatement(sql);
            sta.setString(1,time);
            sta.setString(2,String.valueOf(limits));
            res=sta.executeQuery(sql);
            while(res.next())
            {
                Data data=new Data();
                data=finddata(data,res);
                list.add(data);
            }
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }
        return list;
    }

    @Override
    public List<Data> getDataAccordToDateOffset(int Start, int end) {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        ArrayList<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info ORDER BY publishtime DESC limit ?,?";
            PreparedStatement sta=con.prepareStatement(sql);
            sta.setString(1,String.valueOf(Start));
            sta.setString(2,String.valueOf(end));
            res=sta.executeQuery(sql);
            while(res.next())
            {
                Data data=new Data();
                data=finddata(data,res);
                list.add(data);
            }
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }
        return list;
    }

    @Override
    public List<Data> searchData(String keyWord) {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        ArrayList<Data> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from data_info where content like '%"+keyWord+"%' ";
            PreparedStatement sta=con.prepareStatement(sql);
            res=sta.executeQuery(sql);
            while(res.next())
            {
                Data data=new Data();
                data=finddata(data,res);
                list.add(data);
            }
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }
        return list;
    }

    @Override
    public List<Data> searchDataByTag(Tag tag, String keyWord) {

        return null;
    }
}
