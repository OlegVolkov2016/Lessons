package library;

// Creational pattern: Factory method to determine common basic functions of child classes

public interface Template {
	
	// change fields
	public void set(Object obj);
	// first initialize if 
	public void firstInit();
	// print information
	public void print(String type);
	// edit information
	public void edit(String type, boolean isNew);
	// menu for printing and editing
	public boolean menu(String type);

}
