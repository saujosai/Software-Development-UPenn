public class NewTable {

	private Card[][] grid = new Card[5][];

	grid[0] = new Card[5];
	grid[1] = new Card[5];
	grid[2] = new Card[3];
	grid[3] = new Card[3];
	grid[4] = new Card[5];

	public void showNewTable(){
		int positionNumber = 1;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == null) {
					System.out.format("%5s", positionNumber);
				} else {
					System.out.format("%5s", grid[i][j].getCard());
				}
				positionNumber++;
			}
		}
	}
	
	public static void main(String[] args) {
		NewTable myTable = new NewTable();
		myTable.showNewTable();
			
	}

}
