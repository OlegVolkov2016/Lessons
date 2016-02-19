package lesson2;

import java.util.Random;

public class Test21 {
	
	static void SortArray() {
		int [] arr = new int [10000];
		Random rand = new Random();
		for (int i=0; i < arr.length; i++)
			arr[i] = rand.nextInt(100)-20;
		
		for (int i=0; i < arr.length-1; i++) {
			int index =i;
			for(int j=i+1; j < arr.length; j++) {
				
				if (arr[j] < arr[index]) {
					index =j;
					
					int x = arr[index];
					arr[index] = arr[i];
					arr[i] = x;
					
				}
			}
		}
		for (int i=0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	static void SortArray2() {
		int min, max = 0, mini, minj = 0, maxi, maxj = 0;
		int [][] arr = new int [3][3];
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = rand.nextInt(10);
				System.out.print(arr[i][j] + " ");		
			}
			System.out.println();
		}
		min = arr[0][0];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] < min) {
					min = arr[i][j];
					mini = i;
					minj = j;
				}
				if (arr[i][j] > max) {
					max = arr[i][j];
					maxi = i;
					maxj = j;	
				}
			}
		}
		System.out.println("Min " + min + " in " + minj);
		System.out.println("Max " + max + " in " + maxj);
		int x = 0;
		for (int i = 0; i < arr.length; i++) {
			x = arr[i][minj];
			arr[i][minj] = arr[i][maxj];
			arr[i][maxj] = x;
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");		
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortArray2();
	}

}
