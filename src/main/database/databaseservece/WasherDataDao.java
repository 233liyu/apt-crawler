package main.database.databaseservece;

import main.Beans.Data;
import main.Beans.Tag;
import main.database.databaseconnect.WashingConnect;
import main.database.dbInterface.WasherData;

import java.sql.*;


public class WasherDataDao implements WasherData{
    @Override
    public void createData(Data data) {

        PreparedStatement sta = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "LOCK TABLES data_info WRITE;\n" ;
            sta = connection.prepareStatement(sql);
            sta.execute();

            sql = "CALL create_data(@qwe);";
            sta = connection.prepareStatement(sql);
            sta.execute();


            sql = "UNLOCK TABLES;";
            sta = connection.prepareStatement(sql);
            sta.execute();

            sql = "SELECT @qwe;";
            sta = connection.prepareStatement(sql);
            ResultSet resultSets = sta.executeQuery();

            while (resultSets.next()){
                int id = resultSets.getInt("@qwe");
                System.out.println(id);
                data.setDataID(String.valueOf(id));
            }

            this.updateData(data);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getSourceID(String name){

        int retInt = 0;
        PreparedStatement sta = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "SELECT sourceid FROM source_info WHERE sourcename = ?;";
            sta = connection.prepareStatement(sql);
            sta.setString(1,name);
            ResultSet resultSets = sta.executeQuery();
            while (resultSets.next()){
                retInt = resultSets.getInt("sourceid");
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retInt;
    }

    @Override
    public void updateData(Data data) throws Exception {

        PreparedStatement sta = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "UPDATE data_info SET title = ?, author = ?, content = ?, url = ?, site = ?, publishtime = ?, crawltime = ?, sourceid = ? WHERE id = ?;" ;
            sta = connection.prepareStatement(sql);
            sta.setString(1,data.getTitle());
            sta.setString(2,data.getAuthor());
            sta.setString(3,data.getContent());
            sta.setString(4,data.getURL());
            sta.setString(5,data.getSites());
            sta.setDate(6,data.getPublishDate());
            sta.setDate(7,data.getCrawlDate());
            sta.setInt(8,this.getSourceID(data.getSites()));
            sta.setString(9,data.getDataID());

            sta.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("123");
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
