package sudoku;

public interface FieldInterface {
	
	public boolean generate();
	public int task(int difficult);
	public boolean solve();
	public boolean check();
	public void print();

}
