package main.database.databaseservece;

import main.Beans.Data;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.countDATA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class countDATAseverce implements countDATA {
    @Override
    public int return_apt() throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        int count=0;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select count(*) from data_info";
            sta=con.prepareStatement(sql);
            res=sta.executeQuery();
            while(res.next()){
                count=res.getInt("count(*)");
            }

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
        return count;
    }

    @Override
    public int return_tag() throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        int count=0;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select count(*) from Tag_info";
            sta=con.prepareStatement(sql);
            res=sta.executeQuery();
            while(res.next()){
                count=res.getInt("count(*)");
            }

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
        return count;
    }

    @Override
    public int return_crawl() throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        int count=0;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select count(*) from APTLIB";
            sta=con.prepareStatement(sql);
            res=sta.executeQuery();

            while(res.next()){
                count=res.getInt("count(*)");
            }
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
        return count;
    }

    @Override
    public int retern_todayratio() throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        Date dt=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
        String time=matter1.format(dt);
        int count=0;
        double ratio;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select count(*) from data_info where crawltime=?";
            sta=con.prepareStatement(sql);
            sta.setString(1,time);
            res=sta.executeQuery();
            while(res.next()){
                count=res.getInt("count(*)");
            }
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

        return count;
    }
}
