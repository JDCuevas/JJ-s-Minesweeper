import java.util.Random;

public class GameRules {
	
	
	public int[][] bombArray = new int[MyPanel.TOTAL_ROWS][MyPanel.TOTAL_COLUMNS];
	private Random generator = new Random();
	GameMouseInteractions gameMouseInteractions = new GameMouseInteractions();
	
	public void setBombs(){			//This method sets bombs around the grid
		int bombs = 0;
		while(bombs != 15){ 		//Change to adjust the amount of bombs placed in the grid.
			int xGrid = generator.nextInt(9);
			int yGrid = generator.nextInt(9);
			if(bombArray [xGrid][yGrid] == 0){
				bombArray [xGrid][yGrid] = 1;
				bombs++; 
			} else {
			//Do nothing if location already has a bomb.
			}
		}
	}
	public boolean isBomb(){		
		
		return true;
	}
	
}
