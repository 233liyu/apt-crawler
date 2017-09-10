package main.database.dbInterface;

import main.Beans.Data;
import main.Beans.Intel;

public interface WasherData {

    public void createData(Data data);

    public void updateData(Data data) throws Exception;

    public void LinkDataIntel(Data data, Intel intel) throws Exception;



}
