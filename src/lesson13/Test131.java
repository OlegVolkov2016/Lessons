package lesson13;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Test131 {
	
	String s;
	int i; 
	double d;
	static ArrayList<Test131> al = new ArrayList<Test131>();
	
	public Test131(String s, int i, double d) {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.i = i;
		this.d = d;
		al.add(this);
		
	}
	
	public static void main(String[] args) {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("I:/out.txt"));
			Test131 t1 = new Test131("bshgdaj", 435, 256.145);
			Test131 t2 = new Test131("hhdfhfh", 567, 45.4567);
			dos.writeUTF(t1.s);
			dos.writeInt(t1.i);
			dos.writeDouble(t1.d);
			dos.writeUTF(t2.s);
			dos.writeInt(t2.i);
			dos.writeDouble(t2.d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
