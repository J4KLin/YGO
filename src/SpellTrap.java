import javafx.scene.Group;

public class SpellTrap extends Card{
	
	Thread waiter;
	
	public SpellTrap(board gameboard, String url, OptionWindow options) {
		super(gameboard, url, options);
		waiter = new Thread();
	}
	
	public void setCard(){
		this.setImage(cardback);
		//System.out.println("setting");
		this.toSTZone(false);
	}
	
	public void activate(){
		this.setImage(cardface);
		this.toSTZone(false);
		this.toGrave(false);
//		try        
//		{
//		    waiter.sleep(1000);
//		} 
//		catch(InterruptedException ex) 
//		{
//		    Thread.currentThread().interrupt();
//		}
	}
}
