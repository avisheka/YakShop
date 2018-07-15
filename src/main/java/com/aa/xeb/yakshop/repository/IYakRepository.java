package com.aa.xeb.yakshop.repository;

import com.aa.xeb.yakshop.model.YakYield;

public interface IYakRepository {
     public YakYield getYieldForYak(int id, int elapsedTimeInDays);
     public YakYield getTotalYield(int elapsedTimeInDays);
     public void saveYieldForDay(int id,int elapsedTimeInDays,YakYield yield);
}