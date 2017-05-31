package gomoku1;

import java.awt.Point;

/**
 * @author Riven Xu
 * @date 05/10/16
 * 
 * Stone can be either black or white.
 */
public class Stone 
{
	private boolean black;
	private boolean white;
	
	/**
	 * Constructor
	 * @param color
	 */
	public Stone(String color)
	{
		if(color.equals("black"))
		{
			black = true;
			white = false;
		}
		else if(color.equals("white"))
		{
			white = true;
			black = false;
		}
	}
	
	/**
	 * if the stone is black
	 * @return
	 */
	public boolean isBlack()
	{
		return black;
	}
	
	/**
	 * if the stone is white
	 * @return
	 */
	public boolean isWhite()
	{
		return white;
	}
}
