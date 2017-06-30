import javafx.scene.layout.StackPane;

public class board extends StackPane {
	public int player;
	public CardTile grave;
	public CardTile deck;
	public CardTile fieldsp;
	public CardTile[] monsterField;
	public CardTile[] stField;
	public CardTile extra;
	
	private int x;
	private int y;
	private int nodecounter;
	
	public board(int player){
		nodecounter = 0;
		this.player = player;
		generateField();
	}
	
	private void generateField(){
		genGrave();
		genFieldsp();
		genExtra();
		genDeck();
		genstField();
		genMonsterField();
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
	
}
