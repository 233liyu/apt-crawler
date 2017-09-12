package main.database.databaseconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WashingConnect {

    private static final String driver = "com.mysql.jdbc.Driver"; //数据库驱动
    //连接数据库的URL地址
    private static final String url="jdbc:mysql://123.206.16.129:3306/apt?useSSL=false"+
            "&useUnicode=yes"+
            "&characterEncoding=UTF-8";
    private static final String username = "pink";//数据库的用户名
    private static final String password = "pink_man";//数据库的密码
    private Connection conn = null;

    public Connection getConn() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
        }
        return conn;
    }

    public void close(){
        try {
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static WashingConnect ourInstance = new WashingConnect();

    public static WashingConnect getInstance() {
        return ourInstance;
    }

    private WashingConnect() {
        try {
            Class.forName(driver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
