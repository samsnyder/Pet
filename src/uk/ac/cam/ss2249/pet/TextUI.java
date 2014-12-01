package uk.ac.cam.ss2249.pet;

import java.util.Scanner;

import uk.ac.cam.ss2249.hilo.HiLoUI;

public class TextUI implements UI{
	
	Scanner sc = new Scanner(System.in);
	TextUI cont = this;

	@Override
	public void onDead(Character c) {
		System.out.println("Dead!");
		switch(c.getStatus()){
			case STARVED:
				System.out.println("It starved!");
				break;
			case BOREDDEAD:
				System.out.println("Play with it more!");
				break;
			case TIREDDEAD:
				System.out.println("It needed to sleep more!");
				break;
			default:
				System.out.println("Noooo idea why");
				break;
		}
	}

	@Override
	public void timerTick() {
		
	}
	
	@Override
	public void init(){
		inputOption();
	}
	
	public void inputOption(){
		Character c = State.getInstance().getChar();
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. Feed " + c.getName() + "?");
		System.out.println("2. Play with " + c.getName() + "?");
		System.out.println("3. Put " + c.getName() + " to sleep?");
		System.out.println("4. Play HiLo");
		System.out.println("5. Check Status");
		System.out.println("6. Kill " + c.getName() + "!\n");
		String option = sc.nextLine();
		if(option.equals("1"))
			feed();
		else if(option.equals("2"))
			play();
		else if(option.equals("3"))
			sleep();
		else if(option.equals("4"))
			playHiLo();
		else if(option.equals("5"))
			showStatus();
		else if(option.equals("6"))
			die();
	}
	
	void showStatus(){
		Character c = State.getInstance().getChar();
		System.out.println("Stats for your " + c.getTypeName() + " - " + c.getName());
		System.out.println("Happiness - " + Utils.formatP(c.getHappiness()));
		System.out.println("Tiredness - " + Utils.formatP(c.getTiredness()));
		System.out.println("Hungriness - " + Utils.formatP(c.getHungriness()));
		System.out.println("You have " + Utils.formatMon(State.getInstance().getMoney()));
		inputOption();
	}
	
	void feed(){
		Character c = State.getInstance().getChar();
		System.out.println("What food would you like?");
		for(int i=0; i<FoodFactory.foods.length; i++){
			System.out.println((i+1) + ". " + FoodFactory.foods[i]);
		}
		int option = Integer.parseInt(sc.nextLine());
		Food food = new FoodFactory().getFood(option);
		if(food == null){
			System.out.println("I did not recognise that. Try again!");
			feed();
			return;
		}
		if(c.feed(food)){
			System.out.println("Great! " + c.getName() + "'s new hungriness is " + Utils.formatP(c.getHungriness()));
		}else{
			System.out.println("You did not have enough money!");
			System.out.println("You have " + Utils.formatMon(State.getInstance().getMoney()));
			System.out.println("You need " + Utils.formatMon(food.getCost()));
		}
		inputOption();
	}
	
	void play(){
		Character c = State.getInstance().getChar();
		System.out.println("How long would you like to play? (It costs " + Utils.formatMon(c.getCostPerPlayMinute()) + " per minute)");
		float time = Integer.parseInt(sc.nextLine());
		if(c.pet(time)){
			System.out.println("Great! " + c.getName() + "'s new happiness is " + Utils.formatP(c.getHappiness()));
		}else{
			System.out.println("You did not have enough money!");
			System.out.println("You have " + Utils.formatMon(State.getInstance().getMoney()));
			System.out.println("You need " + Utils.formatMon(c.getCostPerPlayMinute() * time));
		}
		inputOption();
	}
	
	void playHiLo(){
		System.out.print("\n\n");
		HiLoUI hilo = new HiLoUI(new HiLoUI.HiLoInterface() {
			
			@Override
			public void end(boolean didWin, int difficulty) {
				if(didWin){
					System.out.println("You won " + Utils.formatMon(HiLoUI.PRIZE) + "!");
					State.getInstance().earnMoney(HiLoUI.PRIZE);
					cont.inputOption();
				}
			}
		});
		hilo.startGame();
	}
	
	void sleep(){
		Character c = State.getInstance().getChar();
		System.out.println("How long would you like to sleep? (It costs " + Utils.formatMon(c.getCostPerSleepMinute()) + " per minute)");
		float time = Integer.parseInt(sc.nextLine());
		if(c.sleep(time)){
			try {
				State.getInstance().stopTimer();
				System.out.println(c.getName() + " will sleep for " + time + " minutes...");
				Thread.sleep(Utils.minsToMillis(time));
				System.out.println("Great! " + c.getName() + "'s new tiredness is " + Utils.formatP(c.getHappiness()));
				State.getInstance().startTimer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("You did not have enough money!");
			System.out.println("You have " + Utils.formatMon(State.getInstance().getMoney()));
			System.out.println("You need " + Utils.formatMon(c.getCostPerSleepMinute() * time));
		}
		inputOption();
	}
	
	void die(){
		Character c = State.getInstance().getChar();
		System.out.println(c.deadMessage());
		System.exit(0);
	}
	
	@Override
	public void newCharacter(){
		System.out.println("Which character would you like?");
		System.out.println("1. Dog");
		System.out.println("2. Fish");
		String option = sc.nextLine();
		System.out.println("What name would you like?");
		String name = sc.nextLine();
		if(option.equals("1"))
			State.getInstance().newCharacter(Utils.Characters.DOG, name);
		else if(option.equals("2"))
			State.getInstance().newCharacter(Utils.Characters.FISH, name);
	}

}
