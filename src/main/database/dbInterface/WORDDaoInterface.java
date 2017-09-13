package main.database.dbInterface;

import main.Beans.Tag;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: cui
 * create time: 下午1:27
 */
public interface WORDDaoInterface {

    public List<String> getWORDAccordToWORDOffset(int Start, int end) throws SQLException;//排序

    public void refuceWORDByname(String word) throws Exception;

}
