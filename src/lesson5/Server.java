package lesson5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Server {

	Scanner sc = new  Scanner(System.in);
	int[] arr;
	int serverPort = 6666;
	int dport = 6667;
	int cport = 6668;

	ServerSocket ss;
	Socket socket;
	InputStream sin;
	OutputStream sout;
	DataInputStream in;
	DataOutputStream out;
	InetAddress daddress;
	DatagramSocket dsocket;
	DatagramPacket dpacket;

	Server () {
		try {
			ss = new ServerSocket(serverPort);
			System.out.println("Waiting for a client...");
			Socket socket = ss.accept();
			System.out.println("Client go to work...");
			sin  = socket.getInputStream();
			sout  = socket.getOutputStream();
			in = new DataInputStream(sin);
			out = new DataOutputStream(sout);
			daddress = InetAddress.getLocalHost();
			dsocket = new DatagramSocket(dport);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void getArray () {
		int size;
		try {
			size = in.readInt();	
			arr = new int[size];
			System.out.println("Get array size - "+size);
			System.out.println("Get array:");
			for (int i = 0; i < arr.length; i++) {
				arr[i] = in.readInt();
				System.out.print(arr[i]+" ");
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	int calcArray(int[] arr) {
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			result+=arr[i];
		}
		return (result / arr.length);
	}

	void sendResult() {
		int result = calcArray(arr);
		System.out.println("Sending result...");
		try {
			out.writeInt(result);
			out.flush();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void dgetArray () {
		byte[] barr;
		barr = new byte[1];
		try {
			dpacket = new DatagramPacket(barr,barr.length,daddress,dport);
			dsocket.setSoTimeout(5000);
			dsocket.receive(dpacket);
		} catch (SocketTimeoutException e) {
			System.out.println("Time is gone.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Get array size - "+barr[0]);
		arr = new int[barr[0]];
		try {
			barr = new byte[barr[0]];
			dpacket = new DatagramPacket(barr,barr.length,daddress,dport);
			dsocket.setSoTimeout(5000);
			dsocket.receive(dpacket);
		} catch (SocketTimeoutException e) {
			System.out.println("Time is gone.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Get array:");
		for (int i = 0; i < barr.length; i++) {
			arr[i] = barr[i];
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	void dsendResult() {
		byte[] barr = new byte[1];
		barr[0] = (byte) calcArray(arr);
		System.out.println("Sending result...");
		try {
			dpacket = new DatagramPacket(barr,barr.length,daddress,cport);
			dsocket.send(dpacket);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.getArray();
		server.sendResult();
		System.out.println("Now with UDP...");
		server.dgetArray();
		server.dsendResult();
	}

}
