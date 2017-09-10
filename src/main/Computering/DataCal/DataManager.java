package main.Computering.DataCal;

import main.Computering.DataCal.wordCloud.wordCloudHandler;

public class DataManager {

    private static DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
        wordCloudHandler = new wordCloudHandler();
    }

    public wordCloudHandler wordCloudHandler ;



}
