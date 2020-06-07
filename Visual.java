//package gomoku1;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


/**
 * @author Riven Xu
 * @date 05/10/16
 *
 * Further development plan:
 * 		1. A main menu that leads to the board.
 * 		2. Able to resume game if not finished.
 * 		3. Game record based on player name(or color). Player name set only before new game.
 * 		4. Renju rules(for black stone).
 * 		5. Potential AI
 * 
 * The Visual display of the game.
 */
public class Visual extends javax.swing.JFrame implements ActionListener {
	// the main frame that holds everything
	private static JFrame f;

	// the frame pops up when click.provide help information
	private static JFrame fHelp;

	// have three panels, main, buttons, texts
	private JPanel p;
	private JPanel sp1;
	private JPanel sp2;

	// spoil panel.
	private JPanel sp3;

	// text field
	private JTextField name1;
	private JTextField name2;
	
	// text area
	private JTextArea text1;

	// labels
	private JLabel py1;
	private JLabel py2;

	private JLabel declareWin;

	// JButtons
	private JButton[][] boardButton;
	private JButton newGame;

	// menu : two items, quit and help
	private JMenuBar menuBar;
	private JMenuItem item1;
	private JMenuItem item2;

	private Player p1;
	private Player p2;

	private boolean black;
	private boolean white;

	private boolean initGame;

