package uk.ac.cam.ss2249.customs;

import uk.ac.cam.ss2249.pet.Food;
import uk.ac.cam.ss2249.pet.FoodFactory;

public class HamburgerFactory implements FoodFactory{

	@Override
	public Food getInstance() {
		return new Hamburger();
	}

	@Override
	public String getDesc() {
		return "Hamburger";
	}

}
