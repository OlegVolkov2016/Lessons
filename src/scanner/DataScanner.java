package scanner;

public interface DataScanner {

	// Common input methods
	public boolean isRepeat(String question);
	public int makeIntChoise(int from, int to);
	public double makeDoubleChoise (double from, double to);
	public String makeNameChoise();
	public String makeStringChoise();
	
}
