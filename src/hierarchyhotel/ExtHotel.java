package hierarchyhotel;

public class ExtHotel extends HHotel {
	
	// Main hotel fields
	final int MAXROOMS = 10, MAXCLIENTS = 10, MAXSERVICES = 10, MAXRESERVATIONS = 100, MAXORDERS = 100;
	private HRoom[] hotelRooms;
	private int hotelRoomAmount;
	private HClient[] hotelClients;
	private int hotelClientAmount;
	private HService[] hotelServices;
	private int hotelServiceAmount;
	private HReservation[] hotelReservations;
	private int hotelReservationAmount;
	private HOrder[] hotelOrders;
	private int hotelOrderAmount;
	private int hotelLowSeasonStart, hotelLowSeasonEnd, hotelHighSeasonStart, hotelHighSeasonEnd;

	public ExtHotel() {
		// TODO Auto-generated constructor stub
		hotelRooms = new HRoom[MAXROOMS];
		hotelRoomAmount = 0;
		hotelClients = new HClient[MAXCLIENTS];
		hotelClientAmount = 0;
		hotelServices = new HService[MAXSERVICES];
		hotelServiceAmount = 0;
		hotelReservations = new HReservation[MAXRESERVATIONS];
		hotelReservationAmount = 0;
		hotelOrders = new HOrder[MAXORDERS];
		hotelOrderAmount = 0;
		hotelLowSeasonStart = 0; 
		hotelLowSeasonEnd = 0; 
		hotelHighSeasonStart = 0; 
		hotelHighSeasonEnd = 0;
	}

	public ExtHotel(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		hotelRooms = new HRoom[MAXROOMS];
		hotelRoomAmount = 0;
		hotelClients = new HClient[MAXCLIENTS];
		hotelClientAmount = 0;
		hotelServices = new HService[MAXSERVICES];
		hotelServiceAmount = 0;
		hotelReservations = new HReservation[MAXRESERVATIONS];
		hotelReservationAmount = 0;
		hotelOrders = new HOrder[MAXORDERS];
		hotelOrderAmount = 0;
		hotelLowSeasonStart = 0; 
		hotelLowSeasonEnd = 0; 
		hotelHighSeasonStart = 0; 
		hotelHighSeasonEnd = 0;
	}

	public ExtHotel(String name, boolean isEmpty) {
		this(name);
		if (!isEmpty) {
			hotelFirstInit();
		}
	}
	
