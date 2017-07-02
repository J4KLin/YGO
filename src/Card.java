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
	double xpos;
	double ypos;
	Position position;
	CardTile.tileType placement;
	CardTile tile;
	
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
			System.out.println("this card is in: " + placement.name());
			options.onEvent(this, e.getScreenX(), e.getScreenY());
		});
	}
	
	public void deprecated_placement(int x, int y) {
		setTranslateX(x);
		setTranslateY(y);
	}
	
	public void toDeck(boolean init) {
		tile = gameboard.cardMovement(this, CardTile.tileType.DECK, init);
		placement = CardTile.tileType.DECK;
		xpos = tile.xpos;
		ypos = tile.ypos;
		//gameboard.deck.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toExtra(boolean init) {
		tile = gameboard.cardMovement(this, CardTile.tileType.EXTRA, init);
		placement = CardTile.tileType.EXTRA;
		xpos = tile.xpos;
		ypos = tile.ypos;
		//gameboard.extra.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toGrave(boolean init) {
		tile = gameboard.cardMovement(this, CardTile.tileType.GRAVE, init);
		placement = CardTile.tileType.GRAVE;
		xpos = tile.xpos;
		ypos = tile.ypos;
		//gameboard.extra.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toMonsterZone(boolean init){
		tile = gameboard.cardMovement(this, CardTile.tileType.MONSTER, init);
		placement = CardTile.tileType.MONSTER;
		xpos = tile.xpos;
		ypos = tile.ypos;
		//gameboard.deck.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toSTZone(boolean init){
		tile = gameboard.cardMovement(this, CardTile.tileType.SP_TR, init);
		placement = CardTile.tileType.SP_TR;
		xpos = tile.xpos;
		ypos = tile.ypos;
		//gameboard.deck.placeCard(this);
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void toHand(boolean init){
		tile = gameboard.cardMovement(this, CardTile.tileType.HAND, init);
		placement = CardTile.tileType.HAND;
		xpos = tile.xpos;
		ypos = tile.ypos;
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
	
	public void updateLocation(CardTile t){
		setTranslateX(t.xpos);
		setTranslateY(t.ypos);
	}
	
	public void cardMovement(CardTile.tileType to, boolean init){
		if(gameboard.isFull(to)){
			return;
		}
		tile = gameboard.cardMovement(this, to, init);
		placement = to;
		xpos = tile.xpos;
		ypos = tile.ypos;
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
}
