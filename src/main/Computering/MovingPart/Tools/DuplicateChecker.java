package main.Computering.MovingPart.Tools;

import main.Beans.Intel;

import java.util.HashMap;
import java.util.Map;

public class DuplicateChecker {

    public static Map<String, Intel> map = new HashMap<>();

    public static boolean isIntelAddable(Intel intel){
        // TODO: 待完善详细页面
        if(map.containsKey(intel.URL)){
            return false;
        } else {
            map.put(intel.URL,intel);
            return true;
        }

    }



}
