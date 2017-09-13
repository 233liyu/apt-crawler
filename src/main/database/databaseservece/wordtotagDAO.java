package main.database.databaseservece;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.Beans.Tag;
import main.database.databaseconnect.Dataconnect;
import main.database.dbInterface.WORDDaoInterface;

public class wordtotagDAO implements WORDDaoInterface{

    public List<String> finddata(ResultSet resultSet) throws Exception
    {
        List<String> wordlist= new ArrayList<>();
        while(resultSet.next()){
            String word= resultSet.getString("word");
            wordlist.add(word);
        }
        return wordlist;
    }

    @Override
    public List<String> getWORDAccordToWORDOffset(int Start, int end) throws SQLException {
        Dataconnect connect=new Dataconnect();
        ResultSet res = null;
        PreparedStatement sta=null;
        List<String> list=new ArrayList<>();
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="select * from WORDTOTAG WHERE num>10 ORDER BY time DESC limit ?,?;";
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

    @Override
    public void refuceWORDByname(String word) throws Exception {
        Dataconnect connect=new Dataconnect();
        PreparedStatement sta=null;
        try {
            Connection con=null;
            con=connect.getConnection();
            String sql="UPDATE  WORDTOTAG set num = ?  WHERE word = ?; ";
            sta=con.prepareStatement(sql);
            sta.setInt(1,2);
            sta.setString(2,word);
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
