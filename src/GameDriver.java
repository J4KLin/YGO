import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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
	private board playerside;
	private board oppside;
	private Group boardGroup = new Group();
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
		windowPane.openWindow();
		
		return root;
	}
	
	private Parent createContent(Stage primaryStage, Scene scene) {
		popup_menu = new OptionWindow(primaryStage, scene, 0, 0);
		
		boardPane.getChildren().addAll(boardGroup);
		playerside = new board(0, popup_menu,boardGroup, boardPane);
		oppside = new board(1, popup_menu, boardGroup, boardPane);
		
		String yugi = System.getProperty("user.dir") + "\\src\\assets\\yugi";
		String kaiba = System.getProperty("user.dir") + "\\src\\assets\\kaiba";
		playerside.loadDeck(DeckBuilder.buildDeck(boardPane, new File(yugi)));
		oppside.loadDeck(DeckBuilder.buildDeck(boardPane, new File(kaiba)));
		return root;
	}
	

	private void addBackground() {
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
