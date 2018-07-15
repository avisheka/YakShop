package com.aa.xeb.yakshop;

import java.io.File;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aa.xeb.yakshop.model.Yak;
import com.aa.xeb.yakshop.model.YakYield;
import com.aa.xeb.yakshop.service.IYakService;
import com.aa.xeb.yakshop.service.YakServiceImpl;
import com.aa.xeb.yakshop.util.XmlIpReader;

@SpringBootApplication
public class YakShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(YakShopApplication.class, args);
    	String fileName="src/main/resources/herd.xml";
		File file = new File(fileName);
        int elapsedTimeInDays = 13;
        List<Yak> yakList = new XmlIpReader(file).read();
        
        YakYield totalYakYield = getTotalYakYield(yakList, elapsedTimeInDays);
        show(elapsedTimeInDays,totalYakYield,yakList);
	}
	
	public static YakYield getTotalYakYield(List<Yak> yakList, int elapsedTimeInDays) {
		IYakService service = new YakServiceImpl();
        for(Yak yak : yakList){
            service.calculateAndSaveYieldForDay(yak, elapsedTimeInDays);
        }
        YakYield totalYakYield = service.getTotalYakYield(elapsedTimeInDays);
        return totalYakYield;
	}
	
    public static void show(int forDays, YakYield yield, List<Yak> yakList){
        System.out.println("T = "+forDays+"\n\n");

        System.out.println("In Stock:");
        System.out.println("\t\t"+yield.getMilk()+" liters of milk");
        System.out.println("\t\t"+yield.getSkin()+" skins of wool");
        System.out.println("Herd:\n\n");
        for(Yak yak : yakList){
            System.out.println(yak.displayOp(forDays));
        }
    }
}
