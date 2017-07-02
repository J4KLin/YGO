import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardTile extends Rectangle{
	
	enum tileType {DECK, FIELDSP, MONSTER, SP_TR, GRAVE, EXTRA, HAND};
	
	public static final int MAXCAPACITY = 80;
	public tileType tile;
	//private Card card;
	private ArrayList<Card> cards;
	int capacity;
	public double xpos;
	public double ypos;
	
//	public boolean hasCard() {
//		return card != null;
//	}
	
	public boolean isFull() {
		return cards.size() >= capacity;
	}
	
	public boolean canPlace() {
		return cards.size() < capacity;
	}
	
	public void placeCard(Card card) {
		cards.add(card);
		//this.card = card;
	}
	
//	public Card getCard() {
//		return card;
//	}
	
	public void removeCard(Card card){
		cards.remove(card);
		//this.card = null;
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
	
	public CardTile(tileType ttype, int x, int y) {
		tile = ttype;
		initTileCapacity();
		cards = new ArrayList();
		setWidth(GameDriver.CARDWIDTH);
		setHeight(GameDriver.CARDHEIGHT);
		xpos = x;
		ypos = y;
		
		setTranslateX(x);
		setTranslateY(y);
		setStroke(Color.valueOf("#000000"));
		setFill(Color.TRANSPARENT);
		
		setOnMouseClicked(e-> {
			System.out.println("This is: " + tile.name());
		});
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
