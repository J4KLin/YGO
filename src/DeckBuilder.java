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
		deck = new Card[9];
		//deck[0] = new Card(this.gameboard,"/assets/darkmagician.png", this.options);
		deck[0] = new Monster(this.gameboard,"/assets/darkmagician.png", this.options);
		deck[1] = new Monster(this.gameboard, "/assets/blueeyeswhitedragon.jpg", this.options);
		deck[2] = new Monster(this.gameboard, "/assets/summonedskullO.jpg", this.options);
		deck[3] = new Monster(this.gameboard, "/assets/darkmagicianO.jpg", this.options);
		deck[4] = new SpellTrap(this.gameboard, "/assets/monsterreborn.png", this.options);
		deck[5] = new SpellTrap(this.gameboard, "/assets/polymerization.jpg", this.options);
		deck[6] = new Monster(this.gameboard, "/assets/beaverwarrior.png", this.options);
		deck[7] = new SpellTrap(this.gameboard, "/assets/traphole.jpg", this.options);
		deck[8] = new SpellTrap(this.gameboard, "/assets/changeofheart.png", this.options);
//		deck[0].toDeck(true);
//		deck[1].toHand(true);
//		deck[2].toGrave(true);
//		deck[3].toMonsterZone(true);
//		deck[4].toSTZone(true);
//		deck[5].toExtra(true);
//		deck[6].toDeck(true);
//		deck[7].toDeck(true);
//		deck[8].toDeck(true);
		deck[0].cardMovement(CardTile.tileType.DECK, true);
		deck[1].cardMovement(CardTile.tileType.DECK, true);
		deck[2].cardMovement(CardTile.tileType.DECK, true);
		deck[3].cardMovement(CardTile.tileType.DECK, true);
		deck[4].cardMovement(CardTile.tileType.DECK, true);
		deck[5].cardMovement(CardTile.tileType.DECK, true);
		deck[6].cardMovement(CardTile.tileType.DECK, true);
		deck[7].cardMovement(CardTile.tileType.DECK, true);
		deck[8].cardMovement(CardTile.tileType.DECK, true);
		getChildren().setAll(deck);
	}
}
