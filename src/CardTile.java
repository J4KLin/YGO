import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardTile extends Rectangle{
	
	enum tileType {DECK, FIELDSP, MONSTER, SP_TR, GRAVE, EXTRA, HAND};
	
	public static final int MAXCAPACITY = 80;
	public tileType tile;
	private ArrayList<Card> cards;
	int capacity;
	public double xpos;
	public double ypos;
	public OptionWindow option;
	private Pane parentPane;
	
	private ImageView cardImg;
	private Group group;
	private board board;
	
	
	public CardTile(Pane parentPane, board board, Group group, tileType ttype, OptionWindow option, double x, double y) {
		this.parentPane = parentPane;
		this.group = group;
		this.option = option;
		tile = ttype;
		initTileCapacity();
		cards = new ArrayList();
		this.heightProperty().bind(parentPane.heightProperty().multiply(GameDriver.CARDHEIGHT/GameDriver.INITHEIGHT));
		if(ttype == tileType.MONSTER|| ttype == tileType.SP_TR) {
			this.widthProperty().bind(parentPane.widthProperty().multiply(GameDriver.CARDHEIGHT/GameDriver.INITWIDTH));
		}
		else{
			this.widthProperty().bind(parentPane.widthProperty().multiply(GameDriver.CARDWIDTH/GameDriver.INITWIDTH));
		}
		moveTile(x,y);
		setStroke(Color.valueOf("#2297DC"));
		setFill(Color.TRANSPARENT);
		this.strokeWidthProperty().bind(parentPane.widthProperty().multiply(5/GameDriver.INITWIDTH));
		if(ttype == tileType.HAND){
			this.strokeWidthProperty().bind(parentPane.widthProperty().multiply(8/GameDriver.INITWIDTH));
		}
		
		cardImg = new ImageView();
		group.getChildren().add(cardImg);
		cardImg.setMouseTransparent(true);
			
		setOnMouseClicked(e-> {
			System.out.println("This is: " + tile.name());
			if(hasCard()){
				option.onEvent(board, getTopCard(), e.getScreenX(), e.getScreenY());
			}
		});
		
		setOnMouseEntered(e-> {
			setStroke(Color.DARKORANGE);
			if(hasCard()){
				Card c = getTopCard();
				board.viewCard(c);
				if(tile == tileType.HAND){
					slideCardForward();
				}
			}
		});
		
		setOnMouseExited(e-> {
			setStroke(Color.valueOf("#2297DC"));
			if(tile == tileType.HAND){
				slideCardBack();
			}
		});
	}
	
	public boolean isFull() {
		return cards.size() >= capacity;
	}
	
	public boolean hasCard(){
		return cards.size() > 0;
	}
	public boolean canPlace() {
		return cards.size() < capacity;
	}
	
	public void placeCard(Card card) {
		cards.add(card);
		card.updateTile(this);

		cardImg.setImage(card.cardDisplay);
		cardImg.fitHeightProperty().bind(card.fitHeightProperty());
		cardImg.fitWidthProperty().bind(card.fitWidthProperty());
		cardImg.xProperty().bind(card.xProperty());
		cardImg.yProperty().bind(card.yProperty());
		cardImg.rotateProperty().bind(card.rotateProperty());
		cardImg.toFront();
	}

	public void refreshTile() {
		Card card = getTopCard();
		cardImg.setImage(card.cardDisplay);
		cardImg.fitHeightProperty().bind(card.fitHeightProperty());
		cardImg.fitWidthProperty().bind(card.fitWidthProperty());
		cardImg.xProperty().bind(card.xProperty());
		cardImg.yProperty().bind(card.yProperty());
		cardImg.rotateProperty().bind(card.rotateProperty());
		cardImg.toFront();
	}
	
	public void removeCard(Card card){
		if(cards.remove(card)) {
			if(hasCard()) {
				Card nextCard = getTopCard();
				cardImg.setImage(nextCard.cardDisplay);
				cardImg.fitHeightProperty().bind(nextCard.fitHeightProperty());
				cardImg.fitWidthProperty().bind(nextCard.fitWidthProperty());
				cardImg.xProperty().bind(nextCard.xProperty());
				cardImg.yProperty().bind(nextCard.yProperty());
				cardImg.rotateProperty().bind(nextCard.rotateProperty());
			}
			else {
				cardImg.setImage(null);
			}
		}
	}
	
	public Card getTopCard(){
		if(cards.size() > 0){
			return cards.get(cards.size()-1);
		}
		return null;
	}
	
	public void moveTile(double x, double y){
		xpos = x;
		ypos = y;
		this.xProperty().bind(parentPane.widthProperty().multiply(x/GameDriver.INITWIDTH));
		this.yProperty().bind(parentPane.heightProperty().multiply(y/GameDriver.INITHEIGHT));
	}
	
	private void slideCardForward(){
		this.yProperty().bind(parentPane.heightProperty().multiply((ypos-30)/GameDriver.INITHEIGHT));
		getTopCard().deprecated_placement(xpos, ypos-30);
	}
	private void slideCardBack(){
		this.yProperty().bind(parentPane.heightProperty().multiply(ypos/GameDriver.INITHEIGHT));
		if(hasCard()){
			getTopCard().deprecated_placement(xpos, ypos);
		}
	}
	private void initTileCapacity(){
		switch (tile){
			case DECK:
			case GRAVE:
			case HAND:
			case EXTRA:
				capacity = MAXCAPACITY;
				break;
			default:
				capacity = 1;
				break;
		}
	}
}
