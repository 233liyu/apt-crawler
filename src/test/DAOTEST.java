package test;
import main.database.databaseservece.APTDao;
import main.database.dbInterface.APTDaoInterface;
import java.util.ArrayList;
import java.util.List;

public class DAOTEST {
    public static void main(String args[]) throws Exception {
        APTDaoInterface Daouser = new APTDao();
        List<String> x = Daouser.getAPTDataAccordToDateOffset(1, 500);
        int y=0;
        for (String u : x) {
            y++;
            System.out.println(u+" "+y);
        }
    }
}
