package main.Computering.MovingPart;

import java.util.Date;

public class MovingManager {

    public static Long lastMoveDate;
    private AntThread antThread;

    private static MovingManager ourInstance = new MovingManager();

    public static MovingManager getInstance() {
        return ourInstance;
    }

    public void updateTime(Date date){
        lastMoveDate = date.getTime();
    }

    public Date getLastUpdateTime(){
        return new Date(lastMoveDate);
    }

    private MovingManager() {
        lastMoveDate = 0L;
    }

    public void startThread(){
        antThread = new AntThread(this);
        antThread.start();


    }

}
