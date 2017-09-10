package main.database.databaseservece;

import main.Beans.Data;
import main.Beans.Intel;
import main.Beans.Tag;
import main.database.databaseconnect.WashingConnect;
import main.database.dbInterface.DataTagInterface;
import main.database.dbInterface.IntelTagInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagImp implements DataTagInterface, IntelTagInterface {

    private static List<Tag> getFromResult(ResultSet resultSet) throws SQLException {
        List<Tag> list = new ArrayList<>();
        while (resultSet.next()) {
            Tag tag = new Tag();
            tag.setTagName(resultSet.getString("name"));
            tag.setTagCategory(resultSet.getString("category"));
            tag.setTagID(String.valueOf(resultSet.getInt("id")));
            list.add(tag);
        }
        return list;
    }

    private static Tag retTag (List<Tag> list){
        if (list == null) {
            return null;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Tag> getTagsOfData(Data data) throws SQLException {
        ResultSet res = null;
        PreparedStatement sta = null;
        List<Tag> list = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "SELECT * FROM Tag_info WHERE id in (SELECT tag_key FROM data_tag_table WHERE data_key = ?)";
            sta = connection.prepareStatement(sql);
            sta.setString(1, data.getDataID());
            res = sta.executeQuery();

            list = getFromResult(res);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sta.close();
        }
        if (list == null) {
            return new ArrayList<Tag>();
        }
        return list;

    }

    @Override
    public Tag findByID(int id) throws SQLException {
        ResultSet res = null;
        PreparedStatement sta = null;
        List<Tag> list = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "SELECT * FROM Tag_info WHERE id = ?";
            sta = connection.prepareStatement(sql);
            sta.setInt(1, id);

            res = sta.executeQuery();

            list = getFromResult(res);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return retTag(list);

    }

    @Override
    public Tag findByName(String name) {
        ResultSet res = null;
        PreparedStatement sta = null;
        List<Tag> list = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "SELECT * FROM Tag_info WHERE name = ?";
            sta = connection.prepareStatement(sql);
            sta.setString(1, name);

            res = sta.executeQuery();

            list = getFromResult(res);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retTag(list);
    }

    @Override
    public void addTagToData(Data data, Tag tag) throws Exception {

        PreparedStatement sta = null;
        List<Tag> list = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "CALL addTagDataConnection(?,?);";
            sta = connection.prepareCall(sql);
            sta.setInt(1, Integer.parseInt(data.getDataID()));
            sta.setInt(2, Integer.parseInt(tag.getTagID()));
            sta.execute();


        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("add connection failed");
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeTagData(Data data, Tag tag) throws Exception {
        PreparedStatement sta = null;
        List<Tag> list = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "DELETE FROM data_tag_table WHERE data_key = ? AND tag_key = ?";
            sta = connection.prepareStatement(sql);
            sta.setInt(1, Integer.parseInt(data.getDataID()));
            sta.setInt(2, Integer.parseInt(tag.getTagID()));
            sta.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("could not delete connection");
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createTag(Tag tag) throws Exception {

        PreparedStatement sta = null;

        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "INSERT INTO Tag_info (name, category) VALUES (?,?)";
            sta = connection.prepareStatement(sql);
            sta.setString(1, tag.getTagName());
            sta.setString(2, tag.getTagCategory());
            sta.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("could not create connection");
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Tag> getTagsOfIntel(Intel intel) {
        ResultSet res = null;
        PreparedStatement sta = null;
        List<Tag> list = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "SELECT * FROM Tag_info WHERE id IN(SELECT tagid FROM intelligencetag_info WHERE intelligenceid = ?);";
            sta = connection.prepareStatement(sql);
            sta.setString(1, intel.getIntelID());
            res = sta.executeQuery();

            list = getFromResult(res);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (list == null) {
            return new ArrayList<Tag>();
        }
        return list;

    }

    @Override
    public void copyTagToData(Data data, Intel intel) throws Exception {

        ResultSet res = null;
        PreparedStatement sta = null;
        List<Tag> list = null;
        try {
            WashingConnect washingConnect = WashingConnect.getInstance();
            Connection connection = washingConnect.getConn();

            String sql = "CALL addTagFromIntel(?,?);";
            sta = connection.prepareStatement(sql);
            sta.setInt(1, Integer.parseInt(intel.getIntelID()));
            sta.setInt(2, Integer.parseInt(data.getDataID()));
            sta.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("error when copy");
        } finally {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
