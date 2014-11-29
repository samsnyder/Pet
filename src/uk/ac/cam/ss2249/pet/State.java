package uk.ac.cam.ss2249.pet;

import uk.ac.cam.ss2249.customs.Dog;
import uk.ac.cam.ss2249.customs.Fish;

public class State {
	private static State singleton = new State();
	private float deltaT = 0.01f;
	
	private float money = 1000;
	private boolean timerEnabled = false;
	
	Character currentChar;
	
	UI ui;
	
	private State(){
		ui = new TextUI();
	}
	
	public static State getInstance(){
		return singleton;
	}
	
	public float getMoney(){
		return money;
	}
	
	boolean payFee(float amount){
		if(money >= amount){
			money -= amount;
			return true;
		}else{
			return false;
		}
	}
	
	void earnMoney(float amount){
		if(amount > 0)
			money += amount;
	}
	
	void startTimer(){
		timerEnabled = true;
		mainTimer.start();
	}
	
	void stopTimer(){
		timerEnabled = false;
	}
	
	void timerTick(){
		if(currentChar != null){
			if(currentChar.decayByTime(deltaT)){
				stopTimer();
				ui.onDead(currentChar);
				currentChar = null;
			}else{
				ui.timerTick();
			}
		}
		

	}
	
	public Character getChar(){
		return currentChar;
	}
	
	void newCharacter(Utils.Characters charType, String name){
		switch(charType){
			case DOG:
				currentChar = new Dog(name);
			case FISH:
				currentChar = new Fish(name);
		}
		startTimer();
	}
	
	Thread mainTimer = new Thread(new Runnable(){
		public void run(){
			while(timerEnabled){
				timerTick();
				try {
					Thread.sleep(Utils.minsToMillis(deltaT));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
}
