package hotel;

import java.util.Scanner;

public class Hotel {

	// Hotel fields
	String hotelName;
	final int MAXROOMS = 10, MAXCLIENTS = 10, MAXSERVICES = 10, MAXRESERVATIONS = 100, MAXORDERS = 100;
	static final int[] MONTHDAYS = {31,28,31,30,31,30,31,31,30,31,30,31};
	Room[] hotelRooms;
	int hotelRoomAmount;
	Client[] hotelClients;
	int hotelClientAmount;
	Service[] hotelServices;
	int hotelServiceAmount;
	Reservation[] hotelReservations;
	int hotelReservationAmount;
	Order[] hotelOrders;
	int hotelOrderAmount;
	int hotelLowSeasonStart, hotelLowSeasonEnd, hotelHighSeasonStart, hotelHighSeasonEnd;
	Scanner sc;
	
	Hotel (String name, boolean isEmpty) {
		sc = new Scanner(System.in);
		hotelName = name;
		hotelRooms = new Room[MAXROOMS];
		hotelRoomAmount = 0;
		hotelClients = new Client[MAXCLIENTS];
		hotelClientAmount = 0;
		hotelServices = new Service[MAXSERVICES];
		hotelServiceAmount = 0;
		hotelReservations = new Reservation[MAXRESERVATIONS];
		hotelReservationAmount = 0;
		hotelOrders = new Order[MAXORDERS];
		hotelOrderAmount = 0;
		hotelLowSeasonStart = 0; 
		hotelLowSeasonEnd = 0; 
		hotelHighSeasonStart = 0; 
		hotelHighSeasonEnd = 0;
		if (!isEmpty) {
			hotelFirstInit();
		}
	}
	
	// First initializing
	void hotelFirstInit () {
		hotelLowSeasonStart = Reservation.resToDate("01/11"); 
		hotelLowSeasonEnd = Reservation.resToDate("30/04"); 
		hotelHighSeasonStart = Reservation.resToDate("01/07");  
		hotelHighSeasonEnd = Reservation.resToDate("31/08");  
		hotelRoomAmount = 3;
		for (int i = 0; i < hotelRoomAmount; i++) {
			hotelRooms[i] = new Room((i+1)*111,i+1,(i+1)*10,(i+1)*20,(i+1)*30);
		}
		hotelClientAmount = 3;
		for (int i = 0; i < hotelClientAmount; i++) {
			String name = "";
			if (i == 0) name = "Black";
			if (i == 1) name = "White";
			if (i == 2) name = "Brown";
			hotelClients[i] = new Client("Mr. "+name,(i+1)*101+"-"+(i+1)*23+"-"+(i+1)*17+"",i*10);	
		}
		hotelServiceAmount = 3;
		hotelServices[0] = new Service("Dinner",20);
		hotelServices[1] = new Service("Transfer",10);
		hotelServices[2] = new Service("Bowling",15);
		hotelReservationAmount = 3;
		hotelReservations[0] = new Reservation(1,111,"Mr. Brown",Reservation.resToDate("10/07"),7,1,100,false);
		hotelReservations[1] = new Reservation(2,222,"Mr. Black",Reservation.resToDate("05/07"),10,2,200,true);
		hotelReservations[2] = new Reservation(3,333,"Mr. White",Reservation.resToDate("15/07"),5,3,150,false);
		hotelOrderAmount = 2;
		hotelOrders[0] = new Order(1,Reservation.resToDate("06/07"),"Dinner","Mr. Black");
		hotelOrders[1] = new Order(2,Reservation.resToDate("10/07"),"Transfer","Mr. Brown");
	}
	
	// Main menu
	boolean hotelMenu() {
		int choise;
		System.out.println("<<<Hotel menu>>>");
		System.out.print("Choose section (1 - Rooms, 2 - Clients, 3 - Services, 4 - Reservations, 5 - Orders, 0 - Exit): ");
		choise = makeIntChoise(0,5);
		if (choise == 1) while (roomMenu());
		if (choise == 2) while (clientMenu());
		if (choise == 3) while (serviceMenu());
		if (choise == 4) while (reservationMenu());
		if (choise == 5) while (orderMenu());
		if (choise == 0) {
			System.out.println("Thank you. Good bye...");
			return false;
		}
		return true;
	}
	
