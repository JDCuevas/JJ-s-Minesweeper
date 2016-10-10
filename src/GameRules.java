import java.awt.Color;
import java.util.Random;

public class GameRules {
	
	private Random generator = new Random();
	MyPanel myPanel = new MyPanel();
	int bombs = 0;
	
	public void setBombs(){			//This method sets bombs around the grid		
//		if(bombs == 10){	// Resets previous bombs set
//			
//		}
		while(bombs != 10){ 		//Change to adjust the amount of bombs placed in the grid.
			int xGrid = generator.nextInt(9);
			int yGrid = generator.nextInt(9);
			if(myPanel.getBombArray()[xGrid][yGrid] == false){
				myPanel.getBombArray()[xGrid][yGrid] = true;
				myPanel.getColorArray()[xGrid][yGrid] = Color.BLACK; //Added to check if bombs were being place, and to test isBomb method.
				bombs++; 
			} else {
				//Do Nothing
			}
		}
	}
	
	public void resetBombs(){
		for(int i = 0; i < 9; i++){ // 
			for(int j = 0; j < 9; j++){
				myPanel.getBombArray() [i][j] = false; 
			}
		}
		setBombs();
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
	
	public void flag(int x, int y){ //Don't touch - will make a flagArray in my panel to fix single flag bug. And will fix right click event errors. 
		for(int i = 0; i < 9; i++){ // 
			for(int j = 0; j < 9; j++){
				if(isBomb(i,j)){
					myPanel.getColorArray() [i][j] = Color.BLACK; 
				}
			}
		}
		myPanel.getColorArray()[x][y] = Color.RED;
		for(int i = 0; i < 9; i++){ // 
			for(int j = 0; j < 9; j++){
				if(isFlag(i,j)){
					myPanel.getColorArray() [i][j] = Color.RED; 
				}
			}
		}
	}
	
	public boolean isFlag(int x, int y){
		if(myPanel.getColorArray()[x][y] != Color.RED){
			return false;
		}
		return true;
	}
}