	// First initializing
	void hotelFirstInit () {
		hotelLowSeasonStart = toDate("01/11"); 
		hotelLowSeasonEnd = toDate("30/04"); 
		hotelHighSeasonStart = toDate("01/07");  
		hotelHighSeasonEnd = toDate("31/08");  
		hotelRoomAmount = 3;
		for (int i = 0; i < hotelRoomAmount; i++) {
			hotelRooms[i] = new HRoom((i+1)*111,i+1,(i+1)*10,(i+1)*20,(i+1)*30,"","");
		}
		hotelClientAmount = 3;
		for (int i = 0; i < hotelClientAmount; i++) {
			String name = "";
			if (i == 0) name = "Black";
			if (i == 1) name = "White";
			if (i == 2) name = "Brown";
			hotelClients[i] = new HClient("Mr. "+name,(i+1)*101+"-"+(i+1)*23+"-"+(i+1)*17+"",i*10);	
		}
		hotelServiceAmount = 3;
		hotelServices[0] = new HService("Dinner",20);
		hotelServices[1] = new HService("Transfer",10);
		hotelServices[2] = new HService("Bowling",15);
		hotelReservationAmount = 3;
		hotelReservations[0] = new HReservation(1,111,"Mr. Brown",toDate("10/07"),7,1,100,false,"");
		hotelReservations[1] = new HReservation(2,222,"Mr. Black",toDate("05/07"),10,2,200,true,"");
		hotelReservations[2] = new HReservation(3,333,"Mr. White",toDate("15/07"),5,3,150,false,"");
		hotelOrderAmount = 2;
		hotelOrders[0] = new HOrder(1,toDate("06/07"),"Dinner","Mr. Black",20);
		hotelOrders[1] = new HOrder(2,toDate("10/07"),"Transfer","Mr. Brown",15);
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
			hotelRooms[i].print();
		}
	}
	
	// Input new or edit room information
	void hotelEditRooms (boolean isNew, int index) {
		int number, capacity, lowPrice, mediumPrice, highPrice;
		boolean isUnique = false;
		if (!isNew)
			if ((index >= 0) && (index < hotelRoomAmount)) {
				System.out.println("Old information:");
				hotelRooms[index].print();
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
		else number = hotelRooms[index].getNumber();
		System.out.print("Enter new room capacity (1 to 4): ");
		capacity = makeIntChoise(1,4);
		System.out.print("Enter low price (0 to 999): ");
		lowPrice = makeIntChoise(0,999);
		System.out.print("Enter medium price (0 to 999): ");
		mediumPrice = makeIntChoise(0,999);
		System.out.print("Enter high price (0 to 999): ");
		highPrice = makeIntChoise(0,999);
		if (!isNew) {
			hotelRooms[index].set(number,capacity,lowPrice,mediumPrice,highPrice,"","");
		}
		else hotelRooms[hotelRoomAmount++] = new HRoom(number,capacity,lowPrice,mediumPrice,highPrice,"","");
	}
	
	int hotelSearchRooms (int number) {
		for (int i = 0; i < hotelRoomAmount; i++)
			if (hotelRooms[i].getNumber() == number) 
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
				hotelClients[hotelClientAmount--] = null;
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
			hotelClients[i].print();
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
				hotelClients[index].print();
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
		else name = hotelClients[index].getName();
		System.out.print("Enter new phone number (XXX-XX-XX): ");
		phone = makePhoneChoise();
		System.out.print("Enter new discount (0 to 99): ");
		discount = makeIntChoise(0,99);
		if (!isNew) {
			hotelClients[index].set(name,phone,discount);
		}
		else hotelClients[hotelClientAmount++] = new HClient(name,phone,discount);
	}
	
	// Search client by name
	int hotelSearchClients (String name) {
		for (int i = 0; i < hotelClientAmount; i++)
			if (hotelClients[i].getName().equals(name)) 
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
				hotelServices[hotelServiceAmount--] = null;
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
			hotelServices[i].print();
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
				hotelServices[index].print();
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
		else name = hotelServices[index].getName();
		System.out.print("Enter new price (0 to 999): ");
		price = makeIntChoise(0,999);
		if (!isNew) {
			hotelServices[index].set(name,price);
		}
		else hotelServices[hotelServiceAmount++] = new HService(name,price);
	}
	
	// Search service by name
	int hotelSearchServices (String name) {
		for (int i = 0; i < hotelServiceAmount; i++)
			if (hotelServices[i].getName().equals(name)) 
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
			date = toDate(name);
			if (date > 0) {
				System.out.println("List of reservations on date "+toString(date)+":");
				hotelPrintReservations(0,"",date);
			}
			else System.out.println("Wrong date.");
		}
		if (choise == 5) {
			System.out.print("Enter the date (DD/MM): ");
			name = makeDateChoise();
			date = toDate(name);
			if (date > 0) {
				System.out.print("Enter number of nights (1 to 365): ");
				nights = makeIntChoise(1,365);
				System.out.println("List of rooms free on date "+toString(date)+" for "+nights+" night(s):");
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
				hotelReservations[index].setAccept(!(hotelReservations[index].getAccept()));
				hotelReservations[index].print();
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
			if (((number == 0) || ((number > 0) && (hotelReservations[i].getRoom() == number))) &&
			((name.equals("")) || (!name.equals("")) && (hotelReservations[i].getName().equals(name))) &&
			((date == 0) || ((date > 0) && (hotelReservations[i].getDate() <= date) && 
			(((hotelReservations[i].getDate()+hotelReservations[i].getLowPrice()) >= date)))))
				hotelReservations[i].print();
		}
	}
	
	// Print list of free rooms on date for nights and persons
	int hotelPrintFreeRooms (int date, int nights, int persons) {
		int count = 0;
		for (int i = 0; i < hotelRoomAmount; i++)
			if ((hotelIsRoomFree(hotelRooms[i].getNumber(),date,nights,persons)) && (hotelRooms[i].getDate() >= persons)) {
				count++;
				hotelRooms[i].print();
			}
		return count;
	}
	
	// Definition of free room  on date for nights and persons
	boolean hotelIsRoomFree (int number, int date, int nights, int persons) {
		for (int i = 0; i < hotelReservationAmount; i++)
			if (hotelReservations[i].getRoom() == number)
				for (int j = 0; j < nights; j++)
					if ((hotelReservations[i].getDate() <= (date+j)) && ((hotelReservations[i].getDate()+hotelReservations[i].getLowPrice()) >= (date+j)) && (hotelRooms[i].getDate() >= persons))
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
					value+=hotelRooms[roomindex].getLowPrice();
				else if (((hotelHighSeasonStart <= hotelHighSeasonEnd) && (i >= hotelHighSeasonStart) && (i <= hotelHighSeasonEnd)) ||
						((hotelHighSeasonStart > hotelHighSeasonEnd) && ((i >= hotelHighSeasonStart) || (i <= hotelHighSeasonEnd))))
							value+=hotelRooms[roomindex].getHighPrice();
					else value+=hotelRooms[roomindex].getPrice();
			}
			value = value * (1 - (hotelClients[clientindex].getPrice() / 100));
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
				hotelReservations[index].print();
				resnumber = hotelReservations[index].getNumber();
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
			date = toDate(name);
			if (date > 0) {
				System.out.print("Enter number of nights (1 to 365): ");
				nights = makeIntChoise(1,365);
				System.out.print("Enter number of persons (1 to 9): ");
				persons = makeIntChoise(1,9);
				System.out.println("List of rooms free on date "+toString(date)+" for "+nights+" night(s) + for "+persons+" person(s):");
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
											hotelClients[hotelClientAmount++] = new HClient(name,"",0);
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
			hotelReservations[hotelReservationAmount++] = new HReservation(resnumber,number,name,date,nights,persons,value,false,"");
	}
	
	// Search reservation by number
	int hotelSearchReservations (int number) {
		for (int i = 0; i < hotelReservationAmount; i++)
			if (hotelReservations[i].getNumber() == number) 
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
			date = toDate(name);
			if (date > 0) {
				System.out.println("List of orders on date "+toString(date)+":");
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
			if (((service.equals("")) || ((!service.equals("")) && (hotelOrders[i].getName().equals(service)))) &&
			((client.equals("")) || (!client.equals("")) && (hotelOrders[i].getContact().equals(client))) &&
			((date == 0) || ((date > 0) && (hotelOrders[i].getDate() == date)))) {
				hotelOrders[i].print();
				for (int j = 0; j < hotelServiceAmount; j++)
					if (hotelOrders[i].getName().equals(hotelServices[j].getName()))
						System.out.println("Price of service "+hotelOrders[i].getName()+" is "+hotelServices[j].getPrice()+"$.");
				for (int j = 0; j < hotelReservationAmount; j++)
					if (hotelOrders[i].getContact().equals(hotelReservations[j].getName()))
						if ((hotelOrders[i].getDate() >= hotelReservations[j].getDate()) && (hotelOrders[i].getDate() <= hotelReservations[j].getDate()+hotelReservations[j].getLowPrice()))
							if (hotelReservations[j].getAccept())
								System.out.println("Client "+hotelOrders[i].getContact()+" lives in room "+hotelReservations[j].getRoom()+".");
								
			}
		}
	}
	
	// Input new or edit order information
	void hotelEditOrders (boolean isNew, int index) {
		String name, service = "", client = "";
		int number, date, find = 0;
		boolean isUnique = false, isRight = false, isExit = false;
		if (!isNew)
			if ((index >= 0) && (index < hotelOrderAmount)) {
				System.out.println("Old information:");
				hotelOrders[index].print();
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
		else number = hotelOrders[index].getNumber();
		do {
			System.out.print("Enter the date (DD/MM): ");
			name = makeDateChoise();
			date = toDate(name);
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
				hotelOrders[index].set(number,date,service,client,hotelServices[find].getPrice());
			}
			else hotelOrders[hotelOrderAmount++] = new HOrder(number,date,service,client,hotelServices[find].getPrice());
	}
	
	// Search orders by number
	int hotelSearchOrders (int number) {
		for (int i = 0; i < hotelOrderAmount; i++)
			if (hotelOrders[i].getNumber() == number) 
				return i;
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExtHotel myHotel = new ExtHotel("Robinson", false);
		System.out.println("Welcome to the hotel "+myHotel.getName()+"!\n");
		while (myHotel.hotelMenu());
	}
}
