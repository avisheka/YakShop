package com.aa.xeb.yakshop.service;

import com.aa.xeb.yakshop.model.Yak;
import com.aa.xeb.yakshop.model.YakYield;

public interface IYakService {
    public YakYield getTotalYakYield(int elapsedTimeInDays);
    public YakYield getYakYieldForAYak(Yak yak, int elapsedTimeInDays);
    public void calculateAndSaveYieldForDay(Yak yak,int elapsedTimeInDays);
}