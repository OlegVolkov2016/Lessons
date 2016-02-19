package sudoku;

public class RowCalculator implements FieldCalculator {
	
	// Row field calculator
	private static RowCalculator instance = new RowCalculator();
	
	private RowCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public static RowCalculator getInstance() {
		return instance;
	}
	
	// Calculate row values;
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
				value = source.getCell(i,j);
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
