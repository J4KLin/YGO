import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/*
 *Card class used to build singular game objects displayed on the pane
 *Players do not directly interact with card Objects but the underlying tiles on which cards are placed
 */
public class Card extends ImageView{
	enum Position {SET, ATK, DEF, NONE};
	Pane parentPane;
	
	String name;
	Image cardface;
	Image cardback;
	Image cardDisplay;
	
	Position position;
	CardTile.tileType fieldPlacement;
	CardTile tile;
	
	/*
	 * Create a Card object that will be displayed on the parentPane;
	 * a newly create Card will have a default of displaying the cardface
	 * 	@param	parentPane
	 * 		The primary pane on which everything is built upon
	 * 	@param	url
	 * 		Local card image location
	 */
	public Card(Pane parentPane, String url) {
		this.parentPane = parentPane;
		cardface = new Image(url);
		cardback = new Image("/assets/back.png");
		cardDisplay = cardface;
		
		//Bind card object to parentPane for displaying and scaling
		this.fitWidthProperty().bind(parentPane.widthProperty().multiply(GameDriver.CARDWIDTH/GameDriver.INITWIDTH));
		this.fitHeightProperty().bind(parentPane.heightProperty().multiply(GameDriver.CARDHEIGHT/GameDriver.INITHEIGHT));
		this.setMouseTransparent(true);
		setCache(true);
	}
	
	/*
	 * Update the new tile location on which the card will be placed
	 * 	@param tile
	 * 		The CardTile object on which the card will be placed
	 */
	public void updateTile(CardTile tile) {
		this.tile = tile;
		fieldPlacement = tile.tile;
		updateLocation(tile);
	}
	
	/*
	 * Update the pane to display the card on the new tile location
	 * 	@param tile
	 * 		The CardTile object on which the card will be displayed
	 */
	public void updateLocation(CardTile t){
		double adjustFactor = 0;
		if(t.tile == CardTile.tileType.MONSTER || t.tile == CardTile.tileType.SP_TR) {
			adjustFactor += ((GameDriver.CARDHEIGHT-GameDriver.CARDWIDTH)/2);
		}
		this.xProperty().bind(parentPane.widthProperty().multiply((t.xpos+adjustFactor)/GameDriver.INITWIDTH));
		this.yProperty().bind(parentPane.heightProperty().multiply(t.ypos/GameDriver.INITHEIGHT));
	}
	
	/*
	 * Manually set where the card is being displayed on the pane (USE WITH CAUTION)
	 * 	@param x
	 * 		x coordinate on the pane
	 * 	@param y 
	 * 		y coordinate on the pane
	 */
	public void deprecated_placement(double x, double y) {
		this.xProperty().bind(parentPane.widthProperty().multiply(x/GameDriver.INITWIDTH));
		this.yProperty().bind(parentPane.heightProperty().multiply(y/GameDriver.INITHEIGHT));
	}
	
	//Generic Method that will be Overridden by Children Classes
	public void setCard(board curBoard){
		cardDisplay = cardback;
	}
	
	//Generic Method that will be Overridden by Children Classes
	public void activate(){
		cardDisplay = cardface;
	}
	
	public void draw(board curBoard) {
		cardDisplay = cardface;
		curBoard.cardMovement(this, CardTile.tileType.HAND, false);
	}
	
	public void toDeck(board curBoard, boolean init) {
		cardDisplay = cardback;
		curBoard.cardMovement(this, CardTile.tileType.DECK, init);
	}
}
