package uk.ac.cam.ss2249.pet;

import uk.ac.cam.ss2249.customs.Hamburger;
import uk.ac.cam.ss2249.customs.Pizza;

public class FoodFactory {
	public Food getFood(int index){
		switch(index){
			case 1:
				return new Hamburger();
			case 2:
				return new Pizza();
		}
		return null;
	}
	
	public static String[] foods = {"Hamburger", "Pizza"};
}