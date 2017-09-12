package main.database.dbInterface;

import main.Beans.Data;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午2:02
 */
public interface DataDaoAdmin {
    // catch exception and roll back, then throw a new Exception

    public void UpdateDataByID(Data data) throws Exception;

    // trigger here!
    public void DeleteData(String DataID) throws Exception;

    public void CreateData(Data data) throws Exception;

}
