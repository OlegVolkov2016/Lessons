package lesson10;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test101 {
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		System.out.println((new SimpleDateFormat("yyyy-MM-dd")).format(date).toString());
	}

}
