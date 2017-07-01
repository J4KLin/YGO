import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DeckBuilder extends StackPane{

	 Card[] deck;
	 board gameboard;
	 OptionWindow options;
	 
	public DeckBuilder(board gameboard, String side, OptionWindow options) {
		this.gameboard = gameboard;
		this.options = options;
		setPickOnBounds(false);
		deck = new Card[2];
		deck[0] = new Card(this.gameboard,"/assets/darkmagician.png", this.options);
		deck[1] = new Card(this.gameboard, "/assets/blueeyeswhitedragon.jpg", this.options);
		deck[0].toGrave();
		deck[1].toDeck();
		//deck[1].deprecated_placement(320, (GameDriver.CARDHEIGHT*2) + 15);
		//deck[0].deprecated_placement(320, (GameDriver.CARDHEIGHT*3) + 35);
		getChildren().setAll(deck);
	}
}
