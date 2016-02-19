package lesson13;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test12 extends Application{
	
	static FileWriter fw;
	static FileReader fr;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Example GridPane");
		// TODO Auto-generated method stub
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);
		gpane.setPadding(new Insets(20,20,20,20));
		gpane.setId("root");
		
		
		Text t = new Text();
		t.setFont(Font.font("Tahoma",FontWeight.MEDIUM,16));
		t.setText("Welcome");
		t.setId("welcome");
		gpane.add(t, 0, 0, 2, 1);
		Label t1 = new Label("Username");
		t1.setFont(Font.font("Tahoma",FontWeight.MEDIUM,16));
		t1.setId("welcome");
		gpane.add(t1, 0, 1);
		TextField t2 = new TextField();
		gpane.add(t2, 1, 1);
		Label t3 = new Label("Password");
		t3.setFont(Font.font("Tahoma",FontWeight.MEDIUM,16));
		gpane.add(t3, 0, 2);
		PasswordField t4 = new PasswordField();
		gpane.add(t4, 1, 2);
		Button b1 = new Button("Authorize");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(b1);
		gpane.add(hbox,1,4);
		Text t5 = new Text();
		t5.setFont(Font.font("Tahoma",FontWeight.MEDIUM,16));
		gpane.add(t5,1,6);
		
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String str = "";
				int value = 0;
				try {
					while ((value = fr.read()) != -1) {
						str+=(char) value;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				if (str.contains("name="+t2.getText())) {
					t5.setFill(Color.RED);
					t5.setText("Authorization is not OK");
				}
				else {
					try {
						fw.write("name="+t2.getText()+",password="+t4.getText()+",");
						fw.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					t5.setFill(Color.BLUE);
					t5.setText("Authorization is OK");
				}
			}
		});
		
		Scene scene = new Scene(gpane, 300, 400);
		scene.getStylesheets().add(Test12.class.getResource("Test12.css").toExternalForm());
		primaryStage.setScene(scene);
		
		
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		try {
			fw = new FileWriter("I:/out.txt");
			fr = new FileReader("I:/out.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}
}
