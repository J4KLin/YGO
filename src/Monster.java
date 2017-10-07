import javafx.scene.layout.Pane;

public class Monster extends Card{

	public Monster(Pane parentPane, String url) {
		super(parentPane, url);
	}
	
	public void setCard(board curBoard){
		cardDisplay = cardback;
		this.setRotate(90);
		curBoard.cardMovement(this, CardTile.tileType.MONSTER, false);
	}
	
	public void flipCard(){
		cardDisplay = cardface;
		this.setRotate(0);
		tile.refreshTile();
	}
	
	public void summon(board curBoard) {
		cardDisplay = cardface;
		curBoard.cardMovement(this, CardTile.tileType.MONSTER, false);
	}
	
	public void activate(){
		cardDisplay = cardface;
	}
	
	public void changetodefense(){
		this.setRotate(90);
		tile.refreshTile();
	}
	
	public void changetoattack(){
		cardDisplay = cardface;
		this.setRotate(0);
		tile.refreshTile();
	}
}
