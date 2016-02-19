package lesson15;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialization {
	
	public static void main(String[] args) {
		Students student = new Students();
		student.name = "Alex";
		student.address = "Kiev";
		try {
			FileOutputStream fileOut = new FileOutputStream("students.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(student);
			out.flush();
			out.close();
			fileOut.close();
			System.out.println("OK");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
