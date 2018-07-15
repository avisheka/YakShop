package com.aa.xeb.yakshop.service;


import com.aa.xeb.yakshop.model.Yak;
import com.aa.xeb.yakshop.model.YakYield;
import com.aa.xeb.yakshop.repository.IYakRepository;
import com.aa.xeb.yakshop.repository.YakRepositoryImpl;


public class YakServiceImpl implements IYakService {
    private IYakRepository yakStore;
    
    public  YakServiceImpl(){
        yakStore = new YakRepositoryImpl();
    }
    
    public YakYield getTotalYakYield(int elapsedTimeInDays) {
        return yakStore.getTotalYield(elapsedTimeInDays);
    }

    public YakYield getYakYieldForAYak(Yak yak, int elapsedTimeInDays) {
        if(yakStore.getYieldForYak(yak.getId(),elapsedTimeInDays) == null){
            calculateAndSaveYieldForDay(yak,elapsedTimeInDays);
        }
        return yakStore.getYieldForYak(yak.getId(),elapsedTimeInDays);
    }

    public void calculateAndSaveYieldForDay(Yak yak, int elapsedTimeInDays) {
        YakYield yield = yak.computeYakYieldForDay(elapsedTimeInDays);
        yakStore.saveYieldForDay(yak.getId(),elapsedTimeInDays,yield);
    }
}