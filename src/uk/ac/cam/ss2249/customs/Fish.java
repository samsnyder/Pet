package uk.ac.cam.ss2249.customs;

import uk.ac.cam.ss2249.pet.Character;

public class Fish extends Character{
	private String typeName = "Fish";
	
	public Fish(String n){
		super();
		this.setPlayPercentPerMinute(0.1f);
		this.setBorednessPerMinute(0.1f);
		this.setTirednessPerMinute(0.1f);
		this.setHungrinessPerMinute(0.3f);
		this.setCostPerPlayMinute(50);
		this.setName(n);
	}
	
	public String getTypeName(){
		return typeName;
	}

	@Override
	public String deadMessage() {
		return "But do I sink or float when dead? :o";
	}
}
