package lesson9;

import java.util.HashMap;

public class Test9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = new int[10];
		arr[0] = 12;
HashMap<String, Integer> hashMap = new HashMap<>();
hashMap.put("k1", 1);
System.out.println(hashMap.get("k1"));
System.out.println(hashMap.containsValue(1));

System.out.println(hashMap.containsKey("k1"));
	}

}
