import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardWindow extends ScrollPane{
	private double width;
	private double height;
	private double widthFactor;
	private double heightFactor;
	private double xFactor;
	private double yFactor;
	
	private Pane display;
	private double dHeight;
	private double dHeightFactor;
	
	public int rows;
	
	public Card[] cardStack;
	public Rectangle[] tester;
	
	public CardWindow(Pane parent) {
		initScrollPane(parent);
		initDisplayPane();
		
		rectangleCreator(23);
		refreshDisplay();
		this.setContent(display);

	}
	
	private void initScrollPane(Pane parent) {
		width = (2* GameDriver.SIDESPACE) + (GameDriver.COLUMNS * GameDriver.CARDWIDTH) + ((GameDriver.COLUMNS -1) * GameDriver.CARDSPACE);
		height = (2* GameDriver.SIDESPACE) + (4*GameDriver.CARDHEIGHT) + (3*GameDriver.CARDSPACE);
		widthFactor = width/GameDriver.INITWIDTH;
		heightFactor = height/GameDriver.INITHEIGHT;
		xFactor = (((GameDriver.INITWIDTH - GameDriver.DESWIDTH - GameDriver.CARDSPACE - width)/2) + GameDriver.DESWIDTH)/GameDriver.INITWIDTH;
		yFactor = ((GameDriver.INITHEIGHT - height)/2)/GameDriver.INITHEIGHT;

		this.prefHeightProperty().bind(parent.heightProperty().multiply(heightFactor));
		this.prefWidthProperty().bind(parent.widthProperty().multiply(widthFactor));
		this.translateXProperty().bind(parent.widthProperty().multiply(xFactor));
		this.translateYProperty().bind(parent.heightProperty().multiply(yFactor));
		//this.setStyle("-fx-background-color: ALICEBLUE; -fx-opactiy: .1;");
		this.setStyle("-fx-opacity: .8;");

		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	}
	
	private void initDisplayPane() {
		display = new Pane();
		dHeight = (12* GameDriver.CARDHEIGHT) + (11* GameDriver.CARDSPACE) + (2* GameDriver.SIDESPACE);
		dHeightFactor = dHeight/height;
		display.setPrefSize(width, dHeight);
		display.prefWidthProperty().bind(this.prefWidthProperty());
		display.prefHeightProperty().bind(this.prefHeightProperty().multiply(dHeightFactor));
	}
	
	private void rectangleCreator(int num) {
		tester = new Rectangle[num];
		for(int i=0; i<num; i++) {
			tester[i] = new Rectangle(GameDriver.CARDWIDTH, GameDriver.CARDHEIGHT);
			tester[i].setFill(Color.BLACK);
			tester[i].setStroke(Color.BLACK);
		}
	}
	
	private void refreshDisplay() {
		double curx = GameDriver.SIDESPACE;
		double cury = GameDriver.SIDESPACE;
		for(int idx=0 ;idx<tester.length; idx++) {
			if(idx%7 == 0 && idx>0) {
				cury += (GameDriver.CARDHEIGHT + GameDriver.CARDSPACE);
				curx = GameDriver.SIDESPACE;
			}
			addCard(tester[idx], curx, cury);
			curx += (GameDriver.CARDWIDTH + GameDriver.CARDSPACE);
		}
	}
	
	private void addCard(Rectangle r, double x, double y) {
		r.translateXProperty().bind(display.widthProperty().multiply(x/width));
		r.translateYProperty().bind(display.heightProperty().multiply(y/dHeight));
		r.widthProperty().bind(display.widthProperty().multiply(GameDriver.CARDWIDTH/width));
		r.heightProperty().bind(display.heightProperty().multiply(GameDriver.CARDHEIGHT/dHeight));
		display.getChildren().add(r);
	}
}
