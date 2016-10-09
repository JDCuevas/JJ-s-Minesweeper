import java.awt.Color;
import java.util.Random;

public class GameRules {
	
	private Random generator = new Random();
	MyPanel myPanel = new MyPanel();
	
	public void setBombs(){			//This method sets bombs around the grid
		int bombs = 0;
		if(bombs == 10){	// Resets previous bombs set
			for(int i = 0; i < 9; i++){ // 
				for(int j = 0; j < 9; j++){
					myPanel.getBombArray() [i][j] = false; 
				}
			}
		}
		while(bombs != 10){ 		//Change to adjust the amount of bombs placed in the grid.
			int xGrid = generator.nextInt(9);
			int yGrid = generator.nextInt(9);
			if(myPanel.getBombArray() [xGrid][yGrid] == false){
				myPanel.getBombArray() [xGrid][yGrid] = true;
				myPanel.getColorArray()[xGrid][yGrid] = Color.BLACK; //Added to check if bombs were being place, and to test isBomb method.
				bombs++; 
			} else {
				//Do Nothing
			}
		}
	}
	
	public boolean isBomb(int x, int y){	
		if(myPanel.getBombArray()[x][y] == true){
			return true;
		}
		return false;
	}
	
	public void nearbyBombs(int x, int y) {
		int nearbyBombs = 0;

		for(int i = x-1; i < x + 2; i++){ // Checks all 8 surrounding cells
			for(int j = y - 1; j < y + 2 ; j++){	
				if((i > -1 && i < 9) && (j > -1 && j < 9)) // Checks for bombs around clicked cells if the cell exists
					if(isBomb(i,j))
						nearbyBombs++;
			}
		}					
		System.out.println("There are: " + nearbyBombs + " bombs around this cell");
	}
	
}
