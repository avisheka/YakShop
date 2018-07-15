package com.aa.xeb.yakshop.model;

import com.aa.xeb.yakshop.util.YakUtils;

import exception.YakException;

public class Yak {
	private String name;
	private double age = 0; // age in yrs
	private Sex sex;
	private double ageAtLastShave = 0;
	private int id = 0;

	public enum Sex {
		M, F
	}

	public int getId() {
		return id;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getAgeAtLastShave() {
		return ageAtLastShave;
	}

	public void setAgeAtLastShave(double ageAtLastShave) {
		this.ageAtLastShave = ageAtLastShave;
	}

	public Yak(int id, String name, double age, Sex sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		ageAtLastShave = age * 100;
		this.id = id;
	}

	/*********************************
	 * Util methods: Age related
	 ********************************/
	private boolean isAliveByAge(int ageInDays) {
		return ageInDays < 1000;
	}

	public boolean isAlive(int elapsedTimeInDays) {
		return calculateCurrentAgeInDays(elapsedTimeInDays) < 1000;
	}

	private int calculateCurrentAgeInDays(int elapsedTimeInDays) {
		return (int) (age * 100 + elapsedTimeInDays);
	}

	/*******************************
	 * Util methods: Skin related
	 *******************************/
	public int getSkinCount(int elapsedTimeInDays) {
		int skinCount = 1; // for day 0
		for (int day = 1; day < elapsedTimeInDays; day++) {
			int currentAgeInDays = calculateCurrentAgeInDays(day);
			try {
				if (canShaveToday(currentAgeInDays)) {
					skinCount++;
					ageAtLastShave = currentAgeInDays;
				}
			} catch (YakException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		return skinCount;
	}

	// At most every 8+D*0.01 days you can again shave a LabYak (D = age in days).
	private boolean canShaveToday(int currentAgeInDays) throws YakException {
		boolean isEligibleForShave = false;
		double allowedGapInShave = (8 + ageAtLastShave * 0.01);
		if (isAlive(currentAgeInDays)) {
			isEligibleForShave = (currentAgeInDays - ageAtLastShave > allowedGapInShave) ? true : false;
		} else
			throw new YakException(YakUtils.YAK_DIED_MSG);

		return isEligibleForShave;
	}

	/********************************
	 * Util methods: Milk related
	 *******************************/
	public double getTotalMilkQuantityProduce(int elapsedTimeInDays) {
		double totalMilkQuantity = 0;
		for (int day = 0; day < elapsedTimeInDays; day++) {
			try {
				totalMilkQuantity = totalMilkQuantity + getMilkQuantityForDay(calculateCurrentAgeInDays(day));
			} catch (YakException ex) {
				System.out.println(ex.getMessage());
				break;
			}
		}
		return totalMilkQuantity;
	}

	public double getMilkQuantityForDay(int currentAgeInDays) throws YakException {
		double milkForToday = 0;
		if (isAliveByAge(currentAgeInDays)) {
			milkForToday = (50 - currentAgeInDays * 0.03);
		} else {
			throw new YakException(YakUtils.YAK_DIED_MSG);
		}
		return milkForToday;
	}

	/********************************
	 * Util methods: compute YakYield
	 *******************************/
	public YakYield computeYakYieldForDay(int elapsedTimeInDays) {
		return new YakYield(getTotalMilkQuantityProduce(elapsedTimeInDays), getSkinCount(elapsedTimeInDays));
	}

	/********************************
	 * Util methods: Display output
	 *******************************/
	public String displayOp(int elapsedTimeInDays) {
		return isAlive(elapsedTimeInDays) ? name + " " + (age * 100 + elapsedTimeInDays) / 100 + " years old"
				: YakUtils.YAK_DIED_MSG;
	}
}
