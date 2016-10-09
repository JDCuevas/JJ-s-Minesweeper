import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JFrame;

public class GameMouseInteractions extends MouseAdapter {


	public void mousePressed(MouseEvent e) {
		GameRules gameRules = new GameRules();
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			if(myPanel.mouseDownGridX == -1 || myPanel.mouseDownGridY  == -1){ // If pressed outside
				// Do nothing
			}else if(myPanel.mouseDownGridX < 9 && myPanel.mouseDownGridY < 9){
				if(gameRules.isBomb(myPanel.mouseDownGridX, myPanel.mouseDownGridY)){
					System.out.println("BOMB");
				}else{ // If it is not a bomb

					int nearbyBombs = 0;

					for(int i = myPanel.mouseDownGridX-1; i < myPanel.mouseDownGridX+2; i++){ // Checks all 8 surrounding cells
						for(int j = myPanel.mouseDownGridY-1; j < myPanel.mouseDownGridY+2 ; j++){
							
							if((i > -1 && i < 9) && (j > -1 && j < 9)) // Checks for bombs around clicked cells if the cell exists
								if(gameRules.isBomb(i,j))
									nearbyBombs++;
						}
					}
					
					
					
					System.out.println("There are: " + nearbyBombs + " bombs around this cell");
				}
			}else{
				gameRules.setBombs();
				myPanel.repaint();
			}

			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}