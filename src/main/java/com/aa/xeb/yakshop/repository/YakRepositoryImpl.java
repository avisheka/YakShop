package com.aa.xeb.yakshop.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.aa.xeb.yakshop.model.YakYield;

public class YakRepositoryImpl  implements  IYakRepository{

    private Map<Integer,Map<Integer,YakYield>> yakDataMap = new HashMap<Integer,Map<Integer,YakYield>>();
    
    public YakYield getYieldForYak(int id, int elapsedTimeInDays) {
        Map<Integer,YakYield> dayYieldMap = yakDataMap.get(id);
        return dayYieldMap.get(elapsedTimeInDays);
    }

    public YakYield getTotalYield(int elapsedTimeInDays) {
        double totalMilk = 0;
        int totalSkins = 0;
        Iterator<Entry<Integer,Map<Integer,YakYield>>> it = yakDataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer,Map<Integer,YakYield>> pair = it.next();
            YakYield yield = pair.getValue().get(elapsedTimeInDays);
            totalMilk += yield.getMilk();
            totalSkins += yield.getSkin();
        }
        return  new YakYield(totalMilk,totalSkins);
    }

    public void saveYieldForDay(int id, int elapsedTimeInDays, YakYield yield) {
        Map<Integer,YakYield> dayYieldMap = yakDataMap.get(id);
        if(dayYieldMap == null) {
            dayYieldMap = new HashMap<Integer, YakYield>();
            yakDataMap.put(id,dayYieldMap);
        }
        dayYieldMap.put(elapsedTimeInDays,yield);
    }
}