package lesson2;

import java.util.Random;
import java.util.Scanner;

import lesson1.Test1;

public class Test2 {

	static void arrayFillValue(int[] arr, int value) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = value;
		}
	}
	
	static void arrayFillRandom(int[] arr, int from, int to) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(to - from) + from;
		}
	}
	
	static void arrayPrint(int[] arr) {
		for(int i=0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	static void arrayPrintMatrix(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			arrayPrint(arr[i]);
		}
	}
	
	static void arrayChange(int[] arr, int from, int to) {
		int x;
		if ((from >= 0) && (from < arr.length) && (to >= 0) && (to < arr.length)) {
			x = arr[from];
			arr[from] = arr[to];
			arr[to] = x;
		}
	}
	
	static void arraySortBySign(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < 0) {
				if (arr[i-1] >= 0) {
					arrayChange(arr,i-1,i);
					if (i > 1) i-=2;
				}
			}
			else if ((arr[i]) == 0) {
				if (arr[i-1] > 0) {
					arrayChange(arr,i-1,i);
					if (i > 1) i-=2;
				}
			}
		}
	}
	
	static void arrayFindSimple(int[] arr) {
		for (int i = 1; i < arr.length; i++) 
			if (Test1.isSimple(arr[i])) 
				System.out.println("Simple value " + arr[i] + " in position " + i);
	}
	
	static boolean arrayIsUnique(int[] arr, int index) {
		if ((index >= 0) && (index <= arr.length)) {
			for (int i = 0; i < index; i++)
				if (arr[i] == arr[index]) return false;
			return true;
		}
		else return false;
	}
	
	static int arrayNumberOfUnique(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++)
			if (arrayIsUnique(arr, i)) count++;
		return count;
	}
	
	static void arrayFillWithUnique(int[] arr, int[] arrres) {
		int index = 0;
		for (int i = 0; i < arr.length; i++)
			if (arrayIsUnique(arr, i))
				arrres[index++] = arr[i];
	}
	
	static void arrayConcat(int[] arr, int[] arrsec, int[] arrres) {
		int pos = 0;
		for (int i = 0; i < arr.length; i++) {
			if (pos >= arrsec.length) arrres[i+pos] = arr[i];
			else if (arr[i] < arrsec[pos]) arrres[i+pos] = arr[i];
			else arrres[(i--)+pos] = arrsec[pos++];
		}
		for (int j = pos; j < arrsec.length; j++) arrres[arr.length+j] = arrsec[j];
	}
	
	static void arraySort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++)
			for (int j = i+1; j < arr.length; j++)
				if (arr[j] < arr[i]) arrayChange(arr, i, j);
	}
	
	static void arraySortBubble(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr.length-i-1; j++)
				if (arr[j] > arr[j+1]) arrayChange(arr, j, j+1);
	}
	
	static void arraySortQuick(int[] arr, int from, int to) {
		int pos = from;
		if (from >= to) return;
		arrayChange(arr,from,(from+to)/2);
		for (int i = from + 1; i <= to; i++)
			if (arr[i] < arr[from]) arrayChange(arr,++pos,i);
		arrayChange(arr,from,pos);
		arraySortQuick(arr,from,pos-1);
		arraySortQuick(arr,pos+1,to);
	}
	
	static void arrayFillPoints(int[][] arr, int radius) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i][0] = rand.nextInt(radius * 4) - radius * 2;
			arr[i][1] = rand.nextInt(radius * 4) - radius * 2;
			if ((Math.abs(arr[i][0]) <= radius) && (Math.abs(arr[i][1]) <= radius)) arr[i][2] = 1;
			else arr[i][2] = 0;
		}
	}
	
	
	static boolean arrayIsSideCross(int x1, int y1, int x2, int y2, int r) {
		double d, d1, d2;
		d = Math.pow(r,2)*(Math.pow(x2-x1,2)+Math.pow(y2-y1,2))-Math.pow(((x1-x2)*y1-(y1-y2)*x1),2);
		if (d < 0) return false;
		d1 = (x1*(x1-x2)+y1*(y1-y2)+Math.sqrt(d)) / (Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
		d2 = (x1*(x1-x2)+y1*(y1-y2)-Math.sqrt(d)) / (Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
		if (((d1 >= 0) && (d1 <= 1)) || ((d2 >= 0) && (d2 <= 1))) return true;
		else return false;
	}
	
	static void arrayPrintTriangles(int[][] arr, int radius, boolean contains) {
		for (int i = 0; i < arr.length-2; i++) 
			for (int j = i+1; j < arr.length-1; j++) 
				for (int k = j+1; k < arr.length; k++) 
					if (contains) {
						if ((arr[i][2] * arr[j][2] * arr[k][2]) == 1)
							System.out.println("{(" + arr[i][0] + "," + arr[i][1] + 
								"),(" + arr[j][0] + "," + arr[j][1] + 
								"),(" + arr[k][0] + "," + arr[k][1] + ")}");
					}
					// else if ((arr[i][2] + arr[j][2] + arr[k][2]) > 0)
					else if (arrayIsSideCross(arr[i][0],arr[i][1],arr[j][0],arr[j][1],radius) || 
							arrayIsSideCross(arr[i][0],arr[i][1],arr[k][0],arr[k][1],radius) ||
							arrayIsSideCross(arr[k][0],arr[k][1],arr[j][0],arr[j][1],radius))
							System.out.println("{(" + arr[i][0] + "," + arr[i][1] + 
								"),(" + arr[j][0] + "," + arr[j][1] + 
								"),(" + arr[k][0] + "," + arr[k][1] + ")}");
	}
	
	static void arrayFillRandomMatrix(int[][] arr) {
		boolean[] arruse = new boolean[arr.length * arr.length];
		int value;
		boolean isFill;
		Random rand = new Random();
		for (int i = 0; i < arr.length * arr.length; i++) {
			isFill = false;
			do {
				value = rand.nextInt(arr.length * arr.length) + 1;
				if (!arruse[value-1]) {
					arruse[value-1] = true;
					arr[i / arr.length][i % arr.length] = value;
					isFill = true;
				}
			}
			while (!isFill);
		}
	}
	
	static void arrayFillMagicMatrixOdd(int[][] arr) {
		int i, j, k;
		for (i = 0; i < arr.length; i++)
			for (j = 0; j < arr.length; j++)
				arr[i][j] = 0;
		i = 0;
		j = arr.length / 2;
		arr[i][j] = 1;
		for (k = 2; k <= Math.pow(arr.length,2); k++) {
			i--;
			j++;
			if ((i < 0) && (j != arr.length)) i = arr.length - 1;
			else if ((j == arr.length) && (i >= 0)) j = 0;
			else if (((i < 0) && (j == arr.length)) || (arr[i][j] != 0)) {
				i+=2;
				j--;
			}
			arr[i][j] = k;	
		}			
	}

	static void arrayFillMagicMatrixTwo(int[][] arr) {
		int i, j, k, l, m, x;
		l = arr.length / 2;
		m = (l - 1) / 2;
		int[][] arrsec = new int[l][l];
		arrayFillMagicMatrixOdd(arrsec);
		k = (int) Math.pow(l,2);
		for (i = 0; i < arr.length; i++) 
			for (j = 0; j < arr.length; j++) {
				if ((i >= 0) && (i < l) && (j >= 0) && (j < l)) arr[i][j] = arrsec[i][j];
				else if ((i >= l) && (i < arr.length) && (j >= l) && (j < arr.length)) arr[i][j] = arrsec[i-l][j-l]+k;
				else if ((i >= 0) && (i < l) && (j >= l) && (j < arr.length)) arr[i][j] = arrsec[i][j-l]+2*k;
				else if ((i >= l) && (i < arr.length) && (j >= 0) && (j < l)) arr[i][j] = arrsec[i-l][j]+3*k;
			}
		for (i = 0; i < l; i++)
			if (i == (l / 2)) {
				j = (l / 2);
				for (k = 0; k < m; k++) {
					x = arr[i][j];
					arr[i][j] = arr[i+l][j];
					arr[i+l][j] = x;
					j--;
				}
			}
			else {
				j = 0;
				for (k = 0; k < m; k++) {
					x = arr[i][j];
					arr[i][j] = arr[i+l][j];
					arr[i+l][j] = x;
					j++;
				}
			}
		j = arr.length - 1;
		for (k = 0; k < m-1; k++) {
			for(i = 0; i < l; i++) {
				x = arr[i][j];
				arr[i][j] = arr[i+l][j];
				arr[i+l][j] = x;
			}
		j--;
		}
	} 
	
	static void arrayFillMagicMatrixFour(int[][] arr) {
		int i, j, k, l, m;
		l = 1;
		m = (int) Math.pow(arr.length,2);
		for (i = 0; i < arr.length; i++)
			for (j = 0; j < arr.length; j++)
				arr[i][j] = l++;
		i = 2;
		while (i <= (arr.length - 2)) {
			if ((i % 4) == 0) j = 4;
			else j = 2;
			while (j <= (arr.length - 2)) {
				for (int i1 = -1; i1 <= 0; i1++)
					for(int j1 = -1; j1 <= 0; j1++)
						arr[i+i1][j+j1] = m - arr[i+i1][j+j1] +1;
				j+=4;
			}
			i+=2;
		}
		k = 4;
		while (k <= (arr.length - 4)) {
			arr[0][k-1] = m - arr[0][k-1] + 1;
			arr[0][k] = m - arr[0][k] + 1;
			arr[arr.length-1][k-1] = m - arr[arr.length-1][k-1] + 1;
			arr[arr.length-1][k] = m - arr[arr.length-1][k] + 1;
			arr[k-1][0] = m - arr[k-1][0] + 1;
			arr[k][0] = m - arr[k][0] + 1;
			arr[k-1][arr.length-1] = m - arr[k-1][arr.length-1] + 1;
			arr[k][arr.length-1] = m - arr[k][arr.length-1] + 1;
			k+=4;
		}
		arr[0][0] = m - arr[0][0] + 1;
		arr[arr.length-1][arr.length-1] = m - arr[arr.length-1][arr.length-1] + 1;
		arr[0][arr.length-1] = m - arr[0][arr.length-1] + 1;
		arr[arr.length-1][0] = m - arr[arr.length-1][0] + 1;
	}
	
	static void arrayFillMagicMatrix(int[][] arr) {
		if ((arr.length % 2) > 0) arrayFillMagicMatrixOdd(arr);
		else if ((arr.length % 4) > 0) arrayFillMagicMatrixTwo(arr);
		else arrayFillMagicMatrixFour(arr);
	}
	
	static boolean arrayIsMagic(int[][] arr) {
		int sum, sumMagic = 0;
		for (int i = 0; i < arr.length; i++) {
			sumMagic += arr[0][i];
		}
		for (int i = 1; i < arr.length; i++) {
			sum = 0;
			for (int j = 0; j < arr.length; j++) sum += arr[i][j];
			if (sum != sumMagic) return false;
		}
		for (int i = 0; i < arr.length; i++) {
			sum = 0;
			for (int j = 0; j < arr.length; j++) sum += arr[j][i];
			if (sum != sumMagic) return false;
		}
		sum = 0;
		for (int i = 0; i < arr.length; i++) sum += arr[i][i];
		if (sum != sumMagic) return false;
		sum = 0;
		for (int i = 0; i < arr.length; i++) sum += arr[i][arr.length-1-i];
		if (sum != sumMagic) return false;
		return true;
	}
	
	static int arraySumOne(int[][] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++)
			for (int j = i+1; j < arr.length; j++)
				sum+=arr[i][j];
		return sum;
	}
	
	static boolean arrayIsSaddlePoint(int[][] arr, int row, int column) {
		boolean isMin = true, isMax = true;
		for (int i = 0; i < arr[row].length; i++)
			if (arr[row][i] < arr[row][column]) isMin = false;
			else if (arr[row][i] > arr[row][column]) isMax = false;
		if (isMin) {
			for (int i = 0; i < arr.length; i++)
				if (arr[i][column] > arr[row][column]) return false;
			return true;
		}
		if (isMax) {
			for (int i = 0; i < arr.length; i++)
				if (arr[i][column] < arr[row][column]) return false;
			return true;
		}
		return false;
	}
	
	static void arrayChangeRowColumn(int[][] arr, int row, int column) {
		int x;
		for (int i = 0; i < arr.length; i++) {
			x = arr[row][i];
			arr[row][i] = arr[i][column];
			arr[i][column] = x;
		}
	}
	
	static void arrayFillWave(int[][] arr, int[][] arrsec, int row, int column) {
		int i, j;
		for (int k = 0; k < 4; k++) {
			if (k < 2) {
				i = -1 + 2*k;
				j = 0;
			}
			else {
				i = 0;
				j = -1 +2*(k-2);
			}
			if (((row+i) < 0) || ((row+i) >= arr.length) || ((column+j) < 0) || ((column+j) >= arr.length)) ;
			else if (arr[row+i][column+j] == 1) ;
			else if (arrsec[row+i][column+j] == 0) {
				arrsec[row+i][column+j] = arrsec[row][column] + 1;
				//arrayFillWave(arr,arrsec,row+i,column+j);
			}
		}
		for (int k = 0; k < 4; k++) {
			if (k < 2) {
				i = -1 + 2*k;
				j = 0;
			}
			else {
				i = 0;
				j = -1 +2*(k-2);
			}
			if (((row+i) < 0) || ((row+i) >= arr.length) || ((column+j) < 0) || ((column+j) >= arr.length)) ;
			else if (arrsec[row+i][column+j] == (arrsec[row][column] + 1)) {
				arrayFillWave(arr,arrsec,row+i,column+j);
			}
		}
	}
	
	static void arrayPrintShortWave(int[][] arrsec) {
		int row = -1, column = -1, min = 0;
		int i, j;
		for (i = 0; i < arrsec.length; i++) {
			if (((min == 0) && (arrsec[0][i] > 0)) || ((min != 0) && (arrsec[0][i] > 0) && (arrsec[0][i] < min))) {
					row = 0;
					column = i;
					min = arrsec[0][i];
				}
			if (((min == 0) && (arrsec[arrsec.length-1][i] > 0)) || ((min != 0) && (arrsec[arrsec.length-1][i] > 0) && (arrsec[arrsec.length-1][i] < min))) {
				row = arrsec.length-1;
				column = i;
				min = arrsec[arrsec.length-1][i];
			}
			if (((min == 0) && (arrsec[i][0] > 0)) || ((min != 0) && (arrsec[i][0] > 0) && (arrsec[i][0] < min))) {
				row = i;
				column = 0;
				min = arrsec[i][0];
			}
			if (((min == 0) && (arrsec[i][arrsec.length-1] > 0)) || ((min != 0) && (arrsec[i][arrsec.length-1] > 0) && (arrsec[i][arrsec.length-1] < min))) {
				row = i;
				column = arrsec.length-1;
				min = arrsec[i][arrsec.length-1];
			}
		}
		System.out.println("Minimum wave is " + min);
		if (min > 1) {
			int[][] wave = new int[min-1][2];
			wave[min-2][0] = row;
			wave[min-2][1] = column;
			for (int l = min; l >= 3; l--) {
				for (int k = 0; k < 4; k++) {
					if (k < 2) {
						i = -1 + 2*k;
						j = 0;
					}
					else {
						i = 0;
						j = -1 +2*(k-2);
					}
					if (((row+i) < 0) || ((row+i) >= arrsec.length) || ((column+j) < 0) || ((column+j) >= arrsec.length)) ;
					else if (arrsec[row+i][column+j] == (l-1)) {
						row+=i;
						column+=j;
						wave[l-3][0] = row;
						wave[l-3][1] = column;
						break;
					}	
				}
			}
			System.out.println("Short wave is: ");
			for (int l = 0; l < min-1; l++) {
				System.out.println((l + 1) + ": (" + (wave[l][0] + 1) + "," + (wave[l][1] + 1) + ")");
			}
		}
	}
	
	public static String decimalToBasis(int value, int basis) {
		char[] values = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		if ((value / basis) > 0)
			return decimalToBasis(value / basis, basis) + values[value % basis];
		else return values[value % basis] + "";
	}
	
	public static boolean isRightValue(String result, int basis) {
		int x = 0;
		for (int i = 0; i < result.length(); i++) {
			x = (int) result.toUpperCase().charAt(i);
			if ((x > 47) && (x < 58)) x -= 48;
			else if ((x > 64) && (x < 71)) x -= 55;
			else x = -1;
			if ((x < 0) || (x > basis-1)) return false;
		}
		return true;
	}
	
	public static int basisToDecimal(String result, int basis) {
		int value = 0, x = 0;
		for (int i = 0; i < result.length(); i++) {
			x = (int) result.toUpperCase().charAt(i);
			if ((x > 47) && (x < 58)) x -= 48;
			else if ((x > 64) && (x < 71)) x -= 55;
			else x = 0;
			value += x * Math.pow(basis,result.length()-1-i);
		}
		return value;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arrLength, arrLengthSec, arrLengthResult, radius;
		boolean repeat = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Lesson Two");
		
		System.out.println("\nTask No. 1\n");
		do {
			System.out.print("Input array length: ");
			arrLength = sc.nextInt();
			if (arrLength > 0) {
				int[] arr = new int[arrLength];
				arrayFillRandom(arr,-20,20);
				System.out.println("Input array:");
				arrayPrint(arr);
				arraySortBySign(arr);
				System.out.println("Result array:");
				arrayPrint(arr);
			}
			else System.out.println("Wrong length");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 2\n");
		do {
			System.out.print("Input array length: ");
			arrLength = sc.nextInt();
			if (arrLength > 0) {
				int[] arr = new int[arrLength];
				arrayFillRandom(arr,1,100);
				System.out.println("Input array:");
				arrayPrint(arr);
				for (int i = 0; i < arr.length; i++) {
					if (Test1.isSimple(arr[i])) System.out.println("Simple value in position: " + i);
				}
			}
			else System.out.println("Wrong length");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 3\n");
		do {
			System.out.print("Input array length: ");
			arrLength = sc.nextInt();
			if (arrLength > 0) {
				int[] arr = new int[arrLength];
				arrayFillRandom(arr,1,10);
				System.out.println("Input array:");
				arrayPrint(arr);
				System.out.println("Number of unique elements: " + arrayNumberOfUnique(arr));
			}
			else System.out.println("Wrong length");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 4\n");
		do {
			System.out.print("Input array length: ");
			arrLength = sc.nextInt();
			if (arrLength > 0) {
				int[] arr = new int[arrLength];
				arrayFillRandom(arr,1,10);
				System.out.println("Input array:");
				arrayPrint(arr);
				arrLengthResult = arrayNumberOfUnique(arr);
				int[] arrres = new int[arrLengthResult];
				arrayFillWithUnique(arr, arrres);
				System.out.println("Unique array:");
				arrayPrint(arrres);
			}
			else System.out.println("Wrong length");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 5\n");
		do {
			System.out.print("Input first array length: ");
			arrLength = sc.nextInt();
			if (arrLength > 0) {
				int[] arr = new int[arrLength];
				arrayFillRandom(arr,-20,20);
				System.out.println("First array:");
				arrayPrint(arr);
				System.out.print("Input second array length: ");
				arrLengthSec = sc.nextInt();
				if (arrLengthSec > 0) {
					int[] arrsec = new int[arrLengthSec];
					arrayFillRandom(arrsec,-20,20);
					System.out.println("Second array:");
					arrayPrint(arrsec);
					arraySortBubble(arr);
					System.out.println("First sorted array:");
					arrayPrint(arr);
					arraySortQuick(arrsec,0,arrsec.length-1);
					System.out.println("Second sorted array:");
					arrayPrint(arrsec);
					int[] arrres = new int[arrLength + arrLengthSec];
					arrayConcat(arr, arrsec, arrres);
					System.out.println("Result array:");
					arrayPrint(arrres);
				}
				else System.out.println("Wrong length");
			}
			else System.out.println("Wrong length");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 6\n");
		do {
			System.out.print("Input number of points: ");
			arrLength = sc.nextInt();
			if (arrLength > 0) {
				int[][] arr = new int[arrLength][3];
				System.out.print("Input radius: ");
				radius = sc.nextInt();
				if (radius > 0) {
					arrayFillPoints(arr,radius);
					System.out.println("Input points:");
					System.out.print("{");
					for (int i = 0; i < arr.length-1; i++) 
						System.out.print("("+ arr[i][0] + "," + arr[i][1] + "),");
					System.out.println("("+ arr[arr.length-1][0] + "," + arr[arr.length-1][1] + ")}");
					System.out.println();
					System.out.println("Triangles cross the circle:");
					arrayPrintTriangles(arr,radius,false);
					System.out.println();
					System.out.println("Triangles contains the circle:");
					arrayPrintTriangles(arr,radius,true);
				}
				else System.out.println("Wrong radius");		
			}
			else System.out.println("Wrong number");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 7\n");
		do {
			System.out.print("Input matrix size: ");
			arrLength = sc.nextInt();
			if (arrLength > 1) {
				int[][] arr = new int[arrLength][arrLength];
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						//if ((i == 0) || (i == arrLength-1) || (j == 0) || (j == arrLength-1)) arr[i][j] = 1;
						if (((i % (arrLength - 1)) * (j % (arrLength - 1))) == 0) arr[i][j] = 1;
						else arr[i][j] = 0;
				System.out.println("First matrix:");
				arrayPrintMatrix(arr);
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						if (j < arrLength - i) arr[i][j] = i+1;
						else arr[i][j] = 0;
				System.out.println("Second matrix:");
				arrayPrintMatrix(arr);
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						if (((j > i - 1) && (j < arrLength - i)) || ((j < i + 1) && (j > arrLength - i - 2))) arr[i][j] = 1;
						else arr[i][j] = 0;
				System.out.println("Third matrix:");
				arrayPrintMatrix(arr);
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						if (((i > j - 1) && (i < arrLength - j)) || ((i < j + 1) && (i > arrLength - j - 2))) arr[i][j] = 1;
						else arr[i][j] = 0;
				System.out.println("Fourth matrix:");
				arrayPrintMatrix(arr);
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						if (j < i + 1) arr[i][j] = arrLength - i + j;
						else arr[i][j] = 0;
				System.out.println("Fifth matrix:");
				arrayPrintMatrix(arr);
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						if (j < arrLength - i) arr[i][j] = i + j + 1;
						else arr[i][j] = 0;
				System.out.println("Sixth matrix:");
				arrayPrintMatrix(arr);
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						if (j == i) arr[i][j] = i + 1;
						else if (j == arrLength-1-i) arr[i][j] = i + 1;
						else arr[i][j] = 0;
				System.out.println("Seventh matrix:");
				arrayPrintMatrix(arr);
				for (int i = 0; i < arrLength; i++)
					for (int j = 0; j < arrLength; j++)
						arr[i][j] = 1 + Math.abs(i-j);
				System.out.println("Eighth matrix:");
				arrayPrintMatrix(arr);				
			}
			else System.out.println("Wrong size");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 8\n");
		do {
			System.out.print("Input matrix size: ");
			arrLength = sc.nextInt();
			if (arrLength > 2) {
				// int[][] arrsec = {{6,1,8},{7,5,3},{2,9,4}};
				// System.out.println("Is Magic:" + arrayIsMagic(arrsec));
				int[][] arr = new int[arrLength][arrLength];
				arrayFillMagicMatrix(arr);
				System.out.println("Magic matrix:");
				arrayPrintMatrix(arr);
				System.out.println("Test Is Magic return " + arrayIsMagic(arr));
			}
			else System.out.println("Wrong size");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 9\n");
		do {
			System.out.print("Input matrix size: ");
			arrLength = sc.nextInt();
			if (arrLength > 1) {
				int[][] arr = new int[arrLength][arrLength];
				arrayFillRandomMatrix(arr);
				System.out.println("Random matrix:");
				arrayPrintMatrix(arr);
				System.out.println("SumOne: " + arraySumOne(arr));
			}
			else System.out.println("Wrong size");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 10\n");
		do {
			System.out.print("Input first matrix size: ");
			arrLength = sc.nextInt();
			if (arrLength > 1) {
				System.out.print("Input second matrix size: ");
				arrLengthSec = sc.nextInt();
				if (arrLengthSec > 1) {
					int[][] arr = new int[arrLength][arrLengthSec];
					for (int i = 0; i < arrLength; i++)
						arrayFillRandom(arr[i],0,100);
					System.out.println("Input array:");
					arrayPrintMatrix(arr);
					for (int i = 0; i < arr.length; i++)
						for (int j = 0; j < arr[i].length; j++)
							if (arrayIsSaddlePoint(arr,i,j))
								System.out.println("Saddle Point at: " + i + ", " + j);
				}
				else System.out.println("Wrong size");
			}
			else System.out.println("Wrong size");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 11\n");
		do {
			System.out.print("Input matrix size: ");
			arrLength = sc.nextInt();
			if (arrLength > 1) {
				int[][] arr = new int[arrLength][arrLength];
				for (int i = 0; i < arrLength; i++)
					arrayFillRandom(arr[i],0,10);
				System.out.println("Input array:");
				arrayPrintMatrix(arr);
				arrayChangeRowColumn(arr,arrLength-1,arrLength-1);
				System.out.println("Result array:");
				arrayPrintMatrix(arr);
			}
			else System.out.println("Wrong size");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 12\n");
		do {
			System.out.print("Input matrix size: ");
			arrLength = sc.nextInt();
			if (arrLength > 1) {
				int[][] arr = new int[arrLength][arrLength];
				for (int i = 0; i < arrLength; i++)
					arrayFillRandom(arr[i],0,2);
				System.out.println("Input array:");
				arrayPrintMatrix(arr);
				int row, column;
				System.out.print("Input begin row (from 1): ");
				row = sc.nextInt();
				if ((row > 0) && (row <= arrLength)) {
					System.out.print("Input begin column (from 1): ");
					column = sc.nextInt();
					if ((column > 0) && (column <= arrLength) && (arr[row-1][column-1] == 0)) {
						int[][] arrsec = new int[arrLength][arrLength];
						for (int i = 0; i < arrsec.length; i++)
							for (int j = 0; j < arrsec.length; j++)
								arrsec[i][j] = 0;
						arrsec[row-1][column-1] = 2;
						arrayFillWave(arr, arrsec, row-1, column-1);
						System.out.println("Wave array:");
						arrayPrintMatrix(arrsec);
						arrayPrintShortWave(arrsec);
					}
				else System.out.println("Wrong value");
				}
				else System.out.println("Wrong value");
			}
			else System.out.println("Wrong size");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 13\n");
		int value, basis;
		String result;
		do {
			System.out.print("Input decimal value: ");
			value = sc.nextInt();
			if (value >= 0) {
				System.out.print("Input basis (2, 8, 16): ");
				basis = sc.nextInt();
				if ((basis > 1) && (basis < 17)) {
					result = "";
					result = decimalToBasis(value,basis);
					System.out.println("Result is: " + result);
				}
				else System.out.println("Wrong basis");
			}
			else System.out.println("Wrong value");
			System.out.print("Input basis (2, 8, 16): ");
			basis = sc.nextInt();
			if ((basis > 1) && (basis < 17)) {
				System.out.print("Input value in basis: ");
				result = sc.next();
				if (isRightValue(result,basis)) {
					value = basisToDecimal(result,basis);
					System.out.println("Result is: " + value);
				}
				else System.out.println("Wrong value");
			}
			else System.out.println("Wrong basis");
			repeat = Test1.isRepeat(sc);
		} while (repeat);
		repeat = false;
		
		System.out.println("The end.");
		sc.close();
	}
}