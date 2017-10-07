import java.io.File;
import javafx.scene.layout.Pane;

/*
 * A static class for building a deck of cards (an array of Card objects)
 */
public class DeckBuilder{
	/*
	 * Iterate through all the images from a given folder and generate card objects for each image
	 * and return an array of those card objects
	 * 	@param parentPane
	 * 			The primary pane on which everything is built upon
	 * 	@param folder
	 * 			The file containing all the card images
	 * 	@return A array of Card objects
	 */
	public static Card[] buildDeck(Pane parentPane, File folder){
		File[] files = folder.listFiles();
		Card[] deck = new Card[files.length];
		int cardcount = 0;
		for (File file : files){
			deck[cardcount] = createCard(parentPane, file);
			cardcount ++;
		}
		return deck;
	}
	
	
	/*
	 * Generate a card object from an image file. The format of the image name must be
	 * in the format of /^.*[MST]\.(jpg|png)$/
	 * 	@param parentPane
	 * 			The primary pane on which everything is built upon
	 * 	@param imgFile
	 * 			The File object of a card image
	 * 	@return A card object built from the imgFile
	 */
	private static Card createCard(Pane parentPane, File imgFile){
		String filename = imgFile.getName();
		char cardtype = filename.charAt(filename.length()-5);
		Card newcard;
		if (cardtype == 'T' || cardtype == 'S'){
			newcard = new SpellTrap(parentPane, imgFile.toURI().toString());
		}
		else{
			newcard = new Monster(parentPane, imgFile.toURI().toString());
		}
		return newcard;
	}
}