	private Board theBoard = new Board();

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// appearance of the frame
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("error");
		}

		// declare the new app
		Visual app = new Visual();

		// close the application
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// a certain size of the window
		f.setSize(900, 800);
		
		f.setVisible(true);

		f.setResizable(false);

	}

	/**
	 * the constructor
	 */
	public Visual() {
		// Create the overall window for the application
		f = new JFrame("Gomoku v1.0");

		fHelp = new JFrame("Help");
		// create the content panel to be put in the frame
		p = new JPanel();

		sp1 = new JPanel();

		sp2 = new JPanel();

		sp3 = new JPanel();

		// plan for gridbag layout.
		// GridLayout layout = new GridLayout(19,19);

		// set whichever layout is chosen above
		p.setLayout(null);

		// sp2.setLayout(null);

		p.setBounds(15, 10, 670, 670); // <=== position of panel1
		p.setBorder(BorderFactory.createTitledBorder("board")); // border

		sp1.setBounds(700, 10, 150, 220); // <=== position of panel2
		sp1.setBorder(BorderFactory.createTitledBorder("Control")); // border

		sp2.setBounds(700, 260, 150, 50); // <=== position of panel3
		sp2.setBorder(BorderFactory.createTitledBorder("Condition")); // border

		// call the following methods so that they will
		// create & add each of the labels, buttons, & textfields
		// to the content panel
		addLabelsToContentPanel();
		addButtonsToContentPanel();
		addFieldToContentPanel();
		addMenuToFrame();

		// construct new game button
		newGame = new JButton("New Game");

		newGame.setBounds(500, 10, 100, 40);
		newGame.addActionListener(this);

		// the help window text.
		text1 = new JTextArea();
		
		sp1.add(py1);
		sp1.add(name1);

		sp1.add(newGame);

		sp1.add(py2);
		sp1.add(name2);

		sp2.add(declareWin);

		// finally add the content to the window
		// f.setContentPane(p);
		f.add(p);
		f.add(sp1);
		f.add(sp2);
		f.add(sp3);
		
		initGame = true;
	}

	/**
	 * set rollovers for available slot if the player holding black go is
	 * playing
	 */
	private void blackFlip() {
		ImageIcon onBlack = new ImageIcon("images/onBlack.png");
		//System.out.println("black flip");
		for (int i = 0; i <= 18; i++) {
			for (int j = 0; j <= 18; j++) {
				if (theBoard.slotAvaliable(i, j)) {
					boardButton[i][j].setRolloverIcon(onBlack);
				}
			}
		}
	}

	/**
	 * set rollovers for available slot if the player holding white go is
	 * playing
	 */
	private void whiteFlip() {
		ImageIcon onWhite = new ImageIcon("images/onWhite.png");
		//System.out.println("white flip");
		for (int i = 0; i <= 18; i++) {
			for (int j = 0; j <= 18; j++) {
				if (theBoard.slotAvaliable(i, j)) {
					boardButton[i][j].setRolloverIcon(onWhite);
				}
			}
		}
	}

	/**
	 * the major part accomplish most move
	 */
	public void actionPerformed(ActionEvent e) 
	{
		Stone b = new Stone("black");
		Stone a = new Stone("white");
		
    	Icon normal = new ImageIcon("images/emptyNorm.png");
    	
    	// menu setting

		// help button pops the help window
		if (e.getSource() == item1)
		{
			// automatically resize window (use instead of setSize)
			fHelp.setVisible(true);
			
			text1.setEditable(false);

			// the help message
			text1.setText("Gomoku is basically a enlarged 'tic tac toe'." +
						"\n" +"Get five in a row(vertical or horizontal or diagonal) to win!");
			
			fHelp.add(text1);
			fHelp.pack();
			fHelp.setLocationRelativeTo(null);
		}    	
		
		// Quit button
		if (e.getSource() == item2)
		{
			System.exit(0);
		}
		
		// if new Game was pressed 
		if (e.getSource() == newGame)
		{
			//System.out.println("==new game==");
			// reset the board panel
			theBoard = new Board();
			
			// black starts
			declareWin.setText("Black Turn");
			if(name1.getText().isEmpty())
			{
				py1.setText("player1");
			}
			else
				py1.setText(name1.getText());
			if(name2.getText().isEmpty())
			{
				py2.setText("player2");
			}
			else			
				py2.setText(name2.getText());
			
			// set player name if given in the textfield
			p1 = new Player(b,py1.getText());
			p2 = new Player(a,py2.getText());
			
			// make the buttons clickable
			// reset the board
			for(int i = 0; i <= 18;i++)
			{
				for(int j = 0; j <= 18;j++)
				{
					boardButton[i][j].setIcon(normal);
					boardButton[i][j].addActionListener(this);
				}
			}
			if(initGame)
			{
				initGame = false;
				newGame.doClick();
			}
		}
		
		// require name setting
		if(e.getSource() == name1)
			py1.setText(name1.getText());
		if(e.getSource() == name2)
			py2.setText(name2.getText());

		
		// detect whose turn so lay proper go on the location
		// label show whose turn
		ImageIcon blackOn = new ImageIcon("images/black.png");
		ImageIcon whiteOn = new ImageIcon("images/white.png");
		
		

		
		// after click, set the label to the opposite text
		// the rollover of color is set based on color
		// make sure there is no go there already(use slot available)
		// based on whose turn it is the roll-over picture is different

		if(!theBoard.win(p1)&&!theBoard.win(p2))
		{

			// all these while no one is winning
			if (declareWin.getText().equals("Black Turn"))
			{
				black = true;
				white = false;
			}
			else
			{
				white = true;
				black = false;
			}


			//System.out.println("white: "+white+" black: "+black);
			// black turn
			if(black && !white)
			{
				blackFlip();
				for(int i = 0; i <= 18;i++)
				{
					for(int j = 0; j <= 18;j++)
					{
						if(e.getSource() == boardButton[i][j])
						{
							if(theBoard.slotAvaliable(i, j))
							{
								theBoard.onBoard(i, j, b);
								boardButton[i][j].setIcon(blackOn);
								boardButton[i][j].setRolloverIcon(null);
								boardButton[i][j].addActionListener(null);
								declareWin.setText("White Turn");
								break;
							}
						}

					}
				}
			
			}
		
			// white turn
			else if(!black && white)
			{
				whiteFlip();
				for(int i = 0; i <= 18;i++)
				{
					for(int j = 0; j<=18;j++)
					{
						if(e.getSource() == boardButton[i][j])
						{
							if(theBoard.slotAvaliable(i, j))
							{
								theBoard.onBoard(i, j, a);
								boardButton[i][j].setIcon(whiteOn);
								boardButton[i][j].setRolloverIcon(null);
								boardButton[i][j].addActionListener(null);
								declareWin.setText("Black Turn");
								break;
							
							}
						}
		
					}
				}
					
			}
		}
		
		// a detect win that will stop game and jump to winning mode	
		// label which shows whose turn declare winning side
		else 
		{
			if(theBoard.win(p1))
			{
				declareWin.setText(py1.getText()+" Wins!");
				for(int i = 0; i <= 18;i++)
				{
					for(int j = 0; j <= 18;j++)
					{
						boardButton[i][j].setRolloverIcon(null);
						boardButton[i][j].addActionListener(null);
					}
				}
			}
			else if(theBoard.win(p2))
			{
				declareWin.setText(py2.getText()+" Wins!");
				for(int i = 0; i <= 18;i++)
				{
					for(int j = 0; j <= 18;j++)
					{
						boardButton[i][j].setRolloverIcon(null);
						boardButton[i][j].addActionListener(null);
					}
				}
			}
		}
			
		
		// if every slot is filled and no winner, it is a tie.
				boolean tie = false;
				for(int i = 0; i <= 18;i++)
				{
					for(int j = 0; j <= 18;j++)
					{
						tie = theBoard.slotAvaliable(i,j);
						if(tie)
							break;
					}
				}
				
				if(!tie)
				{
					declareWin.setText("Game ties!");
					for(int i = 0; i <= 18;i++)
					{
						for(int j = 0; j <= 18;j++)
						{
							boardButton[i][j].setRolloverIcon(null);
							boardButton[i][j].addActionListener(null);
						}
					}	
				}
	}

	/**
	 * instantiate the buttons and add to the panel
	 */
	public void addButtonsToContentPanel() {

		boardButton = new JButton[19][19];
		Icon normal = new ImageIcon("images/emptyNorm.png");

		for (int i = 0; i <= 18; i++) {
			for (int j = 0; j <= 18; j++) {
				boardButton[i][j] = new JButton(normal);
				boardButton[i][j].setBounds(j * 33 + 20, i * 33 + 25, 33, 33);
				p.add(boardButton[i][j]);
			}
		}
	}

	/**
	 * create and add for the labels to the content panel place the picture into
	 * the label.
	 */
	public void addLabelsToContentPanel() {
		ImageIcon p1B = new ImageIcon("images/BlackIcon.png");
		ImageIcon p2W = new ImageIcon("images/WhiteIcon.png");

		py1 = new JLabel("Player1", p1B, SwingConstants.LEFT);
		py1.setToolTipText("type your own name");

		// Icon myPicture2 = new ImageIcon("butterfly.gif");
		py2 = new JLabel("Player2", p2W, SwingConstants.RIGHT);
		py2.setToolTipText("type your own name");

		declareWin = new JLabel();

	}

	/**
	 * create,add, and attach events for the text fields to the content panel
	 */
	public void addFieldToContentPanel() {
		name1 = new JTextField(10); // create the textfield & set size to 10
		name1.setEditable(true); // the user can change it
		name1.addActionListener(this); // listen for an mouse click

		name2 = new JTextField(10); // create the textfield & set size to 10
		name2.setEditable(true); // the user can change it
		name2.addActionListener(this); // listen for an mouse click

	}

	/**
     * add the items and menu to the frame
     */
	public void addMenuToFrame() {
		// create a new menu bar and menu
		menuBar = new JMenuBar();

		JMenu menu = new JMenu("menu");

		// add a menu item
		item1 = new JMenuItem("Help");
		item2 = new JMenuItem("Quit");
		item1.addActionListener(this);
		item2.addActionListener(this);
		menu.add(item1);
		menu.add(item2);

		// add menu to the menuBar
		menuBar.add(menu);

		// finally add to frame
		f.setJMenuBar(menuBar);
	}

}
