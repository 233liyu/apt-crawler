package main.database.dbInterface;

import main.Beans.Data;
import main.Beans.Tag;

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

    public Data getDataByID(String ID);

    public List<Data> getDataByTag(String tag);

    public List<Data> getDataLimitBeforeDate(Date date, int limits);

    public List<Data> getDataAccordToDateOffset(int Start, int end);

    public List<Data> searchData(String keyWord);

    public List<Data> searchDataByTag(Tag tag, String keyWord);
}
