import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Monster extends Card{

	public Monster(Pane parentPane, board gameboard, String url, OptionWindow options) {
		super(parentPane, gameboard, url, options);
	}
	
	public void setCard(){
		this.setImage(cardback);
		//System.out.println("setting");
		this.setRotate(90);
		//cardMovement(CardTile.tileType.MONSTER, false);
		toMonsterZone(false);
	}
	
	public void flipCard(){
		this.setImage(cardface);
		this.setRotate(0);
	}
	
	public void activate(){
		this.setImage(cardface);
	}
	
	public void changetodefense(){
		this.setRotate(90);
	}
	
	public void changetoattack(){
		this.setRotate(0);
	}
}
