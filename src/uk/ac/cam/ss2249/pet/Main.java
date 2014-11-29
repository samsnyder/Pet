package uk.ac.cam.ss2249.pet;

public class Main {
	public static void main(String[] args){
		State st = State.getInstance();
		st.ui.newCharacter();
		st.ui.init();
	}
}
