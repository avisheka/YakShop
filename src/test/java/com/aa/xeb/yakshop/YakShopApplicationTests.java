package com.aa.xeb.yakshop;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aa.xeb.yakshop.model.Yak;
import com.aa.xeb.yakshop.model.YakYield;
import com.aa.xeb.yakshop.util.XmlIpReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YakShopApplicationTests {

	@Test
	public void testYakShopApplication1() {
		int elapsedTimeInDays = 13;
		String fileName="src/test/resources/herd.xml";
		File file = new File(fileName);
		List<Yak> yakList = new XmlIpReader(file).read();

		YakYield totalYakYield = YakShopApplication.getTotalYakYield(yakList, elapsedTimeInDays);
		YakShopApplication.show(elapsedTimeInDays,totalYakYield,yakList);
	}

	@Test
	public void testYakShopApplication2() {
		int elapsedTimeInDays = 14;
		String fileName="src/test/resources/herd.xml";
		File file = new File(fileName);
		List<Yak> yakList = new XmlIpReader(file).read();
		
		YakYield totalYakYield = YakShopApplication.getTotalYakYield(yakList, elapsedTimeInDays);
		YakShopApplication.show(elapsedTimeInDays,totalYakYield,yakList);
	}
}
