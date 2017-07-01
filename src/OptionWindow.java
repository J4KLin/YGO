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
	Scene scene;
	Stage stage;
	Pane window;
	VBox box;
	double windowheight;
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
	
//	private void genScene(){
//		window = new Rectangle();
//		window.setWidth(100);
//		window.setHeight(100);
//		window.setStroke(Color.valueOf("#000000"));
//		window.setFill(Color.BLACK);
//		this.getContent().addAll(window);
//	}
	private void initAllButton(){
		initButton("Exit").setOnAction(e-> {this.hide();});
		//this.getContent().add(commands.get("Exit"));
		initButton("Attack");
		initButton("Summon");
		initButton("Set");
		initButton("Tribute Summon");
		initButton("Change to Defense");
		initButton("Change to Attack");
		initButton("Draw");
		initButton("Activate");
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
	
	private void genScene(){
		window = new Pane();
		window.setPrefSize(100, 200);
		addButton(commands.get("Exit"));
		addButton(commands.get("Attack"));
		//window.getChildren().add(commands.get("Exit"));
		//commands.get("Attack").setTranslateY(100);
		//window.getChildren().add(commands.get("Attack"));
		this.getContent().addAll(window);
	}
//	private void genScene(){
//		box = new VBox();
//		Button b = new Button("Summon");
//		Button c = new Button("Set");
//		Button d = new Button("Send to Grave");
//		Button f = new Button("Exit");
//		f.setOnAction(e ->{
//			this.hide();
//		});
//		box.getChildren().addAll(b,c,d,f);
//		this.getContent().addAll(box);
//	}
	
	public void onEvent(Card card, double x, double y) {
		if(this.isShowing()) {
			this.hide();
		}
		show(stage, (x-scene.getWindow().getX()) + scene.getWindow().getX(), (y-scene.getWindow().getY()) + scene.getWindow().getY());
	}
	
	private void configureChoices(Card card) {
		if (card instanceof Monster) {
			handleMonster(card);
		}
		else {
			handleSPTR(card);
		}
	}
	
	private void handleMonster(Card card) {
	}
	
	private void handleSPTR(Card card) {
		
	}
}
