import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class board extends StackPane {
	public int player;
	public CardTile grave;
	public CardTile deck;
	public CardTile fieldsp;
	public CardTile[] monsterField;
	public CardTile[] stField;
	public CardTile extra;
	public ArrayList<CardTile> hand;
	
	public Group group;
	
	private double x;
	private double y;
	public Phase phase;
	public OptionWindow option;
	private Pane parentPane;
	
	public ImageView cardView;	//Magnified Card Displayer on the Upper Left Corner of the Board
	public Rectangle cvBorder;	//Lower layer on which cardView sits upon
	
	
	public board(int player , OptionWindow option, Group group, Pane parentPane){
		this.option = option;
		this.group = group;
		this.player = player;
		this.parentPane = parentPane;
		generateField();
		phase = new Phase(player);
	}

	private void generateField(){
		genCardViewer();
		genstField();
		genMonsterField();
		genGrave();
		genFieldsp();
		genExtra();
		genDeck();
		genHand();
	}
	
	//One time generation of cardView
	private void genCardViewer(){
		cvBorder = new Rectangle(GameDriver.DESWIDTH, GameDriver.DESHEIGHT, Color.TRANSPARENT);
		cvBorder.setStroke(Color.valueOf("#2297DC"));
		//cvBorder.setStrokeWidth(5);
		cvBorder.strokeWidthProperty().bind(parentPane.widthProperty().multiply(5/GameDriver.INITWIDTH));
		cvBorder.heightProperty().bind(parentPane.heightProperty().multiply(GameDriver.DESHEIGHT/GameDriver.INITHEIGHT));
		cvBorder.widthProperty().bind(parentPane.widthProperty().multiply(GameDriver.DESWIDTH/GameDriver.INITWIDTH));
		cvBorder.xProperty().bind(parentPane.widthProperty().multiply(GameDriver.CARDSPACE/GameDriver.INITWIDTH));
		cvBorder.yProperty().bind(parentPane.heightProperty().multiply(GameDriver.CARDSPACE/GameDriver.INITHEIGHT));
		group.getChildren().add(cvBorder);
		cardView = new ImageView();
		cardView.fitHeightProperty().bind(parentPane.heightProperty().multiply(GameDriver.DESHEIGHT/GameDriver.INITHEIGHT));
		cardView.fitWidthProperty().bind(parentPane.widthProperty().multiply(GameDriver.DESWIDTH/GameDriver.INITWIDTH));
		cardView.xProperty().bind(parentPane.widthProperty().multiply(GameDriver.CARDSPACE/GameDriver.INITWIDTH));
		cardView.yProperty().bind(parentPane.heightProperty().multiply(GameDriver.CARDSPACE/GameDriver.INITHEIGHT));
		group.getChildren().add(cardView);
	}
	
	
	public void viewCard(Card card){
		cardView.toFront();
		cardView.setImage(card.cardface);
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
			x = (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT *5)+ (GameDriver.CARDSPACE * 8) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDHEIGHT * 3) + (GameDriver.CARDSPACE * 3) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDSPACE*2) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDHEIGHT * 2) + (GameDriver.CARDSPACE * 3);
		}
		grave = new CardTile(parentPane, this, group, CardTile.tileType.GRAVE, option, x, y);
		group.getChildren().add(grave);
	}
	
	private void genExtra(){
		if(player == 0){
			x = (GameDriver.CARDSPACE * 2) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDHEIGHT * 4) + (GameDriver.CARDSPACE * 4) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT *5)+ (GameDriver.CARDSPACE * 8) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDSPACE * 2) + (GameDriver.CARDHEIGHT);
		}
		extra = new CardTile(parentPane, this, group, CardTile.tileType.EXTRA, option, x, y);
		group.getChildren().add(extra);
	}
	
	private void genDeck(){
		if(player == 0){
			x = (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT *5)+ (GameDriver.CARDSPACE * 8) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDHEIGHT * 4) + (GameDriver.CARDSPACE * 4) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDSPACE*2) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDSPACE * 2) + GameDriver.CARDHEIGHT;
		}
		deck = new CardTile(parentPane, this, group, CardTile.tileType.DECK, option, x, y);
		getChildren().add(deck);
		group.getChildren().add(deck);
	}
	
	private void genFieldsp(){
		if(player == 0){
			x = (GameDriver.CARDSPACE * 2) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDHEIGHT * 3) + (GameDriver.CARDSPACE * 3) + GameDriver.SIDESPACE;
		}
		else{
			x = (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT *5)+ (GameDriver.CARDSPACE * 8) + GameDriver.DESWIDTH;
			y = (GameDriver.CARDSPACE * 3) + (GameDriver.CARDHEIGHT * 2);
		}
		fieldsp = new CardTile(parentPane, this, group, CardTile.tileType.FIELDSP, option, x, y);
		getChildren().add(fieldsp);
		group.getChildren().add(fieldsp);
	}
	
	private void genMonsterField(){
		monsterField = new CardTile[5];
		if(player == 0){
			for(int col=0; col < 5; col ++){
				x = (GameDriver.CARDSPACE * (col+3)) + (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT * col) + GameDriver.DESWIDTH;
				y = (GameDriver.CARDHEIGHT * 3) + (GameDriver.CARDSPACE * 3) + GameDriver.SIDESPACE;
				monsterField[col] = new CardTile(parentPane, this, group, CardTile.tileType.MONSTER, option, x, y);
				group.getChildren().add(monsterField[col]);
			}
		}
		else{
			for(int col=0; col <5; col ++){
				x = (GameDriver.CARDSPACE * (col+3)) + (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT * col) + GameDriver.DESWIDTH;
				y = (GameDriver.CARDSPACE * 3) + (GameDriver.CARDHEIGHT * 2);
				monsterField[col] = new CardTile(parentPane, this, group, CardTile.tileType.MONSTER, option, x, y);
				group.getChildren().add(monsterField[col]);
			}
		}
	}
	
	private void genstField(){
		stField = new CardTile[5];
		if(player == 0){
			for(int col=0; col < 5; col ++){
				x = (GameDriver.CARDSPACE * (col+3)) + (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT * col) + GameDriver.DESWIDTH;
				y = (GameDriver.CARDHEIGHT * 4) + (GameDriver.CARDSPACE * 4) + GameDriver.SIDESPACE;
				stField[col] = new CardTile(parentPane, this, group, CardTile.tileType.SP_TR, option, x, y);
				group.getChildren().add(stField[col]);
			}
		}
		else{
			for(int col=0; col <5; col ++){
				x = (GameDriver.CARDSPACE * (col+3)) + (GameDriver.CARDWIDTH) + (GameDriver.CARDHEIGHT * col) + GameDriver.DESWIDTH;
				y = (GameDriver.CARDSPACE * 2) + (GameDriver.CARDHEIGHT * 1);
				stField[col] = new CardTile(parentPane, this, group, CardTile.tileType.SP_TR, option, x, y);
				group.getChildren().add(stField[col]);
			}
		}
	}
	
	public CardTile cardMovement(Card card, CardTile.tileType to, boolean init){
		if(!init){
			if(card.fieldPlacement == CardTile.tileType.HAND){
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
	}
	
	public CardTile addCardtoHand(Card card){
		CardTile newtile = new CardTile(parentPane, this, group, CardTile.tileType.HAND, option, 0,0);
		hand.add(newtile);
		group.getChildren().add(newtile);
		newtile.placeCard(card);
		updateHand();
		return newtile;
	}
	
	public void removeCardfromHand(Card card){
		CardTile oldtile = card.tile;
		hand.remove(oldtile);
		group.getChildren().remove(oldtile);
		updateHand();
	}
	
	private void updateHand(){
		double ypos;
		if(player == 0){
			ypos = GameDriver.SIDESPACE + (GameDriver.CARDHEIGHT * ((2 * GameDriver.ROWS)-1)) + (GameDriver.CARDSPACE * 5);
		}
		else{
			ypos = GameDriver.CARDSPACE;
		}
		int handcount = hand.size();
		double fitsize = (2 * GameDriver.CARDWIDTH) + (5 * GameDriver.CARDHEIGHT)+ ((GameDriver.COLUMNS-1)* GameDriver.CARDSPACE);
		double spreadsize = handcount * GameDriver.CARDWIDTH;
		double adjustfactor;
		double desspace = GameDriver.DESWIDTH + GameDriver.CARDSPACE;
		CardTile curtile;
		if(fitsize >= spreadsize){
			adjustfactor = (fitsize-spreadsize)/2 + GameDriver.CARDSPACE;
			for(int cidx=0; cidx < hand.size(); cidx++){
				curtile = hand.get(cidx);
				curtile.moveTile(adjustfactor + desspace, ypos);
				adjustfactor += GameDriver.CARDWIDTH;
				curtile.getTopCard().updateLocation(curtile);
			}
		}
		else{
			adjustfactor = ((double)1/(handcount-1))*(fitsize-GameDriver.CARDWIDTH);
			double relativepos = GameDriver.CARDSPACE;
			for(int cidx=0; cidx < hand.size(); cidx++){
				curtile = hand.get(cidx);
				curtile.moveTile(relativepos + desspace, ypos);
				relativepos += adjustfactor;
				curtile.getTopCard().updateLocation(curtile);
			}	
		}
	}
	
	public void loadDeck(Card[] cards) {
		for(Card card: cards) {
			card.toDeck(this, true);
		}
	}
}