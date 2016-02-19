package lesson15;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeSerialization {
	
	public static void main(String[] args) {
		Students student = null;
		try {
			FileInputStream fileIn = new FileInputStream("students.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				student = (Students) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			in.close();
			fileIn.close();
			System.out.println("OK");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student.mainCheck();
	}

}
