import javafx.scene.Group;
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
		cardface = new Image(url);
		cardback = new Image("/assets/back.png");
		setImage(cardface);
		setFitWidth(GameDriver.CARDWIDTH);
		setFitHeight(GameDriver.CARDHEIGHT);
		//setCache(true);
		
		setMouseTransparent(true);
//		setOnMouseClicked(e -> {
//			System.out.println("this card is in: " + placement.name());
//			options.onEvent(this, e.getScreenX(), e.getScreenY());
//		});
	}
	
	public void setCard(){
		this.setImage(cardback);
		System.out.println("setting");
	}
	
	public void activate(){
		this.setImage(cardface);
	}
	
	public void deprecated_placement(double x, double y) {
		setTranslateX(x);
		setTranslateY(y);
	}
	
	public void toDeck(boolean init) {
		cardMovement(CardTile.tileType.DECK, init);
//		placement = CardTile.tileType.DECK;
//		xpos = tile.xpos;
//		ypos = tile.ypos;
//		//gameboard.deck.placeCard(this);
//		setTranslateX(xpos);
//		setTranslateY(ypos);
	}
	
	public void toExtra(boolean init) {
		cardMovement(CardTile.tileType.EXTRA, init);
//		placement = CardTile.tileType.EXTRA;
//		xpos = tile.xpos;
//		ypos = tile.ypos;
//		//gameboard.extra.placeCard(this);
//		setTranslateX(xpos);
//		setTranslateY(ypos);
	}
	
	public void toGrave(boolean init) {
		cardMovement(CardTile.tileType.GRAVE, init);
		this.toFront();
//		placement = CardTile.tileType.GRAVE;
//		xpos = tile.xpos;
//		ypos = tile.ypos;
//		//gameboard.extra.placeCard(this);
//		setTranslateX(xpos);
//		setTranslateY(ypos);
	}
	
	public void toMonsterZone(boolean init){
		cardMovement(CardTile.tileType.MONSTER, init);
//		placement = CardTile.tileType.MONSTER;
//		xpos = tile.xpos;
//		ypos = tile.ypos;
//		//gameboard.deck.placeCard(this);
//		setTranslateX(xpos);
//		setTranslateY(ypos);
	}
	
	public void toSTZone(boolean init){
		cardMovement(CardTile.tileType.SP_TR, init);
//		placement = CardTile.tileType.SP_TR;
//		xpos = tile.xpos;
//		ypos = tile.ypos;
//		//gameboard.deck.placeCard(this);
//		setTranslateX(xpos);
//		setTranslateY(ypos);
	}
	
	public void toHand(boolean init){
		cardMovement(CardTile.tileType.HAND, init);
//		placement = CardTile.tileType.HAND;
//		xpos = tile.xpos;
//		ypos = tile.ypos;
//		setTranslateX(xpos);
//		setTranslateY(ypos);
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
		if(to == CardTile.tileType.MONSTER || to == CardTile.tileType.SP_TR){
			xpos += ((GameDriver.CARDHEIGHT-GameDriver.CARDWIDTH)/2);
		}
		setTranslateX(xpos);
		setTranslateY(ypos);
	}
}
