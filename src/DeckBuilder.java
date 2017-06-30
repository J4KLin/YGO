import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DeckBuilder extends StackPane{

	 Card[] deck;
	 board gameboard;
	 
	public DeckBuilder(board gameboard, String side) {
		this.gameboard = gameboard;
		setPickOnBounds(false);
		deck = new Card[2];
		deck[0] = new Card(this.gameboard,"/assets/darkmagician.png");
		deck[1] = new Card(this.gameboard, "/assets/blueeyeswhitedragon.jpg");
		deck[0].toGrave();
		deck[1].toDeck();
		//deck[1].deprecated_placement(320, (GameDriver.CARDHEIGHT*2) + 15);
		//deck[0].deprecated_placement(320, (GameDriver.CARDHEIGHT*3) + 35);
		getChildren().setAll(deck);
	}
}
