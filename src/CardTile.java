import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardTile extends Rectangle{
	
	enum tileType {DECK, FIELDSP, MONSTER, SP_TR, GRAVE, EXTRA, HAND};
	
	public tileType tile;
	private Card card;
	public final int xpos;
	public final int ypos;
	
	public boolean hasCard() {
		return card != null;
	}
	
	public void placeCard(Card card) {
		this.card = card;
	}
	
	public Card getCard() {
		return card;
	}
//	public CardTile(int x, int y){
//		setWidth(GameDriver.CARDWIDTH);
//		setHeight(GameDriver.CARDHEIGHT);
//		
//		relocate(x, y);
//		setStroke(Color.valueOf("#000000"));
//		setFill(Color.TRANSPARENT);
//	}
	
	public CardTile(tileType ttype, int x, int y) {
		tile = ttype;
		setWidth(GameDriver.CARDWIDTH);
		setHeight(GameDriver.CARDHEIGHT);
		xpos = x;
		ypos = y;
		
		//relocate(x, y);
		setTranslateX(x);
		setTranslateY(y);
		setStroke(Color.valueOf("#000000"));
		setFill(Color.TRANSPARENT);
		
		setOnMouseClicked(e-> {
			System.out.println("This is: " + tile.name());
		});
	}
	
}
