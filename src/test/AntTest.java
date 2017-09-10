package test;

import main.Beans.Intel;
import main.Computering.MovingPart.MovingManager;
import main.database.databaseservece.Intelseverce;
import main.database.dbInterface.IntelDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AntTest {
    public static void main(String args[]) throws SQLException {
//
//        IntelDao dao = new Intelseverce();
//        List<Intel> list = dao.findIntelBefore(new java.sql.Date(0L));
////        List<Intel> list = dao.findIntelBefore(new java.sql.Date(0L));
//
//        if (list == null){
//            System.out.println("hhhhhhhhhhhhh");
//        }
//
//        for (Intel intel : list){
//            System.out.println(intel.getIntelID());
//        }
        MovingManager manager = MovingManager.getInstance();
        manager.startThread();

    }

}
