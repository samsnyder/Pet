package uk.ac.cam.ss2249.pet;

public abstract class Food {
	private float cals;
	private float cost;
	
	public float getCals(){
		return cals;
	}
	
	public float getCost(){
		return cost;
	}
	
	public void setCals(float c){
		cals = c;
	}
	
	public void setCost(float c){
		cost = c;
	}
}
