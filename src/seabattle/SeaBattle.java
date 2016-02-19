package seabattle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class SeaBattle {

	// My field and opponent field as a result
	private SeaField myField, oppField;
	// Interrupting flag
	private boolean isInterrupt = false;
	// Server and client
	private int serverPort = 6666;
	private boolean isServer; 
	private ServerSocket ss;
	private Socket socket;
	private InputStream sin;
	private OutputStream sout;
	private DataInputStream in;
	private DataOutputStream out;
	private String address = "127.0.0.1";
	private InetAddress ip;

	SeaBattle() {
		myField = new SeaField();
		oppField = new SeaField();
	}

	// Initializing Server
	public void setServer() {
		try {
			this.isServer = true;
			this.ss = new ServerSocket(serverPort);
			System.out.println("Waiting for a client...");
			this.socket = ss.accept();
			System.out.println("Client is connected...");
			this.sin  = socket.getInputStream();
			this.sout  = socket.getOutputStream();
			this.in = new DataInputStream(sin);
			this.out = new DataOutputStream(sout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Initializing client
	public void setClient() {
		boolean isSet = false;
		System.out.println("Waiting for server starting...");
		do {
			try {
				this.isServer = false;
				this.ip = InetAddress.getByName(address);
				this.socket = new Socket(ip, serverPort);
				System.out.println("Client is OK...");
				this.sin  = socket.getInputStream();
				this.sout  = socket.getOutputStream();
				this.in = new DataInputStream(sin);
				this.out = new DataOutputStream(sout);
				isSet = true;

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		while (!isSet);
	}

	// Sending shoot to opponent and receive result
	public int sendShoot(int row, int column) {
		int shoot = 0;
		try {
			this.out.writeInt(row);
			this.out.flush();
			this.out.writeInt(column);
			this.out.flush();
			shoot = this.in.readInt();
			oppField.getShoot(shoot,row,column);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.isInterrupt = true;
			//e.printStackTrace();
		}
		return shoot;
	}

	// Receive opponent shoot and sending result
	public boolean getShoot() {
		boolean isRepeat, isMiss = true;
		int row, column, shoot;
		isRepeat = true;
		do {
			try {
				System.out.println("Waiting for opponent shoot...");
				row = this.in.readInt();
				column = this.in.readInt();
				System.out.println("Opponent shoot is - "+(row+1)+", "+(column+1)+".");
				shoot = getMyField().sendShoot(row,column);
				getMyField().printField();
				this.out.writeInt(shoot);
				this.out.flush();
				if (shoot == 0) {
					isMiss = true;
					isRepeat = false;
				}
				else {
					isMiss = false;
					isRepeat = false;
					for (int i = 0; i < myField.getNumShips(); i++)
						if (myField.getInjureShips()[i] > 0) {
							isRepeat = true;
							break;
						}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.isInterrupt = true;
				isRepeat = false;
				//e.printStackTrace();
			}
		}
		while (isRepeat);
		return isMiss;
	}
	
	// Series of my shoots until miss or interrupt
	public boolean shootField () {
		boolean isRandom, isRepeat, isMiss = true;
		int row, column, shoot;
		Random rand = new Random();
		isRepeat = true;
		do {
			System.out.println("Now is your turn.");
			isRandom = myField.isRepeat("Do you want shoot random? (y - to random): ");
			if (!isRandom) {
				System.out.print("Set shoot row: ");
				row = myField.makeIntChoise(1,getMyField().getSize())-1;
				System.out.print("Set shoot column: ");
				column = myField.makeIntChoise(1,getMyField().getSize())-1;
			}
			else {
				row = rand.nextInt(getMyField().getSize());
				column = rand.nextInt(getMyField().getSize());
			}
			if (getOppField().getField()[row][column] == 0) {
				getOppField().setShootcount(getOppField().getShootcount()+1);
				shoot = sendShoot(row, column);
				if (isInterrupt) {
					isMiss = true;
					return (isMiss);
				}
				if (shoot == 0) {
					isMiss = true;
					isRepeat = false;
				}
				else {
					isMiss = false;
					isRepeat = false;
					for (int i = 0; i < getOppField().getNumShips(); i++)
						if (getOppField().getInjureShips()[i] > 0) {
							isRepeat = true;
							break;
						}
				}
			}
			else if (!isRandom)
				System.out.println("Wrong coordinates.");
		}
		while (isRepeat);
		return (isMiss);
	}
	
	// Print shoots statistic
	public void printShoots() {
		System.out.println("Your shoots: "+getOppField().getShootcount()+", hits: "+getOppField().getHitcount()+".");
		System.out.println("Opponent shoots: "+getMyField().getShootcount()+", hits: "+getMyField().getHitcount()+".");	
	}
	
	// Getters and setters for fields only
	public SeaField getMyField() {
		return myField;
	}
	
	public void setMyField(SeaField myField) {
		this.myField = myField;
	}
	
	public SeaField getOppField() {
		return oppField;
	}
	public void setOppField(SeaField oppField) {
		this.oppField = oppField;
	}

	public static void main(String[] args) {
		boolean isServ, isRepeat = true, isRandom = false, isMiss = true;
		int size = 0;
		SeaBattle myBattle = new SeaBattle();
		// Input size of field and fill it
		do {
			System.out.print("Enter size of field: ");
			if (SeaField.sc.hasNextInt())
				size = SeaField.sc.nextInt();
			else {
				SeaField.sc.nextLine();
				System.out.println("Wrong size");
				isRepeat = myBattle.myField.isRepeat("Do you want to try again? (y - to repeat): ");
			}
			if (size > 0) {
				SeaField.sc.nextLine();
				myBattle.getMyField().setSize(size);
				//myField.printField();
				isRandom = myBattle.getMyField().isRepeat("Do you want fill field random? (y - to random): ");
				myBattle.getMyField().fillField(isRandom);
				myBattle.getOppField().setSize(size);
				isRepeat = false;
			}
		}
		while (isRepeat);
		// Define if you are server or client
		isServ = myBattle.getMyField().isRepeat("Do you want to run server or client (run the server first)? (y - to server): ");
		if (isServ)
			myBattle.setServer();
		else
			myBattle.setClient();
		System.out.println("Start shooting...");
		// Server is shooting first
		if (myBattle.isServer) {
			isRepeat = true;
			do {
				isMiss = myBattle.shootField();
				if (myBattle.isInterrupt) {
					System.out.println("The game is stopped.");
					isRepeat = false;
				}
				else if (!isMiss) {
					System.out.println("Congratulations. You're win!");
					myBattle.printShoots();
					isRepeat = false;
				}
				else {
					isMiss = myBattle.getShoot();
					if (myBattle.isInterrupt) {
						System.out.println("The game is stopped.");
						isRepeat = false;
					}
					else if (!isMiss) {
						System.out.println("Sory. You're loose!");
						myBattle.printShoots();
						isRepeat = false;
					}					
				}
			}
			while (isRepeat);
		}
		// Client is shooting second
		else {
			isRepeat = true;
			do {
				isMiss = myBattle.getShoot();
				if (myBattle.isInterrupt) {
					System.out.println("The game is stopped.");
					isRepeat = false;
				}
				else if (!isMiss) {
					System.out.println("Sorry. You're loose!");
					myBattle.printShoots();
					isRepeat = false;
				}
				else {
					isMiss = myBattle.shootField();
					if (myBattle.isInterrupt) {
						System.out.println("The game is stopped.");
						isRepeat = false;
					}
					else if (!isMiss) {
						System.out.println("Congratulations. You're win!");
						myBattle.printShoots();
						isRepeat = false;
					}

				}
			}
			while (isRepeat);
		}
	}
}
