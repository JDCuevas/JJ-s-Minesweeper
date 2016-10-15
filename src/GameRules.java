import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameRules {

	private Random generator = new Random(); 

	public static Color[][] colorArray = new Color[9][10];
	public static boolean[][] bombArray = new boolean[9][9];
	public static boolean[][] flagArray = new boolean[9][9];
	public int BOMBS = 10;

	public void setBombs(){			//Sets bombs around the grid.
		int bombs = 0;
		while(bombs != this.BOMBS){ 		//Change to adjust the amount of bombs placed in the grid.
			int xGrid = generator.nextInt(9);
			int yGrid = generator.nextInt(9);

			if(bombArray[xGrid][yGrid] == false){
				bombArray[xGrid][yGrid] = true;
				bombs++;
			} else {
				//Do Nothing
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

	public void revealBombs(){ 
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(isBomb(i,j)&&(!isFlag(i,j))){ // Reveals bombs that weren't flagged
					colorArray[i][j] = Color.BLACK;
				}
			}
		}
	}

	public void gameOver(){ //Game over message with retry option.
		if (JOptionPane.showConfirmDialog(null, "Game over! Nice try though. Want to try again?", "Wow, what a shame you had to see this window.",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			resetGame();
		} else {
			//'No' option
			System.exit(0);
		}
	}

	public void wonGame(){ //Check if all empty cells have been revealed.
		int revealedCount = 0;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(isRevealed(i,j)){
					revealedCount++;
				}
			}
		}
		if(revealedCount == ((MyPanel.TOTAL_COLUMNS * (MyPanel.TOTAL_ROWS)) - BOMBS)){
			if (JOptionPane.showConfirmDialog(null, "You win! But can you do that again?", "Wow, nice! Pat yourself in the back.",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				resetGame();
			} else {
				//'No' option
				System.exit(0);
			}
		}
	}

	public void setFlag(int x, int y){ //Sets flags.
		if(!isRevealed(x,y)){
			flagArray[x][y] = true;
			getColorArray()[x][y] = Color.RED;
		}
	}

	public void removeFlag(int x, int y) { //Removes flags.
		//Fix error here when unflagging
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
			if(!isBomb(x,y) && !isRevealed(x,y)){
				colorArray[x][y] = Color.LIGHT_GRAY;
				if(nearbyBombs(x,y) > 0){
					return;
				} else {
					//Checks cells in all eight directions.
					emptyCellReveal(x + 1, y);
					emptyCellReveal(x, y + 1);
					emptyCellReveal(x - 1, y);
					emptyCellReveal(x, y - 1);
					emptyCellReveal(x-1,y-1);
					emptyCellReveal(x-1,y+1);
					emptyCellReveal(x+1,y-1);
					emptyCellReveal(x+1,y+1);

					//					for(int i = x - 1; i < x + 2; i++){
					//						for(int j = y - 1; j < y + 2 ; j++){	
					//							emptyCellReveal(i,j);	
					//						}
					//					}	
				}
			}
		}
	}

	public static Color[][] getColorArray() { 
		return colorArray;
	}

}
