package lesson12;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Test121 extends Application{
	
	public static String decimalToBasis(int value, int basis) {
		char[] values = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		if ((value / basis) > 0)
			return decimalToBasis(value / basis, basis) + values[value % basis];
		else return values[value % basis] + "";
	}
	
	public static boolean isRightValue(String result, int basis) {
		int x = 0;
		for (int i = 0; i < result.length(); i++) {
			x = (int) result.toUpperCase().charAt(i);
			if ((x > 47) && (x < 58)) x -= 48;
			else if ((x > 64) && (x < 71)) x -= 55;
			else x = -1;
			if ((x < 0) || (x > basis-1)) return false;
		}
		return true;
	}
	
	public static int basisToDecimal(String result, int basis) {
		int value = 0, x = 0;
		for (int i = 0; i < result.length(); i++) {
			x = (int) result.toUpperCase().charAt(i);
			if ((x > 47) && (x < 58)) x -= 48;
			else if ((x > 64) && (x < 71)) x -= 55;
			else x = 0;
			value += x * Math.pow(basis,result.length()-1-i);
		}
		return value;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);
		gpane.setPadding(new Insets(20,20,20,20));
		gpane.setId("root");
		Label decimal = new Label("Decimal");
		decimal.setFont(Font.font("Tahoma",FontWeight.MEDIUM,16));
		gpane.add(decimal, 0, 0);
		TextField decimalT = new TextField();
		gpane.add(decimalT, 0, 1);
		Label basis = new Label("Basis");
		basis.setFont(Font.font("Tahoma",FontWeight.MEDIUM,16));
		gpane.add(basis, 0, 2);
		TextField basisT = new TextField();
		gpane.add(basisT, 0, 3);
		Label value = new Label("Value in basis");
		value.setFont(Font.font("Tahoma",FontWeight.MEDIUM,16));
		gpane.add(value, 2, 0);
		TextField valueT = new TextField();
		gpane.add(valueT, 2, 1);
		Button calc = new Button("To Basis");
		gpane.add(calc, 1, 1);
		calc.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				valueT.setText(decimalToBasis(Integer.parseInt(decimalT.getText()), Integer.parseInt(basisT.getText())));
			}
		});
		Button calcback = new Button("To Decimal");
		gpane.add(calcback, 1, 3);
		calcback.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				decimalT.setText(basisToDecimal(valueT.getText(), Integer.parseInt(basisT.getText()))+"");
			}
		});
		
		Scene scene = new Scene(gpane, 600, 400);
		scene.getStylesheets().add(Test121.class.getResource("Test121.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}



}
