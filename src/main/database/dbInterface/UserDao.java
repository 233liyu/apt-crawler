package main.database.dbInterface;

import main.Beans.SystemUser;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午1:13
 */
public interface UserDao {

    public SystemUser findUserByID(String ID) throws SQLException;

    public SystemUser findUserByName(String userName) throws SQLException;

    public void UpdateUser(SystemUser user) throws Exception;

    // TODO: return user id
    public String CreateUser(SystemUser user) throws Exception;

    public String DeleteUser(SystemUser user) throws Exception;

    public String DeleteUser(String userID) throws Exception;

    public List<SystemUser> searchUserByName(String name) throws SQLException;

    public List<SystemUser> searchUserByEmail(String email) throws SQLException;


}
