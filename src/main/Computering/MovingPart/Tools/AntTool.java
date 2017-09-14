package main.Computering.MovingPart.Tools;

import main.Beans.Data;
import main.Beans.Intel;
import main.database.databaseservece.Intelseverce;
import main.database.databaseservece.TagImp;
import main.database.databaseservece.WasherDataDao;
import main.database.dbInterface.DataTagInterface;
import main.database.dbInterface.IntelDao;
import main.database.dbInterface.IntelTagInterface;
import main.database.dbInterface.WasherData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AntTool {

    public static List<Intel> getUpdateIntel(Date date) throws SQLException {
        IntelDao dao = new Intelseverce();
        return dao.findIntelBefore(new java.sql.Date(date.getTime()));
    }

    public static void AddToData(Intel intel){
        WasherData dao = new WasherDataDao();
        Data data = new Data();

        data.setAuthor(intel.getAuthor());
        data.setContent(intel.getContent());
        data.setCrawlDate(intel.getCrawlDate());
        data.setDataID("0");
        data.setSites(intel.getSites());
        data.setSourceIntelID(intel.IntelID);
        data.setTitle(intel.getTitle());
        data.setPublishDate(intel.getPublishDate());
        data.setURL(intel.getURL());
        dao.createData(data);

        try {
            dao.LinkDataIntel(data,intel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        IntelTagInterface intelTagInterface = new TagImp();
        try {
            intelTagInterface.copyTagToData(data,intel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void MoveToData(Intel intel, Data data){

        WasherData dao = new WasherDataDao();
        IntelTagInterface intelTagInterface = new TagImp();
        try {
            dao.LinkDataIntel(data, intel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        intel.Author = htmlSafeReplace(intel.URL);

        try {
            intelTagInterface.copyTagToData(data,intel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String htmlSafeReplace(String str){
        str = str.replace("“", "&ldquo;");
        str = str.replace("”", "&rdquo;");
        str = str.replace(" ", "&nbsp;");
        str = str.replace("&", "&amp;");
        str = str.replace("'", "&#39;");
        str = str.replace("’", "&rsquo;");
        str = str.replace("—", "&mdash;");
        str = str.replace("–", "&ndash;");
        return str;
    }

}
