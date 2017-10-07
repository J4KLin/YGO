import javafx.scene.layout.Pane;

public class SpellTrap extends Card{
	
	Thread waiter;
	
	public SpellTrap(Pane parentPane, String url) {
		super(parentPane, url);
		waiter = new Thread();
	}
	
	public void setCard(board curBoard){
		cardDisplay = cardback;
		curBoard.cardMovement(this, CardTile.tileType.SP_TR, false);
	}
	
	public void activate(){
		cardDisplay = cardface;
		//this.toSTZone(false);
		//this.toGrave(false);
		
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
