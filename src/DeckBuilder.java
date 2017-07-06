import java.io.File;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class DeckBuilder extends StackPane{

	 Card[] deck;
	 board gameboard;
	 OptionWindow options;
	 Group group;
	 
	public DeckBuilder(board gameboard, String side, OptionWindow options, Group group, File folder) {
		this.group = group;
		this.gameboard = gameboard;
		this.options = options;
		setPickOnBounds(false);
		createDeck(folder);
		gameboard.deck.toFront();
	}
	
	private void createDeck(File folder){
		File[] files = folder.listFiles();
		int cardcount = 0;
		deck = new Card[files.length];
		for (File file : files){
			createCard(file, cardcount);
			cardcount ++;
		}
	}
	
	private void createCard(File file, int index){
		String filename = file.getName();
		char cardtype = filename.charAt(filename.length()-5);
		Card newcard;
		if (cardtype == 'T' || cardtype == 'S'){
			newcard = new SpellTrap(gameboard, file.toURI().toString(), options);
		}
		else{
			newcard = new Monster(gameboard, file.toURI().toString(), options);
		}
		deck[index] = newcard;
		newcard.cardMovement(CardTile.tileType.DECK, true);
		newcard.toBack();
		group.getChildren().add(newcard);
	}
}
