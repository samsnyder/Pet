package uk.ac.cam.ss2249.hilo;

import java.util.Scanner;

public class HiLoUI {
	private Engine e;
	private Scanner sc = new Scanner(System.in);
	HiLoInterface interf;
	public static float PRIZE = 500;
	
	public HiLoUI(HiLoInterface i){
		interf = i;
		e = new Engine();
		System.out.println("Welcome to HiLo!");
	}

	public void startGame(){
		askForDifficulty();
		e.initialise();
		System.out.println("The number is between 1 and " + e.getMaxNum() + ". Let's begin!");
		playRound();
	}
	
	private void playRound(){
		System.out.print("What is your guess? ");
		int guess = 0;
		try{
			guess = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e){
			System.out.println("I did not understand that input, please guess again.");
			playRound();
			return;
		}
		int response = e.guess(guess);
		switch(response){
			case Engine.HIGHER:
				System.out.println("Go higher.");
				break;
			case Engine.LOWER:
				System.out.println("Go lower.");
				break;
			case Engine.CORRECT:
				wonGame();
				return;
			case Engine.GAME_OVER:
				gameOver();
				return;
		}
		System.out.println("You have " + e.guessesRemaining() + " guesses remaining. Try again!\n");
		playRound();
	}
	
	private void wonGame(){
		System.out.println("\nYou are correct!");
		interf.end(true, e.getDifficulty());
	}
	
	private void gameOver(){
		System.out.println("Game over! You ran out of guesses.");
		interf.end(false, e.getDifficulty());
	}
	
	private void askForDifficulty(){
		System.out.print("Game difficulty? (easy, medium or hard): ");
		String reply = sc.nextLine();
		if(reply.equals("easy") || reply.equals("e"))
			e.setDifficulty(Engine.EASY);
		else if(reply.equals("medium") || reply.equals("m"))
			e.setDifficulty(Engine.MEDIUM);
		else if(reply.equals("hard") || reply.equals("h"))
			e.setDifficulty(Engine.HARD);
	}
	
	public interface HiLoInterface{
		void end(boolean didWin, int difficulty);
	}
	
}
