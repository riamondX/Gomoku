package gomoku1;

/**
 * @author Riven Xu
 *
 * the board class creates a Go board that can hold Stones and 
 * detect whether black or white wins.
 *
 */
public class Board 
{
	private Stone[][] square;
	/**
	 * How to check if its win
	 * 
	 * all rows and colums five
	 * left angled line five
	 * right angled line five
	 */
	public Board()
	{
		square = new Stone[19][19];
	}
	
	public boolean win(Player p)
	{
		if(horizonWin(p)||verticalWin(p)||crossWin(p))
			return true;
		else
			return false;
	}
	
	/**
	 * check if any player wins horizontally
	 */
	private boolean horizonWin(Player p)
	{
		boolean isWinner = false;
		if(p.getColor().isBlack())
		{
			int counter = 0;
			for(int i = 0;i<square.length;i++)
			{
				for(int j = 0; j<square[i].length;j++)
				{
			        if (square[i][j] != null && square[i][j].isBlack() && counter < 5)
			            counter++;
			        else if(counter == 5){
			            isWinner = true;
			            break;
			        }
			        else{
			            isWinner = false;
			            counter = 0;
			        }
			    }

			    if(isWinner)
			        break;
				}
			}
		else if(p.getColor().isWhite())
		{
			int counter = 0;
			for(int i = 0;i<square.length;i++)
			{
				for(int j = 0; j<square[i].length;j++)
				{
			        if (square[i][j] != null && square[i][j].isWhite() && counter < 5)
			            counter++;
			        else if(counter == 5){
			            isWinner = true;
			            break;
			        }
			        else{
			            isWinner = false;
			            counter = 0;
			        }
			    }

			    if(isWinner)
			        break;
			}
		}
		return isWinner;
	}
	
	/**
	 * check if any player wins vertically
	 */
	private boolean verticalWin(Player p)
	{
		boolean isWinner = false;
		if(	p.getColor().isBlack())
		{
			int counter = 0;
			for(int i = 0;i<square.length;i++)
			{
				for(int j = 0; j<square[i].length;j++)
				{
			        if (square[j][i] != null && square[j][i].isBlack() && counter < 5)
			            counter++;
			        else if(counter == 5){
			            isWinner = true;
			            break;
			        }
			        else{
			            isWinner = false;
			            counter = 0;
			        }
			    }

			    if(isWinner)
			        break;
				}
			}
		else if(p.getColor().isWhite())
		{
			int counter = 0;
			for(int i = 0;i<square.length;i++)
			{
				for(int j = 0; j<square[i].length;j++)
				{
			        if (square[j][i] != null && square[j][i].isWhite() && counter < 5)
			            counter++;
			        else if(counter == 5){
			            isWinner = true;
			            break;
			        }
			        else{
			            isWinner = false;
			            counter = 0;
			        }
			    }

			    if(isWinner)
			        break;
			}
		}
		return isWinner;
	}
	
	/**
	 * check diagonal.
	 * get five, win.
	 *
	 */
	private boolean crossWin(Player p)
	{
		Stone pGo = p.getColor();
		
		if(	pGo.isBlack())
		{
			for (int i = 0; i < 15; i++) 
			{
				for (int j = 0; j < 15; j++) 
				{
					if ((square[j][i]!=null) && (square[j + 1][i + 1]!=null)
			                	&& (square[j + 2][i + 2]!=null) 
			                	&& (square[j + 3][i + 3]!=null)
			                	&&(square[j + 4][i + 4]!=null) )
					{
						if ((square[j][i].isBlack()) && (square[j + 1][i + 1].isBlack())
								&& (square[j + 2][i + 2].isBlack()) 
								&& (square[j + 3][i + 3].isBlack())
								&&(square[j + 4][i + 4].isBlack())) 
		    			{
		       					return true;
		    			}
					}
				}
			}

			for (int i = 4; i < 19; i++) 
			{
				for (int j = 0; j <15 ; j++)
				{
					if (square[j][i]!=null && square[j + 1][i - 1]!=null
							&& square[j + 2][i - 2]!=null 
							&& square[j + 3][i - 3]!=null
							&&square[j + 4][i - 4]!=null) 
					{
						if ((square[j][i].isBlack()) && (square[j + 1][i - 1].isBlack())
								&& (square[j + 2][i - 2].isBlack()) 
								&& (square[j + 3][i - 3].isBlack())
								&&(square[j + 4][i - 4].isBlack()))  
						{
							return true;
						}
					}
				}
			}
		}
		
		else if(p.getColor().isWhite())
		{
			for (int i = 0; i < 15; i++) 
			{
				for (int j = 0; j < 15; j++) 
				{
					if ((square[j][i]!=null) && (square[j + 1][i + 1]!=null)
			                	&& (square[j + 2][i + 2]!=null) 
			                	&& (square[j + 3][i + 3]!=null)
			                	&&(square[j + 4][i + 4]!=null) )
					{
						if ((square[j][i].isWhite()) && (square[j + 1][i + 1].isWhite())
								&& (square[j + 2][i + 2].isWhite()) 
								&& (square[j + 3][i + 3].isWhite())
								&&(square[j + 4][i + 4].isWhite())) 
		    			{
		       					return true;
		    			}
					}
				}
			}

			for (int i = 4; i < 19; i++) 
			{
				for (int j = 0; j <15 ; j++)
				{
					if (square[j][i]!=null && square[j + 1][i - 1]!=null
							&& square[j + 2][i - 2]!=null 
							&& square[j + 3][i - 3]!=null
							&&square[j + 4][i - 4]!=null) 
					{
						if ((square[j][i].isWhite()) && (square[j + 1][i - 1].isWhite())
								&& (square[j + 2][i - 2].isWhite()) 
								&& (square[j + 3][i - 3].isWhite())
								&&(square[j + 4][i - 4].isWhite()))  
						{
							return true;
						}
					}
				}
			}
		}
			return false;
	}
	
	/**
	 * check if the board is empty
	 * @return
	 */
	public boolean isEmpty()
	{
		for (int i = 0; i < 19; i++) {
		    for (int j = 0; j < 19; j++) {
		    	if(square[i][j]!=null)
		    		return false;
		    }
		}
		return true;
	}
	
	/**
	 * check if the slot on the board which is accepting 
	 * new Go is available
	 */
	public boolean slotAvaliable(int x, int y)
	{
		if(square[x][y]!=null)
			return false;
		return true;
	}
	
	/**
	 * add a Go to the slot if the slot is available
	 */
	public boolean onBoard(int x,int y, Stone g)
	{
		if(slotAvaliable(x,y))
		{	
			square[x][y] = g;
			return true;
		}
		return false;
		
	}
	
	/**
	 *  toString() method to print out the board for debug
	 */
	public String toString() {
	    String result = "";

	    for (int row = 0; row < square.length; row++) 
	    {
	        result += "\n";
	        for (int column = 0; column < square[row].length; column++)
	        {
	           if(square[row][column]==null)
	                result += "-";

	        	else if (square[row][column].isBlack()) {
	                result += "O";
	            }

	            else if (square[row][column].isWhite()) {

	                result += "X";                                                                                                                           
	            }
	            else
	            	result+="?";

	        }
	    }

	    return result;
	}
}

