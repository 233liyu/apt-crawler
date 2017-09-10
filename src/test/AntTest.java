package test;

import main.Beans.Data;
import main.Beans.Intel;
import main.Computering.DataCal.wordCloud.wordCloudHandler;
import main.Computering.MovingPart.MovingManager;
import main.database.databaseservece.Dataservece;
import main.database.dbInterface.DataInterface;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AntTest {
    public static void main(String args[]) throws SQLException {
//        DataInterface dao = new Dataservece();
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.DATE, -2);
//        System.out.println(cal.getTime());
//        List<Data> a = dao.getDataLimitBeforeDate(cal.getTime(),10);
//
//        for (Data intel : a){
//            System.out.println(intel.getDataID());
//        }


        wordCloudHandler handler = new wordCloudHandler();
        wordCloudHandler.toJsonArray(handler.getResult());

//        MovingManager manager = MovingManager.getInstance();
//        manager.startThread();
    }

}
