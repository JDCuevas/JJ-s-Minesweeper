import javax.swing.JFrame;

public class Main { 
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("JJ's Minesweeper");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(335, 390);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		
		GameRules gameRules = new GameRules();
		gameRules.setBombs();

		GameMouseInteractions myMouseAdapter = new GameMouseInteractions();
		myFrame.addMouseListener(myMouseAdapter);


		myFrame.setVisible(true);
	}
}