import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameRules {

	private Random generator = new Random(); 

	public static Color[][] colorArray = new Color[9][10];
	public static boolean[][] bombArray = new boolean[9][9];
	public static boolean[][] flagArray = new boolean[9][9];
	public int bombs = 10;
	
	public void setBombs(){			//Sets bombs around the grid.
		int bombs = 0;
		while(bombs != this.bombs){ 		//Change to adjust the amount of bombs placed in the grid.
			int xGrid = generator.nextInt(9);
			int yGrid = generator.nextInt(9);
			
			if(bombArray[xGrid][yGrid] == false){
				bombArray[xGrid][yGrid] = true;
				bombs++;
			} else {
				//Do Nothing
			}
		}
		
		for(int i = 0; i < 9; i++){  //Loops to get bomb coordinates for bug fixing.
			for(int j = 0; j < 9; j++){
				if(isBomb(i,j)){
					System.out.println("BOMB: " + i + ", " + j);
				}
			}
		}
	}

	public boolean isBomb(int x, int y){ //Getter for bombArray.
		return bombArray[x][y];
	}

	public int nearbyBombs(int x, int y) { //Counts bombs in surrounding cells.
		int nearbyBombs = 0;
		
		// Checks all 8 surrounding cells.
		for(int i = x - 1; i < x + 2; i++){
			for(int j = y - 1; j < y + 2 ; j++){	
				//Makes sure selection is in grid.
				if((i >= 0 && i < 9) && (j >= 0 && j < 9))
					if(isBomb(i,j))
						nearbyBombs++;
			}
		}		
		return nearbyBombs;

	}
	
	public void resetGame(){ //Resets the game.
		for(int i = 0; i < 9; i++){ 
			for(int j = 0; j < 9; j++){
				bombArray[i][j] = false; 
				flagArray[i][j] = false;
				colorArray[i][j] = Color.WHITE;
			}
		}
		setBombs();
	}
	
	public void gameOver(){ //Displays bombs on grid.
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(isBomb(i,j)){
					colorArray[i][j] = Color.BLACK;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Game over! Nice try though.\n(Press the black square at the bottom left to reset)");
	}
	
	public void wonGame(){ //Check if all bombs have been flagged and all empty cells, revealed
		int bombCount = 0;
		int emptyCount = 0;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(isBomb(i,j) && isFlag(i,j)){
					bombCount++;
				} else if(isRevealed(i,j)){
					emptyCount++;
				}
			}
		}
		if(bombCount == bombs && emptyCount == ((MyPanel.TOTAL_COLUMNS * (MyPanel.TOTAL_ROWS - 1)) - bombs)){
			JOptionPane.showMessageDialog(null, "You win!\n(Press the black square at the bottom left to reset)");
		}
	}
	
	public void setFlag(int x, int y){ //Sets flags.
		flagArray[x][y] = true;
		getColorArray()[x][y] = Color.RED;
	}

	public void removeFlag(int x, int y) { //Removes flags.
		//Fix error here when unflagging.
		colorArray[x][y] = Color.WHITE;	
		
		flagArray[x][y] = false;
	}

	public boolean isFlag(int x, int y){ //Getter for flagArray.
		return flagArray[x][y];
	}
	
	public boolean isRevealed(int x, int y){ //Checks to see if cell is revealed.
		if(colorArray[x][y].equals(Color.LIGHT_GRAY)){
			return true;
		}
		return false;
	}
	
	public void emptyCellReveal(int x, int y){ //Recursive method to chain reveal cells without nearbyBombs. Stops at cells with nearbyBombs > 0.
		//Checks to see if coordinates are inside grid.
		if((x >= 0 && x < 9) && (y >= 0 && y < 9)){ 
			if(isBomb(x,y) == false && isRevealed(x,y) == false){
				colorArray[x][y] = Color.LIGHT_GRAY;
				if(nearbyBombs(x,y) > 0){
					return;
				} else {
					//Checks cells in all four cardinal directions.
					emptyCellReveal(x + 1, y);
					emptyCellReveal(x, y + 1);
					emptyCellReveal(x - 1, y);
					emptyCellReveal(x, y - 1);
				}
			}
		}
	}

	public static Color[][] getColorArray() { 
		return colorArray;
	}

}
