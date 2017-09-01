package main.database.dbInterface;

import main.Beans.SystemUser;

import java.util.List;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午1:13
 */
public interface UserDao {

    public SystemUser findUserByID(String ID);

    public SystemUser findUserByName(String userName);

    public void UpdateUser(SystemUser user);

    // TODO: return user id
    public String CreateUser(SystemUser user);

    public String DeleteUser(SystemUser user);

    public String DeleteUser(String userID);

    public List<SystemUser> searchUserByName(String name);

    public List<SystemUser> searchUserByEmail(String email);


}
