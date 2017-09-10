package main.database.databaseservece;

import main.Beans.Intel;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.IntelDao;

import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Intelseverce implements IntelDao {
    public List<Intel> findintel(ResultSet resultSet) throws Exception {
        List<Intel> list = new ArrayList<>();
        while (resultSet.next()) {
            Intel intel = new Intel();
            intel.setAuthor(resultSet.getString("author"));
            intel.setContent(resultSet.getString("content"));
            intel.setIntelID(resultSet.getString("id"));
            intel.setPublishDate(resultSet.getDate("publishtime"));
            intel.setSites(resultSet.getString("site"));
            intel.setTitle(resultSet.getString("title"));
            intel.setURL(resultSet.getString("URL"));
            intel.setCrawlDate(resultSet.getDate("crawltime"));
            list.add(intel);
        }

        return list;
    }

    @Override
    public Intel findIntelByID(int ID) throws SQLException {
        Dataconnect connect = new Dataconnect();
        ResultSet res = null;
        Intel intel = null;
        PreparedStatement sta = null;
        List<Intel> list = new ArrayList<>();
        try {
            Connection con = null;
            con = connect.getConnection();
            String sql = "select * from Intelligence_info where id = ? ";
            sta = con.prepareStatement(sql);
            //sta.setString(1, String.valueOf(ID));
            res = sta.executeQuery();
            System.out.println(sql);
            list = findintel(res);
            System.out.println("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        } finally {
            sta.close();
        }
        if(list.isEmpty())
        {
            System.out.println("it is null");
            return null;
        }
        else
        {
            System.out.println("not null");
            return    list.get(0);
        }

    }

    @Override
    public List<Intel> findIntelBefore(Date date) throws SQLException {
        Dataconnect connect = new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta = null;
        List<Intel> list = new ArrayList<>();
        try {
            Connection con = null;
            con = connect.getConnection();
            String time;
            time = DateFormat.getDateInstance().format(date);
            System.out.println(time);
            String sql = "select * from Intelligence_info where Date(publishtime) > ?";
            sta = con.prepareStatement(sql);
            sta.setString(1, time);
            res = sta.executeQuery();
            list = findintel(res);
            System.out.println("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        } finally {
            sta.close();
        }
        return list;
    }

    @Override
    public List<Intel> findIntelbetween(Date start_time, Date end_time) throws SQLException {

        Dataconnect connect = new Dataconnect();
        ResultSet res = null;
        List<Intel> list = new ArrayList<>();
        PreparedStatement sta = null;
        try {
            Connection con = null;
            con = connect.getConnection();
            String starttime, endtime;
            starttime = DateFormat.getDateInstance().format(start_time);
            endtime = DateFormat.getDateInstance().format(end_time);
            String sql = "select * from Intelligence_info where Date(publishtime) BETWEEN ? and ?";
            sta = con.prepareStatement(sql);
            sta.setString(1, starttime);
            sta.setString(2, endtime);
            res = sta.executeQuery();
            list = findintel(res);
            System.out.println("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库操作失败");
        } finally {
            sta.close();
        }
        return list;
    }
}
