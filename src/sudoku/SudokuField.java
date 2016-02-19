package sudoku;

public class SudokuField implements FieldInterface {
	
	// Sudoku field
	private int power;
	private Field field;
	private BooleanField rowValues, columnValues, boxValues;
	private FieldGenerator generator;
	private FieldChecker checker;
	private FieldTasker tasker;
	private FieldSolver solver;
	
	public SudokuField() {
		// TODO Auto-generated constructor stub
		this(Constants.defaultPower.getValue());
	}
	
	public SudokuField(int power) {
		this.power = power;
		this.field = new Field(power);
		this.field.setPrinter(SimplePrinter.getInstance());
		this.rowValues = new BooleanField(power);
		this.rowValues.setPrinter(SimplePrinter.getInstance());
		this.rowValues.setCalculator(RowCalculator.getInstance());
		this.columnValues = new BooleanField(power);
		this.columnValues.setPrinter(SimplePrinter.getInstance());
		this.columnValues.setCalculator(ColumnCalculator.getInstance());
		this.boxValues = new BooleanField(power);
		this.boxValues.setPrinter(SimplePrinter.getInstance());
		this.boxValues.setCalculator(BoxCalculator.getInstance());
		setHandlers(null);
	}
	
	public SudokuField(SudokuField sudokuField) {
		this(sudokuField.getPower());
		setHandlers(sudokuField);
		for (int i = 0; i < Math.pow(this.getPower(),2); i++) 
			for (int j = 0; j < Math.pow(this.getPower(),2); j++)
				this.field.setCell(i,j,sudokuField.getField().getCell(i,j));
		this.getRowValues().getCalculator().calculateField(this.getField(),this.getRowValues());
		this.getColumnValues().getCalculator().calculateField(this.getField(),this.getColumnValues());
		this.getBoxValues().getCalculator().calculateField(this.getField(),this.getBoxValues());
	}
	
	private void setHandlers(SudokuField field) {
		if (field != null) {
			setGenerator(field.getGenerator());
			setChecker(field.getChecker());
			setTasker(field.getTasker());
			setSolver(field.getSolver());
		}
		else {
//			setGenerator(RandomGenerator.getInstance());
			setGenerator(ShuffleGenerator.getInstance());
			setChecker(SimpleChecker.getInstance());
			setTasker(RandomTasker.getInstance());
			setSolver(RandomSolver.getInstance());
		}
	}
	

	@Override
	public boolean generate() {
		// TODO Auto-generated method stub
		return this.getGenerator().generateField(this);
		
	}

	@Override
	public int task(int difficult) {
		// TODO Auto-generated method stub
		return this.getTasker().generateTask(this,difficult);
		
	}

	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		return this.getSolver().solveField(this);	
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return this.getChecker().checkField(this.getField());	
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		this.getField().getPrinter().printField(this.getField());	
	}
	
	public void makeTurn(Turn turn) {
		this.getField().setCell(turn.getRow(),turn.getColumn(),turn.getValue());
		this.getRowValues().setCell(turn.getRow(),turn.getValue()-1,true);
		this.getColumnValues().setCell(turn.getColumn(),turn.getValue()-1,true);
		this.getBoxValues().setCell(turn.getRow()/this.getPower()*this.getPower()+turn.getColumn()/this.getPower(),turn.getValue()-1,true);
		
	}
	
	public void unmakeTurn(Turn turn) {
		this.getField().setCell(turn.getRow(),turn.getColumn(),0);
		this.getRowValues().setCell(turn.getRow(),turn.getValue()-1,false);
		this.getColumnValues().setCell(turn.getColumn(),turn.getValue()-1,false);
		this.getBoxValues().setCell(turn.getRow()/this.getPower()*this.getPower()+turn.getColumn()/this.getPower(),turn.getValue()-1,false);
	}
	
	//Getters and setters
	public int getPower() {
		return power;
	}
	
	@SuppressWarnings("unused")
	private void setPower(int power) {
		this.power = power;
	}
	
	public Field getField() {
		return field;
	}
	
	public void setField(Field field) {
		this.field = field;
		this.getRowValues().getCalculator().calculateField(this.getField(),this.getRowValues());
		this.getColumnValues().getCalculator().calculateField(this.getField(),this.getColumnValues());
		this.getBoxValues().getCalculator().calculateField(this.getField(),this.getBoxValues());
	}
	
	public BooleanField getRowValues() {
		return rowValues;
	}
	@SuppressWarnings("unused")
	private void setRowValues(BooleanField rowValues) {
		this.rowValues = rowValues;
	}
	
	public BooleanField getColumnValues() {
		return columnValues;
	}
	
	@SuppressWarnings("unused")
	private void setColumnValues(BooleanField columnValues) {
		this.columnValues = columnValues;
	}
	
	public BooleanField getBoxValues() {
		return boxValues;
	}
	
	@SuppressWarnings("unused")
	private void setBoxValues(BooleanField boxValues) {
		this.boxValues = boxValues;
	}
	
	public FieldGenerator getGenerator() {
		return generator;
	}
	
	public void setGenerator(FieldGenerator generator) {
		this.generator = generator;
	}
	
	public FieldChecker getChecker() {
		return checker;
	}
	
	public void setChecker(FieldChecker checker) {
		this.checker = checker;
	}
	
	public FieldTasker getTasker() {
		return tasker;
	}
	
	public void setTasker(FieldTasker tasker) {
		this.tasker = tasker;
	}
	
	public FieldSolver getSolver() {
		return solver;
	}
	
	public void setSolver(FieldSolver solver) {
		this.solver = solver;
	}
	
}
