package uk.ac.cam.ss2249.pet;

import java.text.NumberFormat;
import java.util.Locale;

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
	
	public enum Characters {
		DOG, FISH
	}
}
