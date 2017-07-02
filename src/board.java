import java.util.ArrayList;

import javafx.scene.layout.StackPane;

public class board extends StackPane {
	public int player;
	public CardTile grave;
	public CardTile deck;
	public CardTile fieldsp;
	public CardTile[] monsterField;
	public CardTile[] stField;
	public CardTile extra;
	public ArrayList<CardTile> hand;
	//private int handcount;
	private int monstercount;
	private int sptrcount;
	//private int handcount;
	
	private int x;
	private int y;
	public Phase phase;
	
	public board(int player){
		monstercount = 0;
		sptrcount = 0;
		//handcount = 0;
		this.player = player;
		generateField();
		phase = new Phase(player);
	}
	
	private void generateField(){
		genGrave();
		genFieldsp();
		genExtra();
		genDeck();
		genstField();
		genMonsterField();
		genHand();
	}
	
	public boolean isFull(CardTile.tileType ttype){
		switch (ttype){
		case DECK:
			return deck.isFull();
		case FIELDSP:
			return fieldsp.isFull();
		case GRAVE:
			return grave.isFull();
		case EXTRA:
			return extra.isFull();
		case MONSTER:
			for(int cidx=0; cidx < monsterField.length; cidx ++){
				if(monsterField[cidx].canPlace()){
					return false;
				}
			}
			return true;
		case SP_TR:
			for(int cidx=0; cidx < stField.length; cidx ++){
				if(stField[cidx].canPlace()){
					return false;
				}
			}
			return true;
		default:
			return false;
		}
	}
	private void genGrave(){
		if(player == 0){
			x = (GameDriver.CARDWIDTH *6) + (GameDriver.CARDSPACE * 7);
			y = (GameDriver.CARDHEIGHT * 3) + (GameDriver.CARDSPACE * 3) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDSPACE);
			y = (GameDriver.CARDHEIGHT * 2) + (GameDriver.CARDSPACE * 3);
		}
		grave = new CardTile(CardTile.tileType.GRAVE, x, y);
		getChildren().add(grave);
	}
	
	private void genExtra(){
		if(player == 0){
			x = (GameDriver.CARDSPACE);
			y = (GameDriver.CARDHEIGHT * 4) + (GameDriver.CARDSPACE * 4) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDWIDTH *6) + (GameDriver.CARDSPACE * 7);
			y = (GameDriver.CARDSPACE * 2) + (GameDriver.CARDHEIGHT);
		}
		extra = new CardTile(CardTile.tileType.EXTRA, x, y);
		getChildren().add(extra);
	}
	
	private void genDeck(){
		if(player == 0){
			x = (GameDriver.CARDWIDTH *6) + (GameDriver.CARDSPACE * 7);
			y = (GameDriver.CARDHEIGHT * 4) + (GameDriver.CARDSPACE * 4) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDSPACE);
			y = (GameDriver.CARDSPACE * 2) + GameDriver.CARDHEIGHT;
		}
		deck = new CardTile(CardTile.tileType.DECK, x, y);
		getChildren().add(deck);
	}
	
	private void genFieldsp(){
		if(player == 0){
			x = (GameDriver.CARDSPACE);
			y = (GameDriver.CARDHEIGHT * 3) + (GameDriver.CARDSPACE * 3) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDWIDTH *6) + (GameDriver.CARDSPACE * 7);
			y = (GameDriver.CARDSPACE * 3) + (GameDriver.CARDHEIGHT * 2);
		}
		fieldsp = new CardTile(CardTile.tileType.FIELDSP, x, y);
		getChildren().add(fieldsp);
	}
	
	private void genMonsterField(){
		monsterField = new CardTile[5];
		if(player == 0){
			for(int col=0; col < 5; col ++){
				x = (GameDriver.CARDSPACE * (col+2)) + (GameDriver.CARDWIDTH * (col+1));
				y = (GameDriver.CARDHEIGHT * 3) + (GameDriver.CARDSPACE * 3) + GameDriver.SIDESPACE;
				monsterField[col] = new CardTile(CardTile.tileType.MONSTER, x, y);
			}
		}
		else{
			for(int col=0; col <5; col ++){
				x = (GameDriver.CARDSPACE * (col+2)) + (GameDriver.CARDWIDTH * (col+1));
				y = (GameDriver.CARDSPACE * 3) + (GameDriver.CARDHEIGHT * 2);
				monsterField[col] = new CardTile(CardTile.tileType.MONSTER, x, y);
			}
		}
		getChildren().addAll(monsterField);
	}
	
	private void genstField(){
		stField = new CardTile[5];
		if(player == 0){
			for(int col=0; col < 5; col ++){
				x = (GameDriver.CARDSPACE * (col+2)) + (GameDriver.CARDWIDTH * (col+1));
				y = (GameDriver.CARDHEIGHT * 4) + (GameDriver.CARDSPACE * 4) + GameDriver.SIDESPACE;
				stField[col] = new CardTile(CardTile.tileType.SP_TR, x, y);
			}
		}
		else{
			for(int col=0; col <5; col ++){
				x = (GameDriver.CARDSPACE * (col+2)) + (GameDriver.CARDWIDTH * (col+1));
				y = (GameDriver.CARDSPACE * 2) + (GameDriver.CARDHEIGHT * 1);
				stField[col] = new CardTile(CardTile.tileType.SP_TR, x, y);
			}
		}
		getChildren().addAll(stField);
	}
	
	public CardTile cardMovement(Card card, CardTile.tileType to, boolean init){
		if(!init){
			if(card.placement == CardTile.tileType.HAND){
				removeCardfromHand(card);
			}
			else{
				card.tile.removeCard(card);
			}
		}
		switch (to){
			case DECK:
				deck.placeCard(card);
				return deck;
			case FIELDSP:
				fieldsp.placeCard(card);
				return fieldsp;
			case GRAVE:
				grave.placeCard(card);
				return grave;
			case EXTRA:
				extra.placeCard(card);
				return extra;
			case MONSTER:
				return monsterPlacement(card);
			case SP_TR:
				return sptrPlacement(card);
			default:
				return addCardtoHand(card);
		}
	}
	
	private CardTile monsterPlacement(Card card){
		CardTile curtile;
		for(int tidx=0; tidx < monsterField.length; tidx++){
			curtile = monsterField[tidx];
			if(curtile.canPlace()){
				curtile.placeCard(card);
				return curtile;
			}
		}
		return null;
	}
	
	private CardTile sptrPlacement(Card card){
		CardTile curtile;
		for(int tidx=0; tidx < stField.length; tidx++){
			curtile = stField[tidx];
			if(curtile.canPlace()){
				curtile.placeCard(card);
				return curtile;
			}
		}
		return null;
	}
	private void genHand(){
		hand = new ArrayList<CardTile>();
//		for(int tidx=0; tidx < CardTile.MAXCAPACITY; tidx++){
//			hand[tidx] = new CardTile(CardTile.tileType.HAND, 100, 100);
//		}
		//getChildren().addAll(hand);
		//handcount = 0;
	}
	
	public CardTile addCardtoHand(Card card){
		//handcount += 1;
		CardTile newtile = new CardTile(CardTile.tileType.HAND, 0,0);
		hand.add(newtile);
		getChildren().add(newtile);
		newtile.placeCard(card);
		updateHand();
		return newtile;
	}
	
	public void removeCardfromHand(Card card){
		//handcount -= 1;
		CardTile oldtile = card.tile;
		hand.remove(oldtile);
		getChildren().remove(oldtile);
		updateHand();
	}
	
	private void updateHand(){
		int ypos;
		if(player == 0){
			ypos = GameDriver.SIDESPACE + (GameDriver.CARDHEIGHT * ((2 * GameDriver.ROWS)-1)) + (GameDriver.CARDSPACE * 5);
		}
		else{
			ypos = GameDriver.CARDSPACE;
		}
		int handcount = hand.size();
		double border = (GameDriver.COLUMNS * GameDriver.CARDWIDTH) + ((GameDriver.COLUMNS-1)* GameDriver.CARDSPACE);
		double spreadsize = handcount * GameDriver.CARDWIDTH;
		double adjustfactor;
		CardTile curtile;
		if(border >= spreadsize){
			adjustfactor = (border-spreadsize)/2 + GameDriver.CARDSPACE;
			for(int cidx=0; cidx < hand.size(); cidx++){
				curtile = hand.get(cidx);
				curtile.moveTile(adjustfactor, ypos);
				adjustfactor += GameDriver.CARDWIDTH;
				curtile.getTopCard().updateLocation(curtile);
			}
		}
		else{
			adjustfactor = ((double)1/(handcount-1))*(border-GameDriver.CARDWIDTH);
			double relativepos = GameDriver.CARDSPACE;
			for(int cidx=0; cidx < hand.size(); cidx++){
				curtile = hand.get(cidx);
				curtile.moveTile(relativepos, ypos);
				relativepos += adjustfactor;
				curtile.getTopCard().updateLocation(curtile);
			}
			
			
		}
	}
}
