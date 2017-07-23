import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;


public class GameDriver extends Application {
	public static final double CARDWIDTH = 100;
	public static final double CARDHEIGHT = (CARDWIDTH * 1.5);
	public static final double CARDSPACE = 10;
	public static final double SIDESPACE = 50;
	public static final double ROWS = 3;
	public static final double COLUMNS = 7;
	public static final double OVERSIZEDCOL = 5;	
	public static final double INITHEIGHT = ((ROWS*2)*(CARDSPACE+CARDHEIGHT)) + SIDESPACE;
	public static final double DESHEIGHT = INITHEIGHT/2;
	public static final double DESWIDTH = DESHEIGHT/1.5;
	public static final double INITWIDTH = ((COLUMNS+2)*CARDSPACE) + (2*CARDWIDTH) + (5*CARDHEIGHT) + DESWIDTH;
	
	private Pane root;
	private Pane boardPane;
	private CardWindow windowPane;
	private DeckBuilder deck1;
	private DeckBuilder deck2;
	private board playerside;
	private board oppside;
	private Group boardGroup = new Group();
	private Group cardGroup = new Group();
	
	private Stage primaryStage;
	
	OptionWindow popup_menu;
	
	private Parent createRoot(){
		root = new Pane();
		root.setPrefSize(INITWIDTH, INITHEIGHT);
		
		addBackground();
		
		boardPane = new Pane();
		boardPane.prefHeightProperty().bind(root.heightProperty());
		boardPane.prefWidthProperty().bind(root.widthProperty());
		root.getChildren().add(boardPane);
		
		windowPane = new CardWindow(root);
		root.getChildren().add(windowPane);
		return root;
	}
	
	private Parent createContent(Stage primaryStage, Scene scene) {
		popup_menu = new OptionWindow(primaryStage, scene, 0, 0);
		
		boardPane.getChildren().addAll(boardGroup);
		playerside = new board(0, popup_menu,boardGroup, boardPane);
		oppside = new board(1, popup_menu, boardGroup, boardPane);
		
		boardPane.getChildren().addAll(cardGroup);
		deck1 = new DeckBuilder(boardPane, playerside, "s", popup_menu, cardGroup, new File("C:/Users/jack/eclipse-workspace/2DRPG/src/assets/yugi"));
		//deck1 = new DeckBuilder(playerside, "s", popup_menu, cardGroup, new File("D:/javaworkspace/YGOT/YGO/src/assets/yugi"));
		deck2 = new DeckBuilder(boardPane, oppside, "s", popup_menu, cardGroup, new File("C:/Users/jack/eclipse-workspace/2DRPG/src/assets/kaiba"));
		//deck2 = new DeckBuilder(oppside, "s", popup_menu, cardGroup, new File("D:/javaworkspace/YGOT/YGO/src/assets/kaiba"));
		
		return root;
	}
	

	private void addBackground() {
		//ImageView imageview = new ImageView(new Image("file:///C:/Users/jack/eclipse-workspace/2DRPG/src/sky.jpg"));
//		String url = this.getClass().getResource("/assets/sky.jpg").toString();
//		ImageView imageview = new ImageView(new Image(url));
		ImageView imageview = new ImageView(new Image("/assets/miku.jpg"));
		imageview.fitWidthProperty().bind(root.widthProperty());
		imageview.fitHeightProperty().bind(root.heightProperty());
		root.getChildren().add(imageview);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Scene scene = new Scene(createRoot());
		createContent(primaryStage, scene);
		primaryStage.setTitle("GameBoard");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
