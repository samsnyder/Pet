package uk.ac.cam.ss2249.customs;

import uk.ac.cam.ss2249.pet.Food;
import uk.ac.cam.ss2249.pet.FoodFactory;

public class PizzaFactory implements FoodFactory{

	@Override
	public Food getInstance() {
		return new Pizza();
	}

	@Override
	public String getDesc() {
		return "Pizza";
	}

}
