package main.database.dbInterface;


import main.Beans.Data;
import main.Beans.SystemUser;
import main.Beans.Tag;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DataHistory {
    public void AddHistory(SystemUser user,String dataID);
    public List<Data> GetHistory(SystemUser user) throws SQLException;
    public void DeleteAllHistory(SystemUser user) throws  SQLException;
    public void DeleteSingleHistory(SystemUser user,String dataID)throws  SQLException;
}
