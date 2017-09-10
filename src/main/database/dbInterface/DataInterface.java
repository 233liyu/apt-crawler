package main.database.dbInterface;

import main.Beans.Data;
import main.Beans.SystemUser;
import main.Beans.Tag;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午1:51
 */
public interface DataInterface {
    // normal user and admin to get data
    // not crash to upper but pass empty

    public Data getDataByID(String ID) throws SQLException;

    public List<Data> getDataByTag(String tag) throws SQLException;

    public List<Data> getDataLimitBeforeDate(Date date, int limits) throws SQLException;//时间之后

    public List<Data> getDataAccordToDateOffset(int Start, int end) throws SQLException;//排序

    public List<Data> searchData(String keyWord) throws SQLException;

    public List<Data> searchDataByTag(Tag tag, String keyWord);

    public List<Data> searchLike(SystemUser systemUser) throws SQLException;

    public  void addLike(SystemUser systemUser,String DataID) throws Exception;

    public  void deleteLike(SystemUser systemUser,String DataID) throws Exception;
}
