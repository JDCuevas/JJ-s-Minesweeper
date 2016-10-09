import java.awt.Color;
import java.util.Random;

public class GameRules {
	
	private Random generator = new Random();
	MyPanel myPanel = new MyPanel();
	
	public void setBombs(){			//This method sets bombs around the grid
		int bombs = 0;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				myPanel.getBombArray() [i][j] = 0;
				myPanel.getColorArray()[i][j] = Color.WHITE;
			}
			
		}
		while(bombs != 10){ 		//Change to adjust the amount of bombs placed in the grid.
			int xGrid = generator.nextInt(9);
			int yGrid = generator.nextInt(9);
			if(myPanel.getBombArray() [xGrid][yGrid] == 0){
				myPanel.getBombArray() [xGrid][yGrid] = 1;
				myPanel.getColorArray()[xGrid][yGrid] = Color.BLACK; //Added to check if bombs were being place, and to test isBomb method.
				bombs++; 
			} else {
			//Do nothing if location already has a bomb.
			}
		}
		//IGNORE - DO NOT TOUCH Loop to check if its setting bombs. Bombs represented by black squares
//		for(int j = 0; j < MyPanel.TOTAL_COLUMNS; j++){
//			for(int i = 0; i < MyPanel.TOTAL_ROWS - 1; i++){
//				if(myPanel.getBombArray()[j][i] == 1){
//					myPanel.getColorArray()[i][j] = Color.BLACK;					
//				}
//			}
//		}
	}
	public boolean isBomb(int x, int y){	
		if(myPanel.getBombArray()[x][y] == 1){
			System.out.println("Bomb!");
		}
		return true;
	}
	
}
