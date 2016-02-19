package lesson11;

import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test1 extends Application {
	
	static double addAB(double a, double b)  {
		return a + b;
	}
	
	static double subAB(double a, double b) {
		return a - b;
	}
	
	static double mulAB(double a, double b) {
		return a * b;
	}

	static double divAB(double a, double b) {
		return a / b;
	}
	
	static double modAB(double a, double b) {
		return a % b;
	}
	
	static boolean divByZero(double b) {
		if (b != 0) return false;
		else {
			System.out.println("Division by zero!");
			return true;
		}
	}
	
	public static boolean isRepeat(Scanner sc) {
		String answer;
		System.out.print("Do you want to repeat calculations? (y - to repeat): ");
		answer = sc.next();
		answer = answer.toLowerCase().substring(0, 1);
		if (answer.equals("y")) return true;
		else return false;
	}
	
	static boolean isInRange(double a, int N, int M) {
		if ((a >= N) & (a <= M)) return true;
		else return false;
	}
	
	static double minABC(double a, double b, double c) {
		return Math.min(a, Math.min(b,c));
		// if (a < b) if (a < c) return a;
		// else return c;
		// else if (b < c) return b;
		// else return c;
	}
	
	static boolean isBelongsToRectangle(double a, double b, double c, double d, double x, double y) {
		if ((x >= a) & (x <= c) & (y <= b) & (y >= d)) return true;
		else return false;
	}
	
	static double monthPayment(double a, double b) {
		return (a * b / 100 / 12);
	}
	
	static double averageABC(double a, double b, double c) {
		return ((a + b + c) / 3);
	}
	
	static double solutionOfLinearEquation(double a, double b) {
		if (a != 0) return ((-b) / a);
		else return 0;
	}
	
	static void printDividers(int m) {
		int i;
		for (i = 1; i <= (int) (m / 2); i++) if ((m % i) == 0) System.out.print(i + ", ");
		System.out.println(m + ".");
	}
	
	public static boolean isSimple(int m) {
		int i;
		if (m <= 1) return false; 
		for (i = 2; i <= (int) (m / 2); i++) if ((m % i) == 0) return false;
		return true;
	}
	

	@Override
	public void start(Stage pStage) throws Exception {
		// TODO Auto-generated method stub
		pStage.setTitle("Calc");
		HBox hbox = new HBox();
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		Text t = new Text();
		Button button1 = new Button("+");
		Button button2 = new Button("-");
		Button button3 = new Button("*");
		Button button4 = new Button("/");
		Button button5 = new Button("%");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String str = "";
				str += Double.parseDouble(tf1.getText()) + button1.getText() +  Double.parseDouble(tf2.getText()) + " = " + addAB(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()));
				t.setText(str);
			}
		});
		button2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String str = "";
				str += Double.parseDouble(tf1.getText()) + button1.getText() +  Double.parseDouble(tf2.getText()) + " = " + subAB(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()));
				t.setText(str);
			}
		});
		button3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String str = "";
				str += Double.parseDouble(tf1.getText()) + button1.getText() +  Double.parseDouble(tf2.getText()) + " = " + mulAB(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()));
				t.setText(str);
			}
		});
		button4.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String str = "";
				str += Double.parseDouble(tf1.getText()) + button1.getText() +  Double.parseDouble(tf2.getText()) + " = " + divAB(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()));
				t.setText(str);
			}
		});
		button5.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String str = "";
				str += Double.parseDouble(tf1.getText()) + button1.getText() +  Double.parseDouble(tf2.getText()) + " = " + modAB(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()));
				t.setText(str);
			}
		});
		
		hbox.setPadding(new Insets(40,40,40,40));
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(tf1, tf2, button1, button2, button3, button4, button5, t);
		
		Scene scene = new Scene(hbox, Color.BEIGE);
		pStage.setScene(scene);
		pStage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
