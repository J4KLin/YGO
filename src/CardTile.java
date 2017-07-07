import java.util.ArrayList;

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
		setTranslateX(x);
		setTranslateY(y);
	}
	
	public CardTile(tileType ttype, OptionWindow option, double x, double y) {
		this.option = option;
		tile = ttype;
		initTileCapacity();
		cards = new ArrayList();
		setHeight(GameDriver.CARDHEIGHT);
		if(ttype == tileType.MONSTER|| ttype == tileType.SP_TR) {
			this.setWidth(GameDriver.CARDHEIGHT);
		}
		else{
			setWidth(GameDriver.CARDWIDTH);
		}
		xpos = x;
		ypos = y;
	
		setTranslateX(x);
		setTranslateY(y);
		setStroke(Color.valueOf("#2297DC"));
		setFill(Color.TRANSPARENT);
		setStrokeWidth(5);
		if(ttype == tileType.HAND){
			setStrokeWidth(8);
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
	
	private void slideCardForward(){
		this.setTranslateY(ypos-30);
		getTopCard().deprecated_placement(xpos, ypos-30);
	}
	private void slideCardBack(){
		this.setTranslateY(ypos);
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
