package test;

import main.Beans.Intel;
import main.Beans.SystemUser;
import main.database.databaseservece.Intelseverce;
import main.Beans.*;
import main.database.databaseservece.*;
import main.database.databaseservece.Userseverce;
import main.database.dbInterface.IntelDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import main.database.dbInterface.*;

public class testclass {
    public static void main(String[] args)throws Exception {
        IntelDao intelDao=new Intelseverce();
        DataInterface dataInterface=new Dataservece();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, +2);
        System.out.println(cal.getTime());
        List<Intel> list = intelDao.findIntelBefore(new java.sql.Date(cal.getTime().getTime()));
//        List<Intel> list = intelDao.findIntelBefore(new java.sql.Date(cal.getTime().getTime()));
//        List<Intel> list1 = intelDao.findIntelbetween(new java.sql.Date(cal.getTime().getTime()), new java.sql.Date(new Date().getTime()));
//        List<Data> list1 = dataInterface.getDataLimitBeforeDate(new java.sql.Date(cal.getTime().getTime()),2);
        Date dt=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(matter1.format(dt));

        countDATA countDATA=new countDATAseverce();
        int x1=countDATA.return_apt();
        int x2=countDATA.return_crawl();
        int x3=countDATA.return_tag();
        double x4=countDATA.retern_todayratio();
        java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");

        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(df.format(x4));
//        List<Data> list1 = dataInterface.getDataByTag("1");
//        System.out.println("----------------------------");
//        if(list != null){
//            for (Intel intel : list){
//                System.out.println(intel.getPublishDate());
//            }
//        } else {
//            System.out.println("hahhahaha");
//        }
//
//
//        System.out.println("----------------------------");
//
//        if(list1 != null){
//            for (Data data : list1){
//                System.out.println(data.getContent());
//            }
//        } else {
//            System.out.println("hahhahaha");
//        }
    }

}
