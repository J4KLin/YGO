import java.util.ArrayList;

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
	
	
	public CardTile(Pane parentPane, tileType ttype, OptionWindow option, double x, double y) {
		this.parentPane = parentPane;
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
		//setStrokeWidth(5);
		this.strokeWidthProperty().bind(parentPane.widthProperty().multiply(5/GameDriver.INITWIDTH));
		if(ttype == tileType.HAND){
			//setStrokeWidth(8);
			this.strokeWidthProperty().bind(parentPane.widthProperty().multiply(8/GameDriver.INITWIDTH));
		}
		
		setOnMouseClicked(e-> {
			System.out.println("This is: " + tile.name());
			if(hasCard()){
				option.onEvent(getTopCard(), e.getScreenX(), e.getScreenY());
			}
		});
		
		setOnMouseEntered(e-> {
			setStroke(Color.DARKORANGE);
			if(hasCard()){
				Card c = getTopCard();
				c.gameboard.viewCard(c);
				this.toFront();
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
	}

	public void removeCard(Card card){
		cards.remove(card);
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
//		setTranslateX(x);
//		setTranslateY(y);
		this.xProperty().bind(parentPane.widthProperty().multiply(x/GameDriver.INITWIDTH));
		this.yProperty().bind(parentPane.heightProperty().multiply(y/GameDriver.INITHEIGHT));
	}
	
	private void slideCardForward(){
		//this.setTranslateY(ypos-30);
		this.yProperty().bind(parentPane.heightProperty().multiply((ypos-30)/GameDriver.INITHEIGHT));
		getTopCard().deprecated_placement(xpos, ypos-30);
	}
	private void slideCardBack(){
		//this.setTranslateY(ypos);
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
