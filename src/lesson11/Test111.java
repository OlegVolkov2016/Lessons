package lesson11;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test111 extends Application {
	
	private String name;
	//private Pane pane;
	private Scene scene;
	private Group rootGroup;
	private Text rootText;
	
	public Test111() {
		// TODO Auto-generated constructor stub
		name = "Example";
	//	pane = new Pane();
		scene = new Scene(rootGroup,300,400,Color.BLUEVIOLET);
		rootGroup = new Group();
		rootText = new Text(20,20,"Example text");
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		String name = "Example";
	//	pane = new Pane();
		
		Group rootGroup = new Group();
		Scene scene = new Scene(rootGroup,300,400,Color.BLUEVIOLET);
		Text rootText = new Text(20,20,"Example text");
		primaryStage.setTitle(name);
		primaryStage.setWidth(300);
		primaryStage.setHeight(400);
		rootGroup.getChildren().add(rootText);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	public static void main(String[] args) {
		
		launch(args);
	}

}
