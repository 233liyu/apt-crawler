package test;
import main.database.databaseservece.APTDao;
import main.database.dbInterface.APTDaoInterface;
import main.database.dbInterface.WORDDaoInterface;
import main.database.databaseservece.wordtotagDAO;
import java.util.ArrayList;
import java.util.List;

public class DAOTEST {
    public static void main(String args[]) throws Exception {
        WORDDaoInterface Daouser = new wordtotagDAO();
        //List<String> x = Daouser.getWORDAccordToWORDOffset(1, 500);
        Daouser.refuceWORDByname(" ");
        int y=0;
        /*for (String u : x) {
            y++;
            System.out.println(u+"  "+y);

        }*/
    }
}
