package uk.ac.cam.ss2249.pet;

public abstract class Character {
	private float hungriness = 0f;
	private float happiness = 1f;
	private float tiredness = 0f;
	
	private float playPercentPerMinute = 0.05f;
	private float hungrinessPerMinute = 0.1f;
	private float borednessPerMinute = 0.1f;
	private float tirednessPerMinute = 0.1f;
	private float costPerPlayMinute = 50f;
	private float costPerSleepMinute = 20f;
	
	private String name;
	
	private Status status = Status.FINE;
	private float sleepPerMinute = 0.2f;
	private float hungrinessPerCal = 0.0005f;
	
	public abstract String deadMessage();
	
	void feed(Food f) throws NotEnoughMoneyException{
		State.getInstance().payFee(f.getCost());
		this.setHungriness(hungriness - f.getCals() * hungrinessPerCal);
	}
	
	void pet(float minutes) throws NotEnoughMoneyException{
		State.getInstance().payFee(costPerPlayMinute * minutes);
		this.setHappiness(happiness + minutes * playPercentPerMinute);
	}
	
	void sleep(float time) throws NotEnoughMoneyException{
		State.getInstance().payFee(costPerSleepMinute * time);
		this.setTiredness(tiredness - sleepPerMinute * time);
	}
	
	boolean decayByTime(float deltaT){
		hungriness += deltaT * hungrinessPerMinute;
		happiness -= deltaT * borednessPerMinute;
		tiredness += deltaT * tirednessPerMinute;
		if(checkDead())
			return true;
		else{
			checkStatus();
			return false;
		}
	}
	
	void checkStatus(){
		if(tiredness > 0.9f)
			status = Status.TIRED;
		else if(hungriness > 0.9f)
			status = Status.HUNGRY;
		else if(happiness < 0.1f)
			status = Status.BORED;
	}
	
	boolean checkDead(){
		if(tiredness >= 1f)
			status = Status.TIREDDEAD;
		else if(hungriness >= 1f)
			status = Status.STARVED;
		else if(happiness <= 0f)
			status = Status.BOREDDEAD;
		else
			return false;
		return true;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public float getHungriness(){
		return hungriness;
	}
	
	public float getHappiness(){
		return happiness;
	}
	
	public float getTiredness(){
		return tiredness;
	}
	
	public void setHungrinessPerMinute(float h){
		hungrinessPerMinute = h;
	}
	
	public void setBorednessPerMinute(float h){
		borednessPerMinute = h;
	}
	
	public void setTirednessPerMinute(float t){
		tirednessPerMinute = t;
	}
	
	public void setPlayPercentPerMinute(float p){
		playPercentPerMinute = p;
	}
	
	public void setCostPerPlayMinute(float c){
		costPerPlayMinute = c;
	}
	
	public float getCostPerPlayMinute(){
		return costPerPlayMinute;
	}
	
	public void setCostPerSleepMinute(float c){
		costPerSleepMinute = c;
	}
	
	public float getCostPerSleepMinute(){
		return costPerSleepMinute;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public void setHungriness(float h){
		hungriness = checkLimit(h);
	}
	
	public void setHappiness(float h){
		happiness = checkLimit(h);
	}
	
	public void setTiredness(float t){
		tiredness = checkLimit(t);
	}
	
	private float checkLimit(float i){
		if(i < 0f)
			return 0f;
		else if(i > 1f)
			return 1f;
		return i;
	}
	
	public abstract String getTypeName();
	
	public enum Status{
		FINE, HUNGRY, BORED, TIRED, STARVED, BOREDDEAD, TIREDDEAD
	}
}
