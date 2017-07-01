import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Card extends ImageView{
	enum Position {SET, ATK, DEF, NONE};
	
	String name;
	Image cardface;
	Image cardback;
	board gameboard;
	OptionWindow options;
	int xpos;
	int ypos;
	Position position;
	
	public Card(board gameboard, String url, OptionWindow options) {
		this.gameboard = gameboard;
		this.options = options;
		Image cardface = new Image(url);
		Image cardback = new Image("/assets/back.png");
		setImage(cardface);
		setFitWidth(GameDriver.CARDWIDTH);
		setFitHeight(GameDriver.CARDHEIGHT);
		setCache(true);
		
		setOnMouseClicked(e -> {
			System.out.println("this card is: " + name);
			options.onEvent(this, e.getScreenX(), e.getScreenY());
		});
	}
	
	public void deprecated_placement(int x, int y) {
		setTranslateX(x);
		setTranslateY(y);
	}
	
	public void toDeck() {
		xpos = gameboard.deck.xpos;
		ypos = gameboard.deck.ypos;
		gameboard.deck.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toExtra() {
		xpos = gameboard.extra.xpos;
		ypos = gameboard.extra.ypos;
		gameboard.extra.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toGrave() {
		xpos = gameboard.grave.xpos;
		ypos = gameboard.grave.ypos;
		gameboard.grave.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toMonsterZone() {
		CardTile[] field = gameboard.monsterField;
		CardTile curtile = null;
		for(int tile=0; tile < field.length; tile++) {
			curtile = field[tile];
			if(!curtile.hasCard()) {
				curtile.placeCard(this);
				break;
			}
		}
		if(curtile != null) {
			xpos = curtile.xpos;
			ypos = curtile.ypos;
			setTranslateX(xpos);
			setTranslateY(ypos);
		}
	}
	
	public void toSTZone() {
		CardTile[] field = gameboard.stField;
		CardTile curtile = null;
		for(int tile=0; tile < field.length; tile++) {
			curtile = field[tile];
			if(!curtile.hasCard()) {
				curtile.placeCard(this);
				break;
			}
		}
		if(curtile != null) {
			xpos = curtile.xpos;
			ypos = curtile.ypos;
			setTranslateX(xpos);
			setTranslateY(ypos);
		}
	}
	
}
