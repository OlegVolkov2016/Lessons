package lesson4;

public class Reservation {
	
	int resNumber, roomNumber;
	String clientName;
	int resDate, resNights, resPersons, resValue;
	boolean isAccomodation;
	
	Reservation (int index, int number, String name, int date, int nights, int persons, int value, boolean isset) {
		setReservation(index, number,name,date,nights,persons,value, isset);
	}
	
	void printReservation () {
		System.out.println("Reservation number "+resNumber+":");
		System.out.println("Room "+roomNumber+".");
		System.out.println("Client: "+clientName+".");
		System.out.println("From "+resToString(resDate)+" to "+resToString((resDate+resNights)%365)+" for "+resPersons+" person(s).");
		System.out.println("Value is "+resValue+"$.");
		System.out.println("Accomodation:"+isAccomodation);
	}

	void setReservation (int index, int number, String name, int date, int nights, int persons, int value, boolean isset) {
		resNumber = index;
		roomNumber = number;
		clientName = name;
		resDate = date;
		resNights = nights;
		resPersons = persons;
		resValue = value;
		isAccomodation = isset;
	}
	
	static int resToDate (String str) {
		int date = -1, month, day;
		boolean isRight = false;
		if (str.length() == 5) {
			for (int i = 0; i < str.length(); i++) {
				isRight = true;
				if (i == 2) 
					if (str.toUpperCase().charAt(i) != '/')
						isRight = false;
					else;
				else if ((str.toUpperCase().charAt(i) < '0') || (str.toUpperCase().charAt(i) > '9'))
					isRight = false;
				if (!isRight) break;
			}
			month = Integer.parseInt(str.substring(3,5));
			if ((month >= 1) && (month <= 12)) {
				day = Integer.parseInt(str.substring(0,2));
				if ((day >=1) && (day <= Hotel.MONTHDAYS[month-1])) {
					date = 0;
					for (int i = 0; i < month-1; i++) {
						date+=Hotel.MONTHDAYS[i];
					}
					date+=day;
				}
			}
		}
		if (isRight) return date;
		else return -1;
	}

	static String resToString(int date) {
		String str = "";
		int index = date;
		boolean isRight = false;
		if ((index >= 1) && (index <= 365)) {
			for (int i = 0; i < 11; i++) {
				if (index > Hotel.MONTHDAYS[i])
					index-=Hotel.MONTHDAYS[i];
				else {
					if (index < 10) str+='0';
					str+=index+"/";
					if (i < 9) str+='0';
					str+=(i+1)+"";
					isRight = true;
				}
				if (isRight) break;
			}
		}
		return str;
	}
}
