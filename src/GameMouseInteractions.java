import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class GameMouseInteractions extends MouseAdapter { // test

	public void mousePressed(MouseEvent e) {
		GameRules gameRules = new GameRules();
					
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
		
		switch (e.getButton()) {
		case 1:		//Left mouse button
			
			if(myPanel.mouseDownGridX == -1 || myPanel.mouseDownGridY  == -1){ // If pressed outside
				// Do nothing
			} else if (myPanel.mouseDownGridX < 9 && myPanel.mouseDownGridY < 9){
				if(gameRules.isBomb(myPanel.mouseDownGridX, myPanel.mouseDownGridY)&&(!gameRules.isFlag(myPanel.mouseDownGridX,myPanel.mouseDownGridY))){
					System.out.println("BOMB");
				} else { // If it is not a bomb
					if((gameRules.nearbyBombs(myPanel.mouseDownGridX,myPanel.mouseDownGridY) > 0)&&(!gameRules.isFlag(myPanel.mouseDownGridX,myPanel.mouseDownGridY)))
						System.out.println("There are: " + gameRules.nearbyBombs(myPanel.mouseDownGridX,myPanel.mouseDownGridY)+ " bombs around this cell");
					else{
						
					}

				}
			} else {
				gameRules.resetGame();
				
				myPanel.repaint();
			}

			break;
		case 3:		//Right mouse button
			//Do nothing
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame)c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0); //Can also loop among components to find MyPanel
			x = e.getX();
			y = e.getY();
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			if(myPanel.mouseDownGridX == -1 || myPanel.mouseDownGridY  == -1){ // If pressed outside
				// Do nothing
			} else if (myPanel.mouseDownGridX < 9 && myPanel.mouseDownGridY < 9){
				if(gameRules.isFlag(myPanel.mouseDownGridX,myPanel.mouseDownGridY) == false){
				gameRules.setFlag(gridX, gridY);
				myPanel.repaint();	
				}else{
				gameRules.resetFlag(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
				}
				
				myPanel.repaint();	
			}
			
			
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}