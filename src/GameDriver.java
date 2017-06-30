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
import javafx.stage.Stage;
import javafx.stage.Window;


public class GameDriver extends Application {
	public static final int CARDWIDTH = 100;
	public static final int CARDHEIGHT = (int)(CARDWIDTH * 1.5);
	public static final int CARDSPACE = 5;
	public static final int SIDESPACE = 20;
	public static final int ROWS = 3;
	public static final int COLUMNS = 7;
	
	//private Group tileGroup = new Group();
	private AnchorPane root;
	private DeckBuilder deck1;
	private board playerside;
	private board oppside;
	private Group boardGroup = new Group();
	
	private int boardwidth;
	private int boardheight;
	private Stage primaryStage;
	
	private Button bt;
	OptionWindow popup;
	
	private Parent createRoot(){
		return root = new AnchorPane();
	}
	private Parent createContent(Stage primaryStage, Scene scene) {
		//root = new AnchorPane();
		popup = new OptionWindow(primaryStage, scene, 0, 0);
		boardwidth = (COLUMNS * (CARDWIDTH + CARDSPACE)) + CARDSPACE;
		boardheight = 2*(ROWS * (CARDHEIGHT + CARDSPACE)) + SIDESPACE;
		root.setPrefSize(boardwidth, boardheight);
		addBackground();
		root.getChildren().addAll(boardGroup);
		playerside = new board(0);
		oppside = new board(1);
		boardGroup.getChildren().add(playerside);
		boardGroup.getChildren().add(oppside);
		deck1 = new DeckBuilder(playerside, "s");
		root.getChildren().addAll(deck1);
		
		
		bt = new Button("here");
		root.getChildren().add(bt);
		bt.setOnMouseClicked(e -> {
			double x = e.getScreenX();
			double y = e.getScreenY();
			//popup = new OptionWindow(primaryStage, scene, x, y);
			popup.onEvent(x, y);
			
		});
		return root;
	}
	
	private void addBackground() {
		//ImageView imageview = new ImageView(new Image("file:///C:/Users/jack/eclipse-workspace/2DRPG/src/sky.jpg"));
//		String url = this.getClass().getResource("/assets/sky.jpg").toString();
//		ImageView imageview = new ImageView(new Image(url));
		ImageView imageview = new ImageView(new Image("/assets/rembkg.jpg"));
		imageview.setFitWidth(boardwidth);
		imageview.setFitHeight(boardheight);
		root.getChildren().add(imageview);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		//Scene scene = new Scene(createContent(primaryStage));
		Scene scene = new Scene(createRoot());
		//primaryStage.setTitle("GameBoard");
		//primaryStage.setScene(scene);
		createContent(primaryStage, scene);
		primaryStage.setTitle("GameBoard");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
