package lesson11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test11 extends Application{

	@Override
	public void start(Stage pStage) throws Exception {
//		// TODO Auto-generated method stub
//		primaryStage.setTitle("Example");
//		primaryStage.setWidth(300);
//		primaryStage.setHeight(400);
//		Pane root = new Pane();
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
pStage.setTitle("Hello");
		
		Group rootGroup = new Group();
		Pane root  = new Pane();
		Text t = new Text (50, 20, "This is a text sample");
		t.setFont(Font.font("Verdana",FontWeight.BOLD,20));
		t.setFill(Color.RED);
		rootGroup.getChildren().add(t);
		VBox vbox = new VBox();
		Button button1 = new Button("OK");
		Button button2 = new Button("Cancel");
		TextField tf = new TextField();

		
		
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				t.setText(tf.getText());
			}
		});
		
		tf.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				t.setText(tf.getText());
			}
			
		});
		
		
		vbox.setPadding(new Insets(40,40,40,40));
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(tf, button1, button2, t);
		Scene scene = new Scene(vbox, Color.BEIGE);
		pStage.setScene(scene);
		pStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
	

}
