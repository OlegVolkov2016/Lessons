package sudoku;

public class SimplePrinter implements FieldPrinter{
	
	// Simple field generator
	private static SimplePrinter instance = new SimplePrinter();
	
	private SimplePrinter() {
		// TODO Auto-generated constructor stub
	}
	
	public static SimplePrinter getInstance() {
		return instance;
	}

	@Override
	public void printField(Object field) {
		// TODO Auto-generated method stub
		if ((field instanceof Field) || (field instanceof BooleanField)) {
			int size;
			String value;
			if (field instanceof Field)
				size = ((Field) field).getField().length;
			else if (field instanceof BooleanField)
				size = ((BooleanField) field).getField().length;
			else
				size = 0;
			if (size > 9)
				System.out.print("   ");
			else
				System.out.print("  ");
			for (int j = 0; j < size; j++)
				if (((size > 9) && (j > 8)) || (size < 10)) {
					System.out.print("  "+(j+1)+" ");
				}
				else
					System.out.print("   "+(j+1)+" ");
			System.out.println(" ");
			printLine(size);
			for (int i = 0; i < size; i++) {
				if (((size > 9) && (i > 8)) || (size < 10)) {
					System.out.print((i+1)+" ");
				}
				else
					System.out.print(" "+(i+1)+" ");
				for (int j = 0; j < size; j++) {
					if (field instanceof Field)
						if (((Field) field).getCell(i,j) == 0)
								value = " ";
						else
							value = ((Field) field).getCell(i,j)+"";
					else if (field instanceof BooleanField)
						if (((BooleanField) field).getCell(i,j) == true)
							value = "X";
						else
							value = " ";
					else
						value = " ";
					if (((size > 9) && (value.length() > 1)) || (size < 10)) {
						System.out.print("| "+value+" ");
					}
					else
						System.out.print("|  "+value+" ");
				}
				System.out.println("|");
				printLine(size);
			}
		}
	}
	
	private void printLine(int size) {
		if (size > 9)
			System.out.print("   ");
		else
			System.out.print("  ");
		for (int j = 0; j < size; j++)
			if (size > 9)
				System.out.print("-----");
			else
				System.out.print("----");
		System.out.println("-");
	}

}
