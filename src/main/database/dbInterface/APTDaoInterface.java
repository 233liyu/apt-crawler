package main.database.dbInterface;

import main.Beans.Data;
import main.Beans.Intel;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: cui
 * create time: 下午1:27
 */
public interface APTDaoInterface {

    public List<String> getAPTDataAccordToDateOffset(int Start, int end) throws SQLException;//排序


}
