import java.awt.Color;
import java.util.Random;

public class GameRules {

	private Random generator = new Random(); 

	public static Color[][] colorArray = new Color[9][10];
	public static boolean[][] bombArray = new boolean[9][9];
	public static boolean[][] flagArray = new boolean[9][9];

	int bombs = 0;

	public void setBombs(){			//This method sets bombs around the grid		
		//		if(bombs == 10){	// Resets previous bombs set
		//			
		//		}
		while(bombs != 10){ 		//Change to adjust the amount of bombs placed in the grid.
			int xGrid = generator.nextInt(9);
			int yGrid = generator.nextInt(9);
			if(getBombArray()[xGrid][yGrid] == false){
				getBombArray()[xGrid][yGrid] = true;
				getColorArray()[xGrid][yGrid] = Color.BLACK; //Added to check if bombs were being place, and to test isBomb method.
				bombs++; 
			} else {
				//Do Nothing
			}
		}
	}

	public void resetGame(){
		for(int i = 0; i < 9; i++){ 
			for(int j = 0; j < 9; j++){
				getBombArray() [i][j] = false; 
				flagArray[i][j] = false;
				getColorArray()[i][j] = Color.LIGHT_GRAY;
			}
		}
		setBombs();
	}

	public boolean isBomb(int x, int y){	
		if(getBombArray()[x][y] == true){
			return true;
		}
		return false;
	}

	public int nearbyBombs(int x, int y) {
		int nearbyBombs = 0;

		for(int i = x - 1; i < x + 2; i++){ // Checks all 8 surrounding cells
			for(int j = y - 1; j < y + 2 ; j++){	
				if((i > -1 && i < 9) && (j > -1 && j < 9)) // Checks for bombs around clicked cells if the cell exists
					if(isBomb(i,j))
						nearbyBombs++;
			}
		}		
		
		return nearbyBombs;
		
	}

	public void setFlag(int x, int y){ //Don't touch - will make a flagArray in my panel to fix single flag bug. And will fix right click event errors. 
		//		for(int i = 0; i < 9; i++){ // 
		//			for(int j = 0; j < 9; j++){
		//				if(isBomb(i,j)){
		//					getColorArray() [i][j] = Color.BLACK; 
		//				}
		//			}
		//		}
		flagArray[x][y] = true;
		getColorArray()[x][y] = Color.RED;
		for(int i = 0; i < 9; i++){  
			for(int j = 0; j < 9; j++){
				if(isFlag(i,j)){
					getColorArray() [i][j] = Color.RED; 
				}
			}
		}
	}

	public boolean isFlag(int x, int y){
		if(flagArray[x][y] == true){
			return true;
		}
		return false;
	}

	public static Color[][] getColorArray() { 
		return colorArray;
	}
	public void setColorArray(Color[][] colorArray) {
		GameRules.colorArray = colorArray;
	}
	public static boolean[][] getBombArray() { 
		return bombArray;
	}
	public void setBombArray(boolean[][] bombArray) {
		GameRules.bombArray = bombArray;
	}
	public void resetFlag(int x, int y){
		flagArray[x][y] = false;
		colorArray[x][y] = Color.LIGHT_GRAY;
	}



}
