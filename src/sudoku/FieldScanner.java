package sudoku;

public interface FieldScanner {

	// Common input methods
	public boolean isRepeat(String question);
	public int makeIntChoise(int from, int to);
	public String makeNameChoise();
	public String makeStringChoise();
	
}
