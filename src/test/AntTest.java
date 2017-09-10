package test;

import main.Beans.Intel;
import main.Computering.MovingPart.MovingManager;
import main.database.databaseservece.Intelseverce;
import main.database.dbInterface.IntelDao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class AntTest {
    public static void main(String args[]) throws SQLException {
//        IntelDao intelDao = new Intelseverce();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new java.util.Date());
//        cal.add(Calendar.DATE, +2);
//        System.out.println(cal.getTime());
//        List<Intel> list = intelDao.findIntelBefore(new java.sql.Date(cal.getTime().getTime()));
//
//        for (Intel intel : list){
//            System.out.println(intel.getIntelID());
//        }


        MovingManager manager = MovingManager.getInstance();
        manager.startThread();
    }

}
