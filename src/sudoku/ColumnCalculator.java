package sudoku;

public class ColumnCalculator implements FieldCalculator {
	
	// Column field calculator
	private static ColumnCalculator instance = new ColumnCalculator();
	
	private ColumnCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public static ColumnCalculator getInstance() {
		return instance;
	}
	
	// Calculate column values;
	@Override
	public boolean calculateField(Field source, BooleanField result) {
		// TODO Auto-generated method stub
		int power = source.getPower();
		int size = (int) Math.pow(power,2);
		if (result.getField().length != size) return false;
		int value;
		BooleanField temp = new BooleanField(power);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				value = source.getCell(j,i);
				if (value > 0)
					if (!temp.getCell(i,value-1))
						temp.setCell(i,value-1,true);
					else return false;
			}
		}
		result.setField(temp.getField());
		result.setCalculate(true);
		return true;
	}

}