//
//		double a, b, c, d, x, y, result;
//		int choise = 0, i, m, n, sum;
//		boolean repeat = false;
//		final int N = 1, M = 100;
//		final double KG_IN_POUND = 405.9; 
//		// Test1 test = new Test1();3
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Lesson One");
//
//		System.out.println("\nTask No. 1\n");
//		do {
//			System.out.print("Input first value: ");
//			a = sc.nextDouble();
//			System.out.print("Input second value: ");
//			b = sc.nextDouble();
//			do {
//				System.out.println("Choose the operation:");
//				System.out.println("1) a + b");
//				System.out.println("2) a - b");
//				System.out.println("3) a * b");
//				System.out.println("4) a / b");
//				System.out.println("5) a % b");
//				System.out.print("Make your choise: ");
//				choise = sc.nextInt();
//				if ((choise < 1) | (choise > 5)) System.out.println("Repeat please...");
//			} while ((choise < 1) | (choise > 5));
//			if (choise == 1) System.out.println(a + " + " + b + " = " + addAB(a,b));
//			else if (choise == 2) System.out.println(a + " - " + b + " = " + subAB(a,b));
//			else if (choise == 3) System.out.println(a + " * " + b + " = " + mulAB(a,b));
//			else {
//				if (!divByZero(b))
//					if (choise == 4) System.out.println(a + " / " + b + " = " + divAB(a,b));
//					else System.out.println(a + " * " + b + " = " + modAB(a,b));
//			}
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//		
//		System.out.println("\nTask No. 2\n");
//		do {
//			System.out.print("Input value: ");
//			a = sc.nextDouble();
//			System.out.print("Value " + a + " is ");
//			if (!(isInRange(a,N,M))) System.out.print(" not ");
//			System.out.println("in the range from " + N + " to " + M + ".");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//		
//		System.out.println("\nTask No. 3\n");
//		do {
//			System.out.print("Input value: ");
//			i = sc.nextInt();
//			System.out.print("Value " + i + " is ");
//			if ((i % 2) == 0) System.out.println("even.");
//			else System.out.println("odd.");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//		
//		System.out.println("\nTask No. 4\n");
//		do {
//			System.out.print("Input first value: ");
//			a = sc.nextDouble();
//			System.out.print("Input second value: ");
//			b = sc.nextDouble();
//			System.out.print("Input third value: ");
//			c = sc.nextDouble();
//			result = minABC(a,b,c);
//			System.out.println("Minimum of " + a + ", " + b + ", " + c + " is " + result);
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 5\n");
//		do {
//			System.out.print("Input value: ");
//			a = sc.nextDouble();
//			System.out.print("Value " + a + " is ");
//			if (a < 0) System.out.println("negative.");
//			else if (a > 0) System.out.println("positive.");
//			else System.out.println("zero.");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 6\n");
//		do {
//			System.out.print("Input value: ");
//			a = sc.nextDouble();
//			System.out.print("Absolute value of " + a + " is ");
//			System.out.println(Math.abs(a));
//			if (a < 0) System.out.println((-a) + ".");
//			else System.out.println(a + ".");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 7\n");
//		do {
//			System.out.print("Input left value: ");
//			a = sc.nextDouble();
//			System.out.print("Input up value: ");
//			b = sc.nextDouble();
//			System.out.print("Input right value: ");
//			c = sc.nextDouble();
//			System.out.print("Input down value: ");
//			d = sc.nextDouble();
//			System.out.print("Input X value: ");
//			x = sc.nextDouble();
//			System.out.print("Input y value: ");
//			y = sc.nextDouble();
//			System.out.print("The point ("+ x + ", " + y + ") is ");
//			if (!(isBelongsToRectangle(a,b,c,d,x,y))) System.out.print(" not ");
//			System.out.println("belongs to rectangle (" + a + ", " + b + ") , (" + c + ", " + d + ").");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//		
//		System.out.println("\nTask No. 8\n");
//		do {
//			System.out.print("Input value of deposit: ");
//			a = sc.nextDouble();
//			System.out.print("Input the year rate (%): ");
//			b = sc.nextDouble();
//			System.out.println("The month payment is :" + monthPayment(a,b) + ".");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 9\n");
//		do {
//			System.out.print("Input amount of money: ");
//			x = sc.nextDouble();
//			System.out.print("Input price of item: ");
//			y = sc.nextDouble();
//			if (!divByZero(y)) System.out.println("You can buy " + Math.floor(x/y) + " items, the change is " + (x % y) + ".");
//			else System.out.println("The price is cannot be 0.");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 10\n");
//		do {
//			System.out.print("Input amount of pounds: ");
//			a = sc.nextDouble();
//			System.out.println("This is " + (a * KG_IN_POUND) + " kilograms.");
//			System.out.print("Input amount of kilograms: ");
//			a = sc.nextDouble();
//			System.out.println("This is " + (a / KG_IN_POUND) + " pounds.");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 11\n");
//		do {
//			System.out.print("Input first value: ");
//			a = sc.nextDouble();
//			System.out.print("Input second value: ");
//			b = sc.nextDouble();
//			System.out.print("Input third value: ");
//			c = sc.nextDouble();
//			System.out.println("The average value of "+ a + ", " + b + ", " + c + " is :" + averageABC(a,b,c));
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;		
//		
//		System.out.println("\nTask No. 12\n");
//		System.out.println("The solve of linerar equation ax + b = 0.\n");
//		do {
//			System.out.print("Input value a: ");
//			a = sc.nextDouble();
//			System.out.print("Input value b: ");
//			b = sc.nextDouble();
//			if (!divByZero(a)) System.out.println("The soution is " + solutionOfLinearEquation(a,b) + ".");
//			else System.out.println("The soution is not defined.");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;	
//
//		System.out.println("\nTask No. 13\n");
//		do {
//			System.out.print("Input value: ");
//			a = sc.nextDouble();
//			System.out.print("Input power: ");
//			b = sc.nextDouble();
//			System.out.println("The value in power is :" + Math.pow(a,b) + ".");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 14\n");
//		do {
//			System.out.println("Even nubmers from 1 to 100:");
//			for (i = 2; i <= 100; i+=2) System.out.print(i + ",");
//			System.out.println(".");
//			// repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 15\n");
//		do {
//			System.out.print("Input first value: ");
//			m = sc.nextInt();
//			System.out.print("Input second value: ");
//			n = sc.nextInt();
//			sum = 0;
//			if (m <= n) for (i = m; i <= n; i++) sum += i;
//			System.out.println("The sum of numbers from " + m + " to " + n + " is " + sum + ".");
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;
//
//		System.out.println("\nTask No. 16\n");
//		do {
//			System.out.print("Input value: ");
//			m = sc.nextInt();
//			if (m != 0) {
//				System.out.println("Dividers of " + m + " are:");
//				printDividers(m);
//			}
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;		
//		
//		System.out.println("\nTask No. 17\n");
//		do {
//			System.out.print("Input value: ");
//			m = sc.nextInt();
//			if (m != 0) {
//				System.out.print("The value " + m + " is ");
//				if (!isSimple(m)) System.out.print("not ");
//				System.out.println("simple.");
//			}
//			repeat = isRepeat(sc);
//		} while (repeat);
//		repeat = false;		
//
//		System.out.println("The end.");
//		sc.close();
	}

}
