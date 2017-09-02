package main.database.databaseservece;

import main.Beans.Intel;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.IntelDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Intelseverce implements IntelDao{
    public Intel findintel(Intel intel,ResultSet resultSet) throws Exception
    {

        intel.setAuthor(resultSet.getString("author"));
        intel.setContent(resultSet.getString("content"));
        intel.setIntelID(resultSet.getString("id"));
        intel.setPublishDate(resultSet.getDate("publishtime"));
        intel.setSites(resultSet.getString("site"));
        intel.setTitle(resultSet.getString("title"));
        intel.setURL(resultSet.getString("url"));
        intel.setCrawlDate(resultSet.getDate("crawltime"));
        return intel;
    }

    @Override
    public Intel findIntelByID(int ID) {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        Intel intel=new Intel();

        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from Intelligence_info where id =?";
            PreparedStatement sta=con.prepareStatement(sql);
            sta.setString(1,String.valueOf(ID));
            res=sta.executeQuery(sql);
            intel=findintel(intel,res);
            System.out.println("查询成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        }

        return intel;
    }

    @Override
    public List<Intel> findIntelBefore(Date date) {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        ArrayList<Intel> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String time;
            time = DateFormat.getDateInstance().format(date);
            String sql="select * from Intelligence_info where Date(publishtime) > ?";
            PreparedStatement sta=con.prepareStatement(sql);
            sta.setString(1,time);
            res=sta.executeQuery(sql);
            while(res.next())
            {
                Intel intel=new Intel();
                intel=findintel(intel,res);
                list.add(intel);
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
    public List<Intel> findIntelbetween(Date start_time, Date end_time) {

        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        ArrayList<Intel> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String starttime,endtime;
            starttime = DateFormat.getDateInstance().format(start_time);
            endtime = DateFormat.getDateInstance().format(end_time);
            String sql="select * from Intelligence_info where Date(publishtime) BETWEEN ? and ?";
            PreparedStatement sta=con.prepareStatement(sql);
            sta.setString(1,starttime);
            sta.setString(2,endtime);
            res=sta.executeQuery(sql);
            while(res.next())
            {
                Intel intel=new Intel();
                intel=findintel(intel,res);
                list.add(intel);
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
}
