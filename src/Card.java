import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
	Pane parentPane;
	
	public Card(Pane parentPane, board gameboard, String url, OptionWindow options) {
		this.parentPane = parentPane;
		this.gameboard = gameboard;
		this.options = options;
		cardface = new Image(url);
		cardback = new Image("/assets/back.png");
		setImage(cardface);
//		setFitWidth(GameDriver.CARDWIDTH);
//		setFitHeight(GameDriver.CARDHEIGHT);
		this.fitWidthProperty().bind(parentPane.widthProperty().multiply(GameDriver.CARDWIDTH/GameDriver.INITWIDTH));
		this.fitHeightProperty().bind(parentPane.heightProperty().multiply(GameDriver.CARDHEIGHT/GameDriver.INITHEIGHT));
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
//		setTranslateX(x);
//		setTranslateY(y);
		this.xProperty().bind(parentPane.widthProperty().multiply(x/GameDriver.INITWIDTH));
		this.yProperty().bind(parentPane.heightProperty().multiply(y/GameDriver.INITHEIGHT));
	}
	
	public void toDeck(boolean init) {
		cardMovement(CardTile.tileType.DECK, init);
	}
	
	public void toExtra(boolean init) {
		cardMovement(CardTile.tileType.EXTRA, init);
	}
	
	public void toGrave(boolean init) {
		cardMovement(CardTile.tileType.GRAVE, init);
		this.toFront();
	}
	
	public void toMonsterZone(boolean init){
		cardMovement(CardTile.tileType.MONSTER, init);
	}
	
	public void toSTZone(boolean init){
		cardMovement(CardTile.tileType.SP_TR, init);
	}
	
	public void toHand(boolean init){
		cardMovement(CardTile.tileType.HAND, init);
	}
	
	public void updateLocation(CardTile t){
//		setTranslateX(t.xpos);
//		setTranslateY(t.ypos);
		this.xProperty().bind(parentPane.widthProperty().multiply(t.xpos/GameDriver.INITWIDTH));
		this.yProperty().bind(parentPane.heightProperty().multiply(t.ypos/GameDriver.INITHEIGHT));
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
		//setTranslateX(xpos);
		//setTranslateY(ypos);
		this.xProperty().bind(parentPane.widthProperty().multiply(xpos/GameDriver.INITWIDTH));
		this.yProperty().bind(parentPane.heightProperty().multiply(ypos/GameDriver.INITHEIGHT));
	}
}
