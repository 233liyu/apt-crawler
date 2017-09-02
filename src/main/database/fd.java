package main.database;

import main.Beans.SystemUser;
import main.database.dbInterface.UserDao;

import java.util.List;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午1:12
 */
public class fd implements UserDao {
    @Override
    public SystemUser findUserByID(String ID) {
        return null;
    }

    @Override
    public SystemUser findUserByName(String userName) {
        return null;
    }

    @Override
    public void UpdateUser(SystemUser user) {

    }

    @Override
    public String CreateUser(SystemUser user) {
        return null;
    }

    @Override
    public String DeleteUser(SystemUser user) {
        return null;
    }

    @Override
    public String DeleteUser(String userID) {
        return null;
    }

    @Override
    public List<SystemUser> searchUserByName(String name) {
        return null;
    }

    @Override
    public List<SystemUser> searchUserByEmail(String email) {
        return null;
    }
}
