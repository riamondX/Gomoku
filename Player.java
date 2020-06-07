//package gomoku1;

/**
 * @author Riven Xu
 * @date 05/10/16
 * 
 * The Player class. A Player can have 
 */
public class Player 
{
	public String name;
	public Stone belong;
	
	/**
	 * the constructor
	 */
	public Player(Stone p,String n)
	{
		belong = p;
		name = n;
	}
	
	/**
	 * get the color of the stone that belong to this player
	 * @return
	 */
	public Stone getColor()
	{
		return belong;
	}
	
	/**
	 * get the player's name
	 * @return
	 */
	public String getName()
	{
		return name;
	}

}
