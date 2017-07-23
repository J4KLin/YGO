import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class SpellTrap extends Card{
	
	Thread waiter;
	
	public SpellTrap(Pane parentPane, board gameboard, String url, OptionWindow options) {
		super(parentPane, gameboard, url, options);
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