	// Room menu
	boolean roomMenu() {
		int choise, number, index;
		System.out.println("<<<Room menu>>>");
		System.out.print("Choose section (1 - Print rooms, 2 - New room, 3 - Edit room, 4 - Delete room, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			hotelPrintRooms();
		}
		if (choise == 2) {
			hotelEditRooms(true,0);
		}
		if (choise == 3) {
			System.out.print("Enter room number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchRooms(number);
			if (index >= 0) hotelEditRooms(false,index);
			else System.out.println("Wrong room number.");
		}
		if (choise == 4) {
			System.out.print("Enter room number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchRooms(number);
			if (index >= 0) {
				for (int i = index; i < hotelRoomAmount-1; i++)
					hotelRooms[i] = hotelRooms[i+1];
				hotelRooms[hotelRoomAmount--] = null;
				}
			else System.out.println("Wrong room number.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of rooms
	void hotelPrintRooms () {
		System.out.println("Amount of rooms - "+hotelRoomAmount+".");
		for (int i = 0; i < hotelRoomAmount; i++) {
			hotelRooms[i].printRoom();
		}
	}
	
	// Input new or edit room information
	void hotelEditRooms (boolean isNew, int index) {
		int number, capacity, lowPrice, mediumPrice, highPrice;
		boolean isUnique = false;
		if (!isNew)
			if ((index >= 0) && (index < hotelRoomAmount)) {
				System.out.println("Old information:");
				hotelRooms[index].printRoom();
			}
		else if (hotelRoomAmount == MAXROOMS) {
			System.out.println("Maximum rooms entered.");
			return;
		}
		System.out.println("Enter new information:");
		if (isNew) {
			do {
				System.out.print("Enter new room number (1 to 999): ");
				number = makeIntChoise(1,999);
				if (hotelSearchRooms(number) < 0) isUnique = true;
				else System.out.println("Number is already exist.");
			}
			while (!isUnique);
		}
		else number = hotelRooms[index].roomNumber;
		System.out.print("Enter new room capacity (1 to 4): ");
		capacity = makeIntChoise(1,4);
		System.out.print("Enter low price (0 to 999): ");
		lowPrice = makeIntChoise(0,999);
		System.out.print("Enter medium price (0 to 999): ");
		mediumPrice = makeIntChoise(0,999);
		System.out.print("Enter high price (0 to 999): ");
		highPrice = makeIntChoise(0,999);
		if (!isNew) {
			hotelRooms[index].setRoom(number,capacity,lowPrice,mediumPrice,highPrice);
		}
		else hotelRooms[hotelRoomAmount++] = new Room(number,capacity,lowPrice,mediumPrice,highPrice);
	}
	
	// Search room by number
	int hotelSearchRooms (int number) {
		for (int i = 0; i < hotelRoomAmount; i++)
			if (hotelRooms[i].roomNumber == number) 
				return i;
		return -1;
	}
	
	// Client menu
	boolean clientMenu() {
		int choise, index;
		String name;
		System.out.println("<<<Client menu>>>");
		System.out.print("Choose section (1 - Print clients, 2 - New client, 3 - Edit client, 4 - Delete client, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			hotelPrintClients();
		}
		if (choise == 2) {
			hotelEditClients(true,0);
		}
		if (choise == 3) {
			System.out.print("Enter client name: ");
			name = makeNameChoise();
			index = hotelSearchClients(name);
			if (index >= 0) hotelEditClients(false,index);
			else System.out.println("Wrong client name.");
		}
		if (choise == 4) {
			System.out.print("Enter client name: ");
			name = makeNameChoise();
			index = hotelSearchClients(name);
			if (index >= 0) {
				for (int i = index; i < hotelClientAmount-1; i++)
					hotelClients[i] = hotelClients[i+1];
				hotelRooms[hotelClientAmount--] = null;
				}
			else System.out.println("Wrong client name.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of clients
	void hotelPrintClients () {
		System.out.println("Amount of clients - "+hotelClientAmount+".");
		for (int i = 0; i < hotelClientAmount; i++) {
			hotelClients[i].printClient();
		}
	}
	
	// Input new or edit client information
	void hotelEditClients (boolean isNew, int index) {
		String name, phone;
		int discount;
		boolean isUnique = false;
		if (!isNew)
			if ((index >= 0) && (index < hotelClientAmount)) {
				System.out.println("Old information:");
				hotelClients[index].printClient();
			}
		else if (hotelClientAmount == MAXCLIENTS) {
			System.out.println("Maximum clients entered.");
			return;
		}
		System.out.println("Enter new information:");
		if (isNew) {
			do {
				System.out.print("Enter new name (only letters and .): ");
				name = makeNameChoise();
				if (hotelSearchClients(name) < 0) isUnique = true;
				else System.out.println("Name is already exist.");
			}
			while (!isUnique);
		}
		else name = hotelClients[index].clientName;
		System.out.print("Enter new phone number (XXX-XX-XX): ");
		phone = makePhoneChoise();
		System.out.print("Enter new discount (0 to 99): ");
		discount = makeIntChoise(0,99);
		if (!isNew) {
			hotelClients[index].setClient(name,phone,discount);
		}
		else hotelClients[hotelClientAmount++] = new Client(name,phone,discount);
	}
	
	// Search client by name
	int hotelSearchClients (String name) {
		for (int i = 0; i < hotelClientAmount; i++)
			if (hotelClients[i].clientName.equals(name)) 
				return i;
		return -1;
	}
	
	// Service menu
	boolean serviceMenu() {
		int choise, index;
		String name;
		System.out.println("<<<Services menu>>>");
		System.out.print("Choose section (1 - Print services, 2 - New service, 3 - Edit service, 4 - Delete service, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			hotelPrintServices();
		}
		if (choise == 2) {
			hotelEditServices(true,0);
		}
		if (choise == 3) {
			System.out.print("Enter service name: ");
			name = makeNameChoise();
			index = hotelSearchServices(name);
			if (index >= 0) hotelEditServices(false,index);
			else System.out.println("Wrong service name.");
		}
		if (choise == 4) {
			System.out.print("Enter service name: ");
			name = makeNameChoise();
			index = hotelSearchServices(name);
			if (index >= 0) {
				for (int i = index; i < hotelServiceAmount-1; i++)
					hotelServices[i] = hotelServices[i+1];
				hotelRooms[hotelServiceAmount--] = null;
				}
			else System.out.println("Wrong service name.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
		
	// Print list of services
	void hotelPrintServices () {
		System.out.println("Amount of services - "+hotelServiceAmount+".");
		for (int i = 0; i < hotelServiceAmount; i++) {
			hotelServices[i].printService();
		}
	}
	
	// Input new or edit service information
	void hotelEditServices (boolean isNew, int index) {
		String name;
		int price;
		boolean isUnique = false;
		if (!isNew)
			if ((index >= 0) && (index < hotelServiceAmount)) {
				System.out.println("Old information:");
				hotelServices[index].printService();
			}
		else if (hotelServiceAmount == MAXSERVICES) {
			System.out.println("Maximum services entered.");
			return;
		}
		System.out.println("Enter new information:");
		if (isNew) {
			do {
				System.out.print("Enter new name (only letters and .): ");
				name = makeNameChoise();
				if (hotelSearchServices(name) < 0) isUnique = true;
				else System.out.println("Name is already exist.");
			}
			while (!isUnique);
		}
		else name = hotelServices[index].serviceName;
		System.out.print("Enter new price (0 to 999): ");
		price = makeIntChoise(0,999);
		if (!isNew) {
			hotelServices[index].setService(name,price);
		}
		else hotelServices[hotelServiceAmount++] = new Service(name,price);
	}
	
	// Search service by name
	int hotelSearchServices (String name) {
		for (int i = 0; i < hotelServiceAmount; i++)
			if (hotelServices[i].serviceName.equals(name)) 
				return i;
		return -1;
	}
	
	// Reservation menu
	boolean reservationMenu() {
		int choise, index, number, date, nights;
		String name;
		System.out.println("<<<Reservations menu>>>");
		System.out.print("Choose section (1 - Print reservations, 2 - ViewByRoom, 3 - ViewByClient, 4 - ViewByDate, " + 
				"5 - FreeOnDate, 6 - New Reservation, 7 - Edit Reservation, 8 - Delete Reservation, 9 - Change Accomodation, 0 - Exit): ");
		choise = makeIntChoise(0,9);
		if (choise == 1) {
			System.out.println("List of all reservations:");
			hotelPrintReservations(0,"",0);
		}
		if (choise == 2) {
			System.out.print("Enter room number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchRooms(number);
			if (index >= 0) {
				System.out.println("List of reservations for room number "+number+":");
				hotelPrintReservations(number,"",0);
			}
			else System.out.println("Wrong room number.");
		}
		if (choise == 3) {
			System.out.print("Enter client name: ");
			name = makeNameChoise();
			index = hotelSearchClients(name);
			if (index >= 0) {
				System.out.println("List of reservations for client "+name+":");
				hotelPrintReservations(0,name,0);
			}
			else System.out.println("Wrong client name.");			
		}
		if (choise == 4) {
			System.out.print("Enter the date (DD/MM): ");
			name = makeDateChoise();
			date = Reservation.resToDate(name);
			if (date > 0) {
				System.out.println("List of reservations on date "+Reservation.resToString(date)+":");
				hotelPrintReservations(0,"",date);
			}
			else System.out.println("Wrong date.");
		}
		if (choise == 5) {
			System.out.print("Enter the date (DD/MM): ");
			name = makeDateChoise();
			date = Reservation.resToDate(name);
			if (date > 0) {
				System.out.print("Enter number of nights (1 to 365): ");
				nights = makeIntChoise(1,365);
				System.out.println("List of rooms free on date "+Reservation.resToString(date)+" for "+nights+" night(s):");
				hotelPrintFreeRooms(date,nights,0);
			}
			else System.out.println("Wrong date.");
		}
		if (choise == 6) {
			hotelEditReservations(true,0);
		}
		if (choise == 7) {
			System.out.print("Enter reservation number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchReservations(number);
			if (index >= 0) hotelEditReservations(false,index);
			else System.out.println("Wrong reservation number.");
		}
		if (choise == 8) {
			System.out.print("Enter reservation number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchReservations(number);
			if (index >= 0) {
				for (int i = index; i < hotelReservationAmount-1; i++)
					hotelReservations[i] = hotelReservations[i+1];
				hotelReservations[hotelReservationAmount--] = null;
				}
			else System.out.println("Wrong reservation number.");
		}
		if (choise == 9) {
			System.out.print("Enter reservation number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchReservations(number);
			if (index >= 0) {
				hotelReservations[index].isAccomodation = !(hotelReservations[index].isAccomodation);
				hotelReservations[index].printReservation();
			}
			else System.out.println("Wrong reservation number.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of all reservation or by room number, client name or on concrete date
	void hotelPrintReservations(int number, String name, int date) {
		for (int i = 0; i < hotelReservationAmount; i++) {
			if (((number == 0) || ((number > 0) && (hotelReservations[i].roomNumber == number))) &&
			((name.equals("")) || (!name.equals("")) && (hotelReservations[i].clientName.equals(name))) &&
			((date == 0) || ((date > 0) && (hotelReservations[i].resDate <= date) && 
			(((hotelReservations[i].resDate+hotelReservations[i].resNights) >= date)))))
				hotelReservations[i].printReservation();
		}
	}
	
	// Print list of free rooms on date for nights and persons
	int hotelPrintFreeRooms (int date, int nights, int persons) {
		int count = 0;
		for (int i = 0; i < hotelRoomAmount; i++)
			if ((hotelIsRoomFree(hotelRooms[i].roomNumber,date,nights,persons)) && (hotelRooms[i].roomCapacity >= persons)) {
				count++;
				hotelRooms[i].printRoom();
			}
		return count;
	}
	
	// Definition of free room  on date for nights and persons
	boolean hotelIsRoomFree (int number, int date, int nights, int persons) {
		for (int i = 0; i < hotelReservationAmount; i++)
			if (hotelReservations[i].roomNumber == number)
				for (int j = 0; j < nights; j++)
					if ((hotelReservations[i].resDate <= (date+j)) && ((hotelReservations[i].resDate+hotelReservations[i].resNights) >= (date+j)) && (hotelRooms[i].roomCapacity >= persons))
						return false;
		return true;
	}
	
	// Calculating of cost of reservation
	int hotelCalculate (int number, String name, int date, int nights) {
		int value = -1, roomindex = hotelSearchRooms(number), clientindex = hotelSearchClients(name);
		if ((roomindex >= 0) && (clientindex >= 0)) {
			value = 0;
			for (int i = date; i < (date+nights); i++) {
				if (((hotelLowSeasonStart <= hotelLowSeasonEnd) && (i >= hotelLowSeasonStart) && (i <= hotelLowSeasonEnd)) ||
					((hotelLowSeasonStart > hotelLowSeasonEnd) && ((i >= hotelLowSeasonStart) || (i <= hotelLowSeasonEnd))))
					value+=hotelRooms[roomindex].roomLowPrice;
				else if (((hotelHighSeasonStart <= hotelHighSeasonEnd) && (i >= hotelHighSeasonStart) && (i <= hotelHighSeasonEnd)) ||
						((hotelHighSeasonStart > hotelHighSeasonEnd) && ((i >= hotelHighSeasonStart) || (i <= hotelHighSeasonEnd))))
							value+=hotelRooms[roomindex].roomHighPrice;
					else value+=hotelRooms[roomindex].roomMediumPrice;
			}
			value = value * (1 - (hotelClients[clientindex].clientDiscount / 100));
		}
		return value;
	}
	
	// Input new or edit reservation information
	void hotelEditReservations (boolean isNew, int index) {
		int resnumber = 0, number = 0, date, nights = 0, persons = 0, value = 0, count, find;
		String name;
		boolean isUnique = false, isRight = false, isExit = false;
		if (!isNew)
			if ((index >= 0) && (index < hotelReservationAmount)) {
				System.out.println("Old information:");
				hotelReservations[index].printReservation();
				resnumber = hotelReservations[index].resNumber;
				for (int i = index; i < hotelReservationAmount-1; i++)
					hotelReservations[i] = hotelReservations[i+1];
				hotelReservations[hotelReservationAmount--] = null;
			}
		else if (hotelReservationAmount == MAXRESERVATIONS) {
			System.out.println("Maximum reservationss entered.");
			return;
		}
		System.out.println("Enter new information:");
		if (isNew) {
			do {
				System.out.print("Enter new number (1 to 999): ");
				resnumber = makeIntChoise(1,999);
				if (hotelSearchReservations(resnumber) < 0) isUnique = true;
				else System.out.println("Number is already exist.");
			}
			while (!isUnique);
		}
		do {
			System.out.print("Enter the date (DD/MM): ");
			name = makeDateChoise();
			date = Reservation.resToDate(name);
			if (date > 0) {
				System.out.print("Enter number of nights (1 to 365): ");
				nights = makeIntChoise(1,365);
				System.out.print("Enter number of persons (1 to 9): ");
				persons = makeIntChoise(1,9);
				System.out.println("List of rooms free on date "+Reservation.resToString(date)+" for "+nights+" night(s) + for "+persons+" person(s):");
				count = hotelPrintFreeRooms(date,nights,persons);
				if (count > 0) {
					do {
						System.out.print("Enter room number (1 to 999): ");
						number = makeIntChoise(1,999);
						count = hotelSearchRooms(number);
						if (count >= 0) {
							if (hotelIsRoomFree(number,date,nights,persons)) {
								do {
									System.out.print("Enter client name: ");
									name = makeNameChoise();
									find = hotelSearchClients(name);
									if (find >= 0) {
										value = hotelCalculate(number,name,date,nights);
										isRight = true;
										isExit = true;
									}
									else {
										isExit = !isRepeat("New client? (y - to new): ");
										if (!isExit) {
											hotelClients[hotelClientAmount++] = new Client(name,"",0);
											hotelEditClients(false,hotelClientAmount-1);
											value = hotelCalculate(number,name,date,nights);
											isRight = true;
											isExit = true;
										}
										else {
											System.out.println("Client is not exist.");
											isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
										}
									}
								}
								while (!isExit);
							}
							else {
								System.out.println("Room is not available.");
								isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
							}
						}
						else {
							System.out.println("Wrong room number.");
							isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
						}
				
					}
					while (!isExit);
				}
				else {
					System.out.println("No rooms are available.");
					isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
				}
			}
			else {
				System.out.println("Wrong date.");
				isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
			}
		}
		while (!isExit);
		if (isRight)
			hotelReservations[hotelReservationAmount++] = new Reservation(resnumber,number,name,date,nights,persons,value,false);
	}
	
	// Search reservation by number
	int hotelSearchReservations (int number) {
		for (int i = 0; i < hotelReservationAmount; i++)
			if (hotelReservations[i].resNumber == number) 
				return i;
		return -1;
	}
	
	// Order menu
	boolean orderMenu() {
		int choise, index, number, date;
		String name;
		System.out.println("<<<Orders menu>>>");
		System.out.print("Choose section (1 - Print orders, 2 - ViewByService, 3 - ViewByClient, 4 - ViewByDate, " + 
				"5 - New Order, 6 - Edit Order, 7 - Delete Order, 0 - Exit): ");
		choise = makeIntChoise(0,7);
		if (choise == 1) {
			System.out.println("List of all orders:");
			hotelPrintOrders("","",0);
		}
		if (choise == 2) {
			System.out.print("Enter service name: ");
			name = makeNameChoise();
			index = hotelSearchServices(name);
			if (index >= 0) {
				System.out.println("List of orders for service "+name+":");
				hotelPrintOrders(name,"",0);
			}
			else System.out.println("Wrong service name.");
		}
		if (choise == 3) {
			System.out.print("Enter client name: ");
			name = makeNameChoise();
			index = hotelSearchClients(name);
			if (index >= 0) {
				System.out.println("List of orders for client "+name+":");
				hotelPrintOrders("",name,0);
			}
			else System.out.println("Wrong client name.");			
		}
		if (choise == 4) {
			System.out.print("Enter the date (DD/MM): ");
			name = makeDateChoise();
			date = Reservation.resToDate(name);
			if (date > 0) {
				System.out.println("List of orders on date "+Reservation.resToString(date)+":");
				hotelPrintOrders("","",date);
			}
			else System.out.println("Wrong date.");
		}
		if (choise == 5) {
			hotelEditOrders(true,0);
		}
		if (choise == 6) {
			System.out.print("Enter order number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchOrders(number);
			if (index >= 0) hotelEditOrders(false,index);
			else System.out.println("Wrong order number.");
		}
		if (choise == 7) {
			System.out.print("Enter order number (1 to 999): ");
			number = makeIntChoise(1,999);
			index = hotelSearchOrders(number);
			if (index >= 0) {
				for (int i = index; i < hotelOrderAmount-1; i++)
					hotelOrders[i] = hotelOrders[i+1];
				hotelOrders[hotelOrderAmount--] = null;
				}
			else System.out.println("Wrong reservation number.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of all orders or by service name, client name or on concrete date
	void hotelPrintOrders(String service, String client, int date) {
		for (int i = 0; i < hotelOrderAmount; i++) {
			if (((service.equals("")) || ((!service.equals("")) && (hotelOrders[i].serviceName.equals(service)))) &&
			((client.equals("")) || (!client.equals("")) && (hotelOrders[i].clientName.equals(client))) &&
			((date == 0) || ((date > 0) && (hotelOrders[i].orderDate == date)))) {
				hotelOrders[i].printOrder();
				for (int j = 0; j < hotelServiceAmount; j++)
					if (hotelOrders[i].serviceName.equals(hotelServices[j].serviceName))
						System.out.println("Price of service "+hotelOrders[i].serviceName+" is "+hotelServices[j].servicePrice+"$.");
				for (int j = 0; j < hotelReservationAmount; j++)
					if (hotelOrders[i].clientName.equals(hotelReservations[j].clientName))
						if ((hotelOrders[i].orderDate >= hotelReservations[j].resDate) && (hotelOrders[i].orderDate <= hotelReservations[j].resDate+hotelReservations[j].resNights))
							if (hotelReservations[j].isAccomodation)
								System.out.println("Client "+hotelOrders[i].clientName+" lives in room "+hotelReservations[j].roomNumber+".");
								
			}
		}
	}
	
	// Input new or edit order information
	void hotelEditOrders (boolean isNew, int index) {
		String name, service = "", client = "";
		int number, date, find;
		boolean isUnique = false, isRight = false, isExit = false;
		if (!isNew)
			if ((index >= 0) && (index < hotelOrderAmount)) {
				System.out.println("Old information:");
				hotelOrders[index].printOrder();
			}
		else if (hotelOrderAmount == MAXORDERS) {
			System.out.println("Maximum orders entered.");
			return;
		}
		System.out.println("Enter new information:");
		if (isNew) {
			do {
				System.out.print("Enter new number (1 to 999): ");
				number = makeIntChoise(1,999);
				if (hotelSearchOrders(number) < 0) isUnique = true;
				else System.out.println("Number is already exist.");
			}
			while (!isUnique);
		}
		else number = hotelOrders[index].orderNumber;
		do {
			System.out.print("Enter the date (DD/MM): ");
			name = makeDateChoise();
			date = Reservation.resToDate(name);
			if (date > 0) {
				System.out.print("Enter service name: ");
				service = makeNameChoise();
				find = hotelSearchServices(service);
				if (find >= 0) {
					System.out.print("Enter client name: ");
					client = makeNameChoise();
					find = hotelSearchClients(client);
					if (find >= 0) {
						isRight = true;
						isExit = true;
					}
					else {
						System.out.println("Wrong client name.");
						isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
					}
				}
				else {
					System.out.println("Wrong service name.");
					isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
				}
			}
			else {
				System.out.println("Wrong date.");
				isExit = !isRepeat("Do you want to repeat input? (y - to repeat): ");
			}
		}
		while (!isExit);
		if (isRight)
			if (!isNew) {
				hotelOrders[index].setOrder(number,date,service,client);
			}
			else hotelOrders[hotelOrderAmount++] = new Order(number,date,service,client);
	}
	
	
	// Search orders by number
	int hotelSearchOrders (int number) {
		for (int i = 0; i < hotelOrderAmount; i++)
			if (hotelOrders[i].orderNumber == number) 
				return i;
		return -1;
	}
	
	
	// Simple methods of input confirmations, integer values in certain range,
	// names, phones and dates in DD/MM format
	boolean isRepeat(String question) {
		String answer;
		System.out.print(question);
		answer = sc.nextLine();
		answer = answer.toLowerCase().substring(0, 1);
		if (answer.equals("y")) return true;
		else return false;
	}
	
	int makeIntChoise (int from, int to) {
		String enter;
		int choise = -1; 
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			try {
				choise = Integer.parseInt(enter);
				if ((choise >= from) && (choise <= to))
					isMake = true;
				else System.out.println("Wrong value. Enter again.");
			}
			catch (NumberFormatException e) {
				System.out.println("Not a value. Enter again.");
			}
		}
		while (!isMake);
		return choise;
	}
	
	
	String makeNameChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.length() > 0) {
				isMake = true;
				for (int i = 0; i < enter.length(); i++) {
					if (((enter.toUpperCase().charAt(i) < 'A') || (enter.toUpperCase().charAt(i) > 'Z')) &&
							(enter.toUpperCase().charAt(i) != '.') && (enter.toUpperCase().charAt(i) != ' ')) {
						System.out.println("Wrong value. Enter again.");
						isMake = false;
					}
				if (!isMake) break;
				}
			}
			else System.out.println("Wrong value. Enter again.");
		}
		while (!isMake);
		return enter;
	}
	
	String makePhoneChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.length() == 9) {
				isMake = true;
				for (int i = 0; i < enter.length(); i++) {
					if ((i == 3) || (i == 6))
						if (enter.toUpperCase().charAt(i) != '-') {
						System.out.println("Wrong value. Enter again.");
						isMake = false;
						}
						else;
					else if ((enter.toUpperCase().charAt(i) < '0') || (enter.toUpperCase().charAt(i) > '9')) {
						System.out.println("Wrong value. Enter again.");
						isMake = false;
					}
					if (!isMake) break;	
				}
			}
			else System.out.println("Wrong value. Enter again.");
		}
		while (!isMake);
		return enter;
	}
	
	String makeDateChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if ((enter.length() > 0) && (Reservation.resToDate(enter) > 0))
				isMake = true;
			else System.out.println("Wrong value. Enter again.");
		}
		while (!isMake);
		return enter;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hotel myHotel = new Hotel("Robinson", false);
		System.out.println("Welcome to the hotel "+myHotel.hotelName+"!\n");
		while (myHotel.hotelMenu());
		myHotel.sc.close();
	}

}
