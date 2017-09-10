package main.database.dbInterface;

import main.Beans.Data;
import main.Beans.Intel;
import main.Beans.Tag;

import java.util.List;

public interface IntelTagInterface {

    public List<Tag> getTagsOfIntel(Intel intel);

    public void copyTagToData(Data data, Intel intel) throws Exception;


}
