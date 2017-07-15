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
	
	//private Group tileGroup = new Group();
	private Pane root;
	private Pane basePane;
	private CardWindow windowPane;
	private DeckBuilder deck1;
	private DeckBuilder deck2;
	private board playerside;
	private board oppside;
	private Group boardGroup = new Group();
	private Group cardGroup = new Group();
	
	public double boardwidth;
	public double boardheight;
	private Stage primaryStage;
	
	private Button bt;
	OptionWindow popup;
	
	private Parent createRoot(){
		basePane = new Pane();
		root = new Pane();
		root.setPrefSize(INITWIDTH, INITHEIGHT);
		addBackground();
		root.getChildren().add(basePane);
		windowPane = new CardWindow(root);
		root.getChildren().add(windowPane);
		return root;
		
	}
	private Parent createContent(Stage primaryStage, Scene scene) {
		popup = new OptionWindow(primaryStage, scene, 0, 0);
		boardwidth = INITWIDTH;
		boardheight = INITHEIGHT;
		basePane.setPrefSize(boardwidth,  boardheight);
		basePane.getChildren().addAll(boardGroup);
		//
		playerside = new board(0, popup,boardGroup);
		oppside = new board(1, popup, boardGroup);
		basePane.getChildren().addAll(cardGroup);
		//
		deck1 = new DeckBuilder(playerside, "s", popup, cardGroup, new File("C:/Users/jack/eclipse-workspace/2DRPG/src/assets/yugi"));
		//deck1 = new DeckBuilder(playerside, "s", popup, cardGroup, new File("D:/javaworkspace/YGOT/YGO/src/assets/yugi"));
		deck2 = new DeckBuilder(oppside, "s", popup, cardGroup, new File("C:/Users/jack/eclipse-workspace/2DRPG/src/assets/kaiba"));
		//deck2 = new DeckBuilder(oppside, "s", popup, cardGroup, new File("D:/javaworkspace/YGOT/YGO/src/assets/kaiba"));
		
//		bt = new Button("here");
//		root.getChildren().add(bt);
//		bt.setOnMouseClicked(e -> {
//			simg(img);
			//img.setImage(new Image("/assets/darkmagician.png"));
//			
//		});
		return root;
	}
	
	private void simg(ImageView img){
		img.setImage(new Image("/assets/darkmagician.png"));
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
