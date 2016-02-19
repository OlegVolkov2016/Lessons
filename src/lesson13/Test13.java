package lesson13;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Test13 {
	
	static ArrayList<String> arr1;
	
	static void p(String s) {
		System.out.println(s);
	}
	
	static void dir(File f) {
		String[] arr = null;
		if (f.isDirectory()) {
		arr = f.list();
		}
		for (int i = 0; i < arr.length; i++) {
			arr1.add(arr[i]);
			System.out.println(arr[i]);
			File fnext = new File(f.getPath()+"/"+arr[i]);
			if (fnext.isDirectory()) {
				System.out.println("Dir");
				dir(fnext);
			}
		}
	}
	
	
	public static void main(String[] args) {
		File f1 = new File("I:/Expresssion.txt");
		File f2 = new File("I:/out.txt");
	 FileInputStream fis;
	try {
		fis = new FileInputStream(f1);
		 System.out.println("" +fis.available());
		 int content;
		 fis.skip(1000);
		 while ((content = fis.read()) != -1) {
			 System.out.print((char) content);
		 }
		 
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		FileOutputStream fos = new FileOutputStream("I:/out.txt");
		for (int i = 0; i < 2000; i++) {
			fos.write((byte) i);
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	

//		arr1 = new ArrayList<String>();
//		String[] arr = null;
//		dir(f1);
//		for (int i = 0; i < arr1.size(); i++) {
//			System.out.println(arr1.get(i));
//		}
//		p("F N" + f1.getName());
//		p("Path" + f1.getPath());
//		p("F N" + f1.getName());
//		p("Absol P" +f1.getAbsolutePath());
//		
//		p(f1.getParent());
//		
//		p(f1.exists() ? "exit" : "not exist");
//		p(f1.canWrite() ? "write" : "not write");
//		p(f1.canRead() ? "read" : "not read");
//		p(f1.isDirectory() ? "dir" : "not dir");
//		p(f1.isFile() ? "file" : "not file");
//		p(f1.lastModified()+" modified");
//		p("Size "+f1.length());
	}

}
