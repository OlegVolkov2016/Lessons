package lesson7;

public enum Monthdays {
	January(31),
	February(28),
	March(31),
	April(30),
	May(31),
	June(30),
	July(31),
	August(31),
	September(30),
	October(31),
	November(30),
	December(31);
	
	private int count;
	
	Monthdays(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

}
