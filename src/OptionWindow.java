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
	Rectangle window;
	VBox box;
	Button[] commands;
	
	OptionWindow(Stage stage, Scene scene, double x, double y){
		this.scene = scene;
		this.stage = stage;
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
	private void genScene(){
		box = new VBox();
		Button b = new Button("Summon");
		Button c = new Button("Set");
		Button d = new Button("Send to Grave");
		box.getChildren().addAll(b,c,d);
		this.getContent().addAll(box);
	}
	
	public void onEvent(double x, double y) {
		if(this.isShowing()) {
			this.hide();
		}
		show(stage, (x-scene.getWindow().getX()) + scene.getWindow().getX(), (y-scene.getWindow().getY()) + scene.getWindow().getY());
	}
	
	private void onMonsterStage(){
		Button atk = new Button("Attack");
		Button set = new Button("Set");
	}
}
