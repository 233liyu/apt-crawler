package main.database.databaseconnect;
import java.sql.Connection;
import java.sql.DriverManager;

public class Dataconnect {
    private static final String driver = "com.mysql.jdbc.Driver"; //数据库驱动
    //连接数据库的URL地址
    private static final String url="jdbc:mysql://123.206.16.129:3306/apt?useSSL=false"+
            "&useUnicode=yes"+
            "&characterEncoding=UTF-8";
    private static final String username="pink";//数据库的用户名
    private static final String password="pink_man";//数据库的密码

    private static Connection conn=null;

    static
    {
        try
        {
            Class.forName(driver);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("加载驱动失败");
        }

    }
    public static Connection getConnection() throws Exception
    {

        if(conn==null)
        {
            conn = DriverManager.getConnection(url, username, password);
        }
        return conn;
    }

}
