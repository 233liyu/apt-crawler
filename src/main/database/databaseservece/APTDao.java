package main.database.databaseservece;

import main.Beans.Data;
import main.Beans.SystemUser;
import main.Beans.Tag;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.APTDaoInterface;
import main.database.dbInterface.DataInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APTDao implements APTDaoInterface {

    public List<String> finddata(ResultSet resultSet) throws Exception
    {
        List<String> APTlist= new ArrayList<>();
        while(resultSet.next()){
            String APTurl=resultSet.getString("url");
            APTlist.add(APTurl);
        }
        return APTlist;
    }

    @Override
    public List<String> getAPTDataAccordToDateOffset(int Start, int end) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        List<String> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from APTLIB ORDER BY time DESC limit ?,?;";
            sta=con.prepareStatement(sql);
            sta.setInt(1,Start);
            sta.setInt(2,end-Start);
            System.out.println(sql);
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
        return list;
    }
}
