package uk.ac.cam.ss2249.hilo;

import java.util.Random;

public class Engine {
	private int maxNum = 10;
	private int maxGuesses = 10;
	
	public final static int LOWER = 0;
	public final static int HIGHER = 1;
	public final static int CORRECT = 2;
	
	public final static int EASY = 3;
	public final static int MEDIUM = 4;
	public final static int HARD = 5;
	
	public final static int GAME_OVER = 6;
	
	private int currentNum;
	private int currentGuesses;
	private Random r = new Random();
	
	private int difficulty = 0;
	
	public void setDifficulty(int diff){
		difficulty = diff;
		switch(diff){
			case EASY:
				maxNum = 20;
				maxGuesses = 10;
				break;
			case MEDIUM:
				maxNum = 40;
				maxGuesses = 7;
				break;
			case HARD:
				maxNum = 60;
				maxGuesses = 5;
				break;
		}
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public void initialise(){
		currentNum = 1 + r.nextInt(maxNum);
		currentGuesses = 0;
	}
	
	public int guess(int guess){
		if(guess == currentNum)
			return CORRECT;
		currentGuesses++;
		if(currentGuesses >= maxGuesses)
			return GAME_OVER;
		if(guess < currentNum)
			return HIGHER;
		return LOWER;
	}
	
	public int getCurrentGuesses(){
		return currentGuesses;
	}
	
	public int guessesRemaining(){
		return maxGuesses - currentGuesses;
	}
	
	public int getMaxNum(){
		return maxNum;
	}
}
