package clickmania;

import java.util.Random;

public class FieldC {
	/**
	 * Playing field tiles.
	 */
	private Color[][] colors;
	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;
	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Constructor.
	 * 
	 * @param rowCount
	 *            - row count
	 * @param columnCount
	 *            - column count
	 */
	public FieldC(int rowCount, int columnCount) {
		this.columnCount = columnCount;
		this.rowCount = rowCount;
		colors = new Color[rowCount][columnCount];
		generate();
	}

	/**
	 * generate playing field, generate random numbers which represent game -
	 * first fill arrayList with numbers from 1 to n(row*column-1) and " " than
	 * it shuffle
	 */
	private void generate() {
		Random randomGenerator = new Random();
		Integer[] createRandomNumber = new Integer[getColumnCount() * getRowCount()];
		for (int i = 0; i < getColumnCount() * getRowCount(); i++) {
			createRandomNumber[i] = randomGenerator.nextInt(6) + 1;
		}
		int count = 0;
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				colors[row][column] = new Color(createRandomNumber[count]);
				count++;
			}
		}

	}

	public void remove(int chosenRow, int chosenColumn) {
		int value = getColor(chosenRow, chosenColumn).getValue();
		getColor(chosenRow, chosenColumn).setValue(0);
		removeAdjacentColors(chosenRow, chosenColumn, value);
		setDown();
	}

	private void setDown() {
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				if (colors[row][column].getValue() == 0) {
					for(int rowNext=row;rowNext>=0;rowNext--){
						if(rowNext==0){
							colors[rowNext][column].setValue(0);
						}else{
							colors[rowNext][column].setValue(colors[rowNext-1][column].getValue());
						}
							
					}
				}
			}
		}
		setLeft();
	}

	private void setLeft() {
		for (int column = 0; column < getColumnCount(); column++) {
			if (colors[rowCount - 1][column].getValue() == 0) {
				for (int columnNext = column; columnNext < getColumnCount(); columnNext++) {
					for (int row = 0; row < getRowCount(); row++) {
						if(columnNext==getColumnCount()-1){
							colors[row][columnNext].setValue(0);
						}else{
						colors[row][columnNext].setValue(colors[row][columnNext + 1].getValue());
					}}
				}
			}
		}
	}

	private void removeAdjacentColors(int chosenRow, int chosenColumn, int value) {
		if (chosenRow != 0 && value == getColor(chosenRow - 1, chosenColumn).getValue()) {
			remove(chosenRow - 1, chosenColumn);
		}
		if (chosenRow != getRowCount() - 1 && value == getColor(chosenRow + 1, chosenColumn).getValue()) {
			remove(chosenRow + 1, chosenColumn);
		}
		if (chosenColumn != 0 && value == getColor(chosenRow, chosenColumn - 1).getValue()) {
			remove(chosenRow, chosenColumn - 1);
		}
		if (chosenColumn != getColumnCount() - 1 && value == getColor(chosenRow, chosenColumn + 1).getValue()) {
			remove(chosenRow, chosenColumn + 1);
		}

	}

	/**
	 * Returns true if game is solved, false otherwise.
	 * 
	 * @return true if game is solved, false otherwise
	 */
	public boolean endOfGame() {
		for(int row=0;row<rowCount;row++){
			for(int column=0;column<columnCount;column++){
				if(colors[row][column].getValue()!=0){
					int colorValue = getColor(row, column).getValue();
					if ((row!=0  && colorValue == getColor(row - 1, column)
							.getValue())
							|| (row!=getRowCount()-1 && colorValue == getColor(row + 1, column).getValue())
							|| (column!=0 && colorValue == getColor(row, column - 1).getValue())
							|| (column!=getColumnCount()-1 && colorValue == getColor(row, column + 1).getValue())){
						return false;
					}
				}
			}
		}
		return true;
	}

	public Color getColor(int row, int column) {
		return colors[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

}
