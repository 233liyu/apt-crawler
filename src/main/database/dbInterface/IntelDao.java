package main.database.dbInterface;

import main.Beans.Intel;

import java.sql.Date;
import java.util.List;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午1:27
 */
public interface IntelDao {

    public Intel findIntelByID(int ID);

    public List<Intel> findIntelBefore(Date date);

    public List<Intel> findIntelbetween(Date start_time, Date end_time);


}
