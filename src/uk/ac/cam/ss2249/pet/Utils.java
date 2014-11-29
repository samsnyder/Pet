package uk.ac.cam.ss2249.pet;

import java.text.NumberFormat;
import java.util.Locale;

import uk.ac.cam.ss2249.customs.HamburgerFactory;
import uk.ac.cam.ss2249.customs.PizzaFactory;

public class Utils {
	static long minsToMillis(float mins){
		return (long) (mins * 60000);
	}
	
	static String formatP(float p){
		return ((int) (p * 100)) + "%";
	}
	
	static String formatMon(float mon){
		return NumberFormat.getCurrencyInstance(Locale.UK).format(mon / 100f);
	}
	
	static FoodFactory[] food = {new HamburgerFactory(), new PizzaFactory()};
	
	public enum Characters {
		DOG, FISH
	}
}
