package library;

public class Book extends Client {
	
	// Book of library
	// discount is price of a book
	// file name with digital version
	private String fileName;
	// state of location 0 - free, 1 - in hall, 2 - in order, 3 - selled... 
	private int state; 

	public Book() {
		// TODO Auto-generated constructor stub
		super();
		fileName = "";
	}

	public Book(String name, double discount, String filename, int state) {
		super(name, discount);
		// TODO Auto-generated constructor stub
		this.fileName = filename;
		this.state = state;
	}
	
	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		super.set(obj);
		this.fileName = ((Book) obj).getFileName();
		this.state = ((Book) obj).getState();
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		super.firstInit();
	}
	
	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.print("Name of "+type+": "+getName()+", price of sell: "+getDiscount()+"$, now ");
		if (getState() == 0) System.out.println("in library.");
		else if (getState() == 1) System.out.println("in reading hall.");
		else if (getState() == 2) System.out.println("is on hands.");
		else if (getState() == 3) System.out.println("is already selled.");
		System.out.println("Digital version is located by link: "+getFileName());
	}
	
	@Override
	public void edit(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		double discount;
		String fileName;
		int state;
		name = inputName(type,isNew);
		discount = inputDiscount(type,isNew);
		state = inputState(type,isNew);
		fileName = inputFileName(type, isNew);
//		setName(name);
//		setDiscount(discount);
//		setState(state);
//		setFileName(fileName);
		set(new Book(name,discount,fileName,state));
	}
	
	@Override
	public String inputName(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Name of "+type+": "+getName());
			System.out.print("Enter new name of "+type+" (any symbols): ");
			name = makeStringChoise();
			if (name.equals("")) 
				if (!isNew) name = getName();
				else {
					System.out.println("Wrong name.");
					isMake = false;
				}
		}
		while(!isMake);
		return name;
	}
	
	@Override
	public double inputDiscount(String type, boolean isNew) {
		// TODO Auto-generated method stub
		double discount;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Current price: "+getDiscount()+"$.");
			System.out.print("Enter new price in $ (from 0 to 10000): ");
			discount = makeDoubleChoise(0,10000);
			if (Double.isNaN(discount)) 
				if (!isNew) discount = getDiscount();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return discount;
	}
	
	public int inputState(String type, boolean isNew) {
		int state;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) {
				System.out.print("Current state: ");
				if (getState() == 0) System.out.println("in library.");
				else if (getState() == 1) System.out.println("in reading hall.");
				else if (getState() == 2) System.out.println("is on hands.");
				else if (getState() == 3) System.out.println("is already selled.");
			}
			System.out.print("Enter new discount state (0 - free, 1 - hall, 2 - hands, 3 - selled): ");
			state = makeIntChoise(0,3);
			if (state == Integer.MIN_VALUE) 
				if (!isNew) state = getState();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return state;
	}
	
	public String inputFileName(String type, boolean isNew) {
		String fileName;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Digital version is located by link: "+getFileName());
			System.out.print("Enter new link (only letters and .): ");
			fileName = makeNameChoise();
			if (fileName.equals("")) 
				if (!isNew) fileName = getFileName();
				else {
					System.out.println("Wrong name.");
					isMake = false;
				}
		}
		while(!isMake);
		return fileName;
	}
	
	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		return super.menu(type);
	}
	
	// Getters and setters
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}

}
