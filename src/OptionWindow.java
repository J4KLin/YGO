import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OptionWindow extends Popup{
	private static final String[] labels = {"Summon", "Set", "Tribute Summon", "Special Summon", "Activate",
			"Flip", "Change to Attack", "Change to Defense", "Attack", "View Card", "Exit", "Draw", "View Deck",
			"View Grave", "View Extra Deck"};
	Scene scene;
	Stage stage;
	Pane window;
	VBox box;
	double windowheight;
	Card curCard;
	//Button[] commands;
	HashMap<String, Button> commands = new HashMap();
	
	OptionWindow(Stage stage, Scene scene, double x, double y){
		this.scene = scene;
		this.stage = stage;
		windowheight = 0;
		initAllButton();
		genScene();
		//show(stage, x, y);
		//show(stage, (x-scene.getWindow().getX()) + scene.getWindow().getX(), (y-scene.getWindow().getY()) + scene.getWindow().getY());
	}
	
	private void initAllButton(){
		initButton("Exit").setOnAction(e-> {this.hide();});
		//this.getContent().add(commands.get("Exit"));
		initButton("Attack");
		initButton("Summon").setOnAction(e-> {
			curCard.cardMovement(CardTile.tileType.MONSTER, false);
			this.hide();
		});
		initButton("Set").setOnAction(e-> {
			curCard.setCard();
			this.hide();
		});;
		initButton("Tribute Summon");
		initButton("Change to Defense").setOnAction(e-> {
			((Monster) curCard).changetodefense();
			this.hide();
		});;
		initButton("Change to Attack").setOnAction(e-> {
			((Monster) curCard).changetoattack();
			this.hide();
		});;
		initButton("Draw").setOnAction(e-> {
			curCard.cardMovement(CardTile.tileType.HAND, false);
			this.hide();
		});;
		initButton("Activate").setOnAction(e-> {
			curCard.activate();
			this.hide();
		});;
		initButton("Special Summon");
		initButton("Flip").setOnAction(e-> {
			((Monster) curCard).flipCard();
			this.hide();
		});;
		initButton("View Card").setOnAction(e-> {
			System.out.println("Viewing");
			curCard.gameboard.viewCard(curCard);
			this.hide();
		});
		initButton("View Grave");
		initButton("View Extra Deck");
		initButton("View Deck");
	}
	
	private Button initButton(String button){
		Button b = new Button(button);
		commands.put(button, b);
		b.setPrefHeight(25);
		b.setMinHeight(25);
		b.setMaxHeight(25);
		return b;
	}
	
	private void addButton(Button button){
		button.setTranslateY(windowheight);
		//button.setBackground(null);
		window.getChildren().add(button);
		windowheight = windowheight + button.getPrefHeight();
	}
	
	private void removeAllButtons() {
		window.getChildren().clear();
		windowheight = 0;
	}
	
	private void genScene(){
		window = new Pane();
		window.setPrefSize(100, 200);
//		addButton(commands.get("Exit"));
//		addButton(commands.get("Attack"));
//		addButton(commands.get("Summon"));
		//window.getChildren().add(commands.get("Exit"));
		//commands.get("Attack").setTranslateY(100);
		//window.getChildren().add(commands.get("Attack"));
		this.getContent().addAll(window);
	}
	
	public void onEvent(Card card, double x, double y) {
		curCard = card;
		updateOptions(card, card.placement);
		if(this.isShowing()) {
			this.hide();
		}
		show(stage, (x-scene.getWindow().getX()) + scene.getWindow().getX(), (y-scene.getWindow().getY()) + scene.getWindow().getY());
	}
	
	private void updateOptions(Card card, CardTile.tileType type) {
		removeAllButtons();
		switch (type) {
			case DECK:
				handleDeck(card);
				break;
			case GRAVE:
				handleGrave(card);
				break;
			case EXTRA:
				handleExtra(card);
				break;
			case FIELDSP:
				handleFieldSP(card);
				break;
			case MONSTER:
				handleMonsterF(card);
				break;
			case SP_TR:
				handleSPTRF(card);
				break;
			default:
				handleHand(card);
				break;
		}
		addButton(commands.get("Exit"));
	}
	
	private void handleDeck(Card card) {
		addButton(commands.get("Draw"));
		addButton(commands.get("View Deck"));
	}
	
	private void handleGrave(Card card) {
		addButton(commands.get("View Grave"));
	}
	
	private void handleExtra(Card card) {
		addButton(commands.get("View Extra Deck"));
	}
	
	private void handleFieldSP(Card card) {
		addButton(commands.get("View Card"));
	}
	
	private void handleMonsterF(Card card) {
		addButton(commands.get("View Card"));
		addButton(commands.get("Activate"));
		addButton(commands.get("Flip"));
		addButton(commands.get("Attack"));
		addButton(commands.get("Change to Attack"));
		addButton(commands.get("Change to Defense"));
	}
	
	private void handleSPTRF(Card card) {
		addButton(commands.get("View Card"));
		addButton(commands.get("Activate"));
	}
	
	private void handleHand(Card card) {
		addButton(commands.get("View Card"));
		if (card instanceof Monster) {
			addButton(commands.get("Summon"));
			addButton(commands.get("Tribute Summon"));
			addButton(commands.get("Set"));
			addButton(commands.get("Activate"));
		}
		else {
			addButton(commands.get("Set"));
			addButton(commands.get("Activate"));
		}
	}
}
