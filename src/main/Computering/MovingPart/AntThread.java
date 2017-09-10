package main.Computering.MovingPart;

import main.Beans.Intel;
import main.Computering.MovingPart.Tools.AntTool;
import main.Computering.MovingPart.Tools.DuplicateChecker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AntThread extends Thread{

    private MovingManager manager = null;

    private AntThread(){

    }

    public AntThread(MovingManager manager){
        this.manager = manager;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {

        while (true){

            System.out.println("Ant thread running");

            List<Intel> intelList = new ArrayList<>();
            try {
                System.out.println(manager.getLastUpdateTime());
                intelList = AntTool.getUpdateIntel(manager.getLastUpdateTime());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(intelList != null){
                System.out.println("list size : " + intelList.size());
                for (Intel intel : intelList){
                    if(DuplicateChecker.isIntelAddable(intel)){
                        // if the intel is able to be add into system directly
                        AntTool.AddToData(intel);
                        manager.updateTime(new Date(intel.getPublishDate() == null ? manager.lastMoveDate : intel.getPublishDate().getTime()));
                    } else {


                    }
                }
            } else {
                System.out.println("intelList does not existed!");
            }


            try {
                sleep(200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

        }
    }

}
