package lesson3;

import java.util.Scanner;

import lesson1.Test1;
import lesson2.Test2;

public class Test3 {
	
	static char[] separators = {'.', '!', '?', ' ', '"', '(', ')', ',', ':', ';', '[', ']', 
			'\\', '{', '}', '&', '|', '<', '>', '=', '+', '*', '-', '/'};
	
	static boolean isSeparator (char ch) {
		for (int i = 0; i < separators.length; i++) {
			if (ch == separators[i]) return true;
		}
		return false;
	}

	static boolean isSeparatorOperation (char ch) {
		for (int i = separators.length - 4; i < separators.length; i++) {
			if (ch == separators[i]) return true;
		}
		return false;
	}
	
	static boolean isSeparatorSentence (char ch) {
		for (int i = 0; i < 3; i++) {
			if (ch == separators[i]) return true;
		}
		return false;
	}
	
	
	static int stringWordCount (String str, boolean sentence) {
		int count = 0;
		boolean isPrev = false, isSep = false;
		for (int i = 0; i < str.length(); i++) {
			if (sentence) isSep = isSeparatorSentence(str.charAt(i));
			else isSep = isSeparator(str.charAt(i));
			//System.out.println(isSep + " " + isPrev + " " + i);
			if ((!isSep) && ((i == 0) || (isPrev)))
				count++;
				isPrev = isSep;
		}
		return count;
	}

	static void stringWordCoords (String str, int[] coords, boolean sentence) {
		int count = 0;
		boolean isPrev = true, isSep = false;
		for (int i = 0; i < str.length(); i++) {
			if (sentence) {
				isSep = isSeparatorSentence(str.charAt(i));
				if ((!isSep) && ((i == 0) || (isPrev))) {
					count++;
					coords[(count - 1) * 2] = i;
					if (count > 1)
						coords[(count - 1) * 2 - 1] = i;
				}
				if (i == str.length() - 1)
					coords[(count - 1) * 2 + 1] = i + 1;
				isPrev = isSep;
			}
			else {
				isSep = isSeparator(str.charAt(i));
				//System.out.println(isSep + " " + isPrev + " " + i);
				if ((!isSep) && ((i == 0) || (isPrev))) {
					count++;
					coords[(count - 1) * 2] = i;
				}
				else if ((isSep) && (!isPrev)) coords[(count - 1) * 2 + 1] = i;
				else if ((!isSep) && (i == (str.length() - 1))) coords[(count - 1) * 2 + 1] = i+1;
				isPrev = isSep;
			}
		}
	}
	
	static void stringPrintCoords (String str, int[] coords) {
		for (int i = 0; i < coords.length; i+=2) {
			System.out.println("Word No. " + (i/2 + 1) + " is in: " + coords[i] + "," + coords[i+1]);
			System.out.println("Word No. " + (i/2 + 1) + " is: " + str.substring(coords[i],coords[i+1]));
		}
	}
	
	static int stringSubCount (String str, String substr) {
		int count, countwords;
		count = 0;
		countwords = stringWordCount(str,false);
		if (countwords > 0) {
			int[] coords = new int[countwords * 2];
			stringWordCoords(str,coords,false);
			for (int i = 0; i < countwords; i++) {
				if (substr.equals(str.substring(coords[i * 2], coords[i * 2 + 1])))
					count++;
			}
		}
		return count;
	}
	
	static void stringGetExpressions (String str) {
		int start1, end1, start2, end2, op1, op2;
		boolean isNumber;
		for (int i = 2; i < str.length() - 2; i++)
			if ((isSeparatorOperation(str.charAt(i))) && (str.charAt(i-1) == ' ') && (str.charAt(i+1) == ' ')) {
				isNumber = false;
				start1 = 0;
				end1 = i-1;
				for (int j = i-2; j >= 0; j--) {
					if ((str.charAt(j) >= '0') && (str.charAt(j) <= '9'))
						isNumber = true;
					else if ((str.charAt(j) == ' ') && (isNumber)) {
						start1 = j + 1;
						break;
					}
					else if (str.charAt(j) == ' ')
						end1 = j;
					else if (isNumber) {
						isNumber = false;
						break;
					}
				}
				if (isNumber) {
					isNumber = false;
					start2 = i+2;
					end2 = str.length();
					for (int j = i+2; j < str.length(); j++) {
						if ((str.charAt(j) >= '0') && (str.charAt(j) <= '9'))
							isNumber = true;
						else if ((str.charAt(j) == ' ') && (isNumber)) {
							end2 = j;
							break;
						}
						else if (str.charAt(j) == ' ')
							start2 = j+1;
						else if (isNumber) {
							isNumber = false;
							break;
						}
					}
					if (isNumber) {
						op1 = Integer.parseInt(str.substring(start1,end1));
						op2 = Integer.parseInt(str.substring(start2,end2));
						System.out.println("Operation find:");
						System.out.println(str.substring(start1,end2));
						System.out.print("The result is:" );
						if (str.charAt(i) == '+')
							System.out.println(op1 + op2 + "");
						else if (str.charAt(i) == '-')
							System.out.println(op1 - op2 + "");
						else if (str.charAt(i) == '*')
							System.out.println(op1 * op2 + "");
						else if (str.charAt(i) == '/')
							if (op2 != 0)
								System.out.println(op1 / op2 + "");
							else System.out.println("division by zero");
					}
				}
			}
	}
	
	static void stringPrintMaxWord (String str, int[] coords, char letter) {
		int max, maxword, count;
		String strsec;
		max = 0;
		maxword = 0;
		for (int i = 0; i < coords.length; i+=2) {
			strsec = str.substring(coords[i], coords[i+1]);
			if (strsec.indexOf(letter) > -1) { 
				count = 0;
				for (int j = 0; j < strsec.length(); j++)
					if (strsec.charAt(j) == letter)
						count++;
				if (count > max) {
					max = count;
					maxword = i / 2;
				}
			}
		}
		if (max > 0) {
			System.out.println("Maximun entries of \'" + letter + "\' is " + max);
			System.out.println("in the word \"" + str.substring(coords[maxword * 2], coords[maxword * 2 + 1]));
		}
		else 
			System.out.println("No entries of \'" + letter + "\'.");
	}
	
	static int stringCountBeginWords (String str, int[] coords, char letter) {
		int count = 0;
		for (int i = 0; i < coords.length; i+=2)
			if (str.charAt(coords[i]) == letter)
				count++;
		return count;
	}
	
	
	static int stringCountWords (String str, int[] coords) {
		int count = 0;
		String strsec;
		boolean isWord;
		for (int i = 0; i < coords.length; i+=2) {
			strsec = str.substring(coords[i],coords[i+1]);
			isWord = true;
			for (int j = 0; j < strsec.length(); j++)
				if ((strsec.toUpperCase().charAt(j) < 'A') || (strsec.toUpperCase().charAt(j) > 'Z')) {
					isWord = false;
					break;
				}
			if (isWord)
				count++;
		}
		return count;
	}
	
	static int stringCountValues (String str, int[] coords) {
		int count = 0;
		String strsec;
		boolean isValue;
		for (int i = 0; i < coords.length; i+=2) {
			strsec = str.substring(coords[i],coords[i+1]);
			isValue = true;
			for (int j = 0; j < strsec.length(); j++)
				if ((strsec.toUpperCase().charAt(j) < '0') || (strsec.toUpperCase().charAt(j) > '9')) {
					isValue = false;
					break;
				}
			if (isValue)
				count++;
		}
		return count;
	}
	
	static String stringTrim(String str) {
		String strsec = "";
		int start = 0, current = 0;
		do {
			current = str.indexOf(' ',start);
			if (current < 0) {
				strsec += str.substring(start);
				start = str.length();
			}
			else {
				if (current > 0) 
					strsec += str.substring(start,current+1);
				else
					strsec += str.substring(start,current);
				start = current + 1;
				for (int j = current+1; j < str.length(); j++)
					if (str.charAt(j) == ' ')
						start++;
					else break;
				if (start == str.length())
				  strsec = strsec.substring(0,strsec.length()-1);
			}
		}
		while (start < str.length());
		return strsec;
	}
	
	static int stringWordCountLength(String str, int[] coords, int strlength) {
		int count = 0;
		for (int i = 0; i < coords.length; i+=2)
			if ((coords[i+1] - coords[i]) == strlength)
				count++;
		return count;
	}
	
	static void stringGetSortedWords(String str, int[] coords, int[] coordssec, int strlength) {
		int count = 0, x;
		String strsec;
		for (int i = 0; i < coords.length; i+=2)
			if ((coords[i+1] - coords[i]) == strlength) {
				coordssec[count * 2] = coords[i];
				coordssec[count * 2 + 1] = coords[i+1];
				count++;
			}
		for (int i = 0; i < coordssec.length-2; i+=2)
			for (int j = i+2; j < coordssec.length; j+=2) {
				strsec = str.substring(coordssec[j],coordssec[j+1]);
				if (strsec.compareTo(str.substring(coordssec[i],coordssec[i+1])) < 0) {
					x = coordssec[i];
					coordssec[i] = coordssec[j];
					coordssec[j] = x;
					x = coordssec[i+1];
					coordssec[i+1] = coordssec[j+1];
					coordssec[j+1] = x;
				}
			}
	}
	
	static int stringWordsLetterCount(String str) {
		int count, countwords;
		count = 0;
		countwords = stringWordCount(str,false);
		if (countwords > 0) {
			int[] coords = new int[countwords * 2];
			stringWordCoords(str,coords,false);
			for (int i = 0; i < countwords; i++) {
				if (str.charAt(coords[i *2]) == str.charAt(coords[i * 2 + 1] - 1))
					count++;
			}
		}
		return count;
	}
	
	static String stringFillTo (String str, int strlength) {
		String strsec = "";
		int current = 0, index;
		if (strlength > 0) {
			strsec = str.trim();
			if (strsec.length() <= strlength) {
				current = strsec.length();
				for (int i = 0; i < (strlength - strsec.length()); i++) {
					index = strsec.lastIndexOf(' ', current); 
					if (index > 0) {
						strsec = strsec.substring(0,index+1) + " " + strsec.substring(index+1,strsec.length());
						current = index - 1;
					}
					else if (current < strsec.length())
						current = strsec.length();
				}
			}
			else strsec = strsec.substring(0,strlength);
		}
		return strsec;
	}
	
	static boolean isStringRightExpression (String str) {
		boolean isNum = false, waitPar1 = true, waitNum = true, waitSign1 = true, waitSign2 = false, waitPar2 = false;
		int countPar = 0;
		char curr;
		String strsec = stringTrim(str);
		for (int i = 0; i < strsec.length(); i++) {
			curr = strsec.charAt(i);
			//System.out.println(curr + ": " + "( " + waitPar1 + " 1 " + waitNum + " +|- " + waitSign1 + " *|/|% " + waitSign2 + " ) " + waitPar2 +
			//		" OP " + isNum + " - " + countNum);
			if (curr == '(') 
				if (waitPar1) {
					countPar++;
					waitNum = true;
					waitSign1 = true;
					waitSign2 = false;
					waitPar2 = false;
				}
				else return false;
			else if (curr == ')')
				if (waitPar2) {
					if (isNum)
						isNum = false;
					countPar--;
					waitSign1 = true;
					waitSign2 = true;
					if (countPar > 0)
						waitPar2 = true;
				}
				else return false;
			else if ((curr == '-') || (curr == '+'))
				if (waitSign1) {
					if (isNum)
						isNum = false;
					waitPar1 = true;
					waitNum = true;
					waitSign1 = false;
					waitSign2 = false;
					waitPar2 = false;				
				}
				else return false;
			else if ((curr == '*') || (curr == '/') || (curr == '%'))
				if (waitSign2) {
					if (isNum)
						isNum = false;
					waitPar1 = true;
					waitNum = true;
					waitSign1 = false;
					waitSign2 = false;
					waitPar2 = false;				
				}
				else return false;
			else if ((curr >= '0') && (curr <= '9'))
				if (waitNum) {
					if (!isNum)	
						isNum = true;
					waitPar1 = false;
					waitNum = true;
					waitSign1 = true;
					waitSign2 = true;
					if (countPar > 0)
						waitPar2 = true;
				}
				else return false;
			else if (curr == ' ') {
				if (isNum) {
					isNum = false;
					waitPar1 = false;
					waitNum = false;
					waitSign1 = true;
					waitSign2 = true;
					if (countPar > 0)
						waitPar2 = true;
				}
			}
			else return false;
		}
		if (countPar > 0)
			return false;
		return true;
	}
	
	static String stringWordReverse (String str, int[] coords) {
		String strsec = str.substring(0,coords[0]);
		StringBuffer strb;
		for (int i = 0; i < coords.length; i+=2) {
			strb = new StringBuffer(str.substring(coords[i],coords[i+1]));
			strb.reverse();
			if (i < coords.length - 2)
				strsec += strb.toString() + str.substring(coords[i+1],coords[i+2]);
			else
				strsec += strb.toString() + str.substring(coords[i+1],str.length());
		}
		return strsec;
	}
	
	static boolean isParsRight (String str) {
		StringBuffer strb = new StringBuffer(str);
		int count = 0;
		for (int i = 0; i < strb.length(); i++)
			if (strb.charAt(i) == '(')
				count++;
			else if (strb.charAt(i) == ')')
				if (count > 0)
					count--;
				else return false;
		if (count == 0)
			return true;
		else
			return false;
	}
	
	static boolean stringIsUnique (String str, int index) {
		if ((index >= 0) && (index <= str.length())) {
			for (int i = 0; i < index; i++)
				if (str.charAt(i) == str.charAt(index)) return false;
			return true;
		}
		else return false;
	}
	
	static int stringNumberOfUnique (String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++)
			if ((str.toUpperCase().charAt(i) >= 'A') && (str.toUpperCase().charAt(i) <= 'Z') && stringIsUnique(str, i))
				count++;
		return count;
	}
	
	static void stringFillWithUnique (String str, char[] letters) {
		int index = 0, count;
		for (int i = 0; i < str.length(); i++)
			if ((str.toUpperCase().charAt(i) >= 'A') && (str.toUpperCase().charAt(i) <= 'Z') && stringIsUnique(str, i)) {
				letters[index * 2] = str.charAt(i);
				count = 1;
				for (int j = i+1; j < str.length(); j++)
					if (str.charAt(j) == str.charAt(i))
					 count++;
				letters[index++ * 2 + 1] = (char) count;
				}
	}
	
	static void stringPrintUnique (char[] letters) {
		System.out.println("The letters:");
		for (int i = 0; i < letters.length; i+=2) {
			System.out.print(letters[i] + " - " + ((int) letters[i+1]) + " раз");
			if ((((((int) letters [i+1]) % 10) >= 2) && ((((int) letters [i+1]) % 10) <= 4)
					&& (((((int) letters [i+1]) % 100) <= 11) || ((((int) letters [i+1]) % 100) >= 20))))
				System.out.print("а");
			System.out.println(",");
		}
	}
	
	static String stringReplaceNumbers(String str) {
		StringBuffer strb = new StringBuffer(str);
		char number;
		int index1 = strb.indexOf("[",0), index2 = 0;
		while (index1 > 0) {
			index2 = strb.indexOf("]",index1+1);
			if (index2 > 0) {
				for (int i = index1+1; i < index2; i++)
					if ((strb.charAt(i) >= '0') && (strb.charAt(i) <= '9'))
						for (int j = i+1; j < index2; j++)
							if ((strb.charAt(j) >= '0') && (strb.charAt(j) <= '9') && (strb.charAt(j) < strb.charAt(i))) {
								number = strb.charAt(i);
								strb.setCharAt(i,strb.charAt(j));
								strb.setCharAt(j,number);
							}
			index1 =  strb.indexOf("[",index2+1);		
			}
			else index1 = -1; 
		}
		return strb.toString();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str, strsec, strsub;
		char letter;
		int count, countsec, countwords, countvalues, strlength;
		int [] coords, coordssec;
		StringBuilder strb;
		char[] letters;
		boolean repeat = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Lesson Three");
		
		System.out.println("\nTask No. 0\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordCount(str,false);
			System.out.print("Number of words: ");
			System.out.println(count);
			if (count > 0) {
				coords = new int[count * 2];
				stringWordCoords(str,coords,false);
				stringPrintCoords(str,coords);
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 1\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = 0;
			countsec = 0;
			for (int i = 0; i < str.length(); i++) {
				if ((str.toUpperCase().charAt(i) >= 'A') && (str.toUpperCase().charAt(i) <= 'Z')) 
					count++;
				if ((str.charAt(i) >= '0') && (str.charAt(i) <= '9'))
					countsec++;
			}
			System.out.print("Number of letters: ");
			System.out.println(count);
			System.out.print("Number of numbers: ");
			System.out.println(countsec);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
	
		System.out.println("\nTask No. 2\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			System.out.print("Input substring: ");
			strsec = sc.nextLine();
			count = stringSubCount(str,strsec);
			System.out.print("Number of entries: ");
			System.out.println(count);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 3\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			stringGetExpressions(str);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 4\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordCount(str,false);
			if (count > 0) {
				System.out.print("Input letter: ");
				strsec = sc.nextLine();
				letter = strsec.charAt(0);
				coords = new int[count * 2];
				stringWordCoords(str,coords,false);
				stringPrintMaxWord(str,coords,letter);
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 5\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordCount(str,false);
			if (count > 0) {
				System.out.print("Input letter: ");
				strsec = sc.nextLine();
				letter = strsec.charAt(0);
				coords = new int[count * 2];
				stringWordCoords(str,coords,false);
				countwords = stringCountBeginWords(str,coords,letter);
				System.out.println("Number of words begins with \'" + letter + "\': " + countwords);
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 6\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordCount(str,false);
			if (count > 0) {
				coords = new int[count * 2];
				stringWordCoords(str,coords,false);
				countwords = stringCountWords(str,coords);
				countvalues = stringCountValues(str,coords);
				System.out.println("Number of words: " + countwords);
				System.out.println("Number of values: " + countvalues);
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 7\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			strsec = stringTrim(str);
			System.out.println("Reult string:");
			System.out.println(strsec);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 8\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			System.out.print("Input substring: ");
			strsec = sc.nextLine();
			count = stringWordCount(str,true);
			System.out.print("Number of sentences: ");
			System.out.println(count);
			if (count > 0) {
				coords = new int[count * 2];
				stringWordCoords(str,coords,true);
				for (int i = 0; i < coords.length; i+=2) {
					strsub = str.substring(coords[i],coords[i+1]);
					if (stringSubCount(strsub,strsec) > 0)
						System.out.println("The next sentence has a word \"" + strsec + "\":\n" + strsub);
				}
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 9\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			System.out.print("Input length: ");
			strlength = sc.nextInt();
			if (strlength > 0) {
				count = stringWordCount(str,false);
				if (count > 0) {
					coords = new int[count * 2];
					stringWordCoords(str,coords,false);
					countsec = stringWordCountLength(str,coords,strlength);
					if (countsec > 0) {
						coordssec = new int[countsec * 2];
						stringGetSortedWords(str,coords,coordssec,strlength);
						System.out.println("Words with length " + strlength + " are:");
						for (int i = 0; i < coordssec.length; i+=2) {
							System.out.println(str.substring(coordssec[i],coordssec[i+1]));
						}
					}
				}
			}
			else System.out.println("Wrong length");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 10\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordsLetterCount(str);
			System.out.println("Number of words that begins and end the same letter is: " + count);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 11\n");
		do {
			System.out.print("Input string with (, ), +, -, *, /, %, 0..9 : ");
			str = sc.nextLine();
			if (isStringRightExpression(str))
				System.out.println("This string is right expresion.");
			else 
				System.out.println("This string is wrong expresion.");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 12\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordCount(str,true);
			System.out.print("Number of sentences: ");
			System.out.println(count);
			if (count > 0) {
				System.out.print("Input length: ");
				strlength = sc.nextInt();
				if (strlength > 0) {
					coords = new int[count * 2];
					stringWordCoords(str,coords,true);
					for (int i = 0; i < coords.length; i+=2) {
						strsub = str.substring(coords[i],coords[i+1]);
						strsub = stringFillTo(strsub,strlength);
						System.out.println(strsub);
					}
				}
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 13\n");
		do {
			System.out.print("Input word: ");
			str = sc.nextLine();
			strb = new StringBuilder(str);
			strsec = strb.reverse().toString();
			if (str.equals(strsec))
				System.out.println("This is palindrom.");
			else 
				System.out.println("This is not palindrom.");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 14\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordCount(str,false);
			if (count > 0) {
				coords = new int[count * 2];
				stringWordCoords(str,coords,false);
				strsec = stringWordReverse(str,coords);
				System.out.println("Result string:");
				System.out.println(strsec);
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 15\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			if (isParsRight(str))
				System.out.println("Parenthesis is right.");
			else 
				System.out.println("Parenthesis is wrong.");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 16\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringNumberOfUnique(str);
			letters = new char[count * 2];
			stringFillWithUnique(str,letters);
			stringPrintUnique(letters);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 17\n");
		do {
			System.out.print("Input value in basis 2: ");
			str = sc.nextLine();
			if (Test2.isRightValue(str,2)) {
				count = Test2.basisToDecimal(str,2);
				System.out.println("Result is: " + count);
			}
			else System.out.println("Wrong value");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 18\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			strsec = stringReplaceNumbers(str);
			System.out.println("Result string:");
			System.out.println(strsec);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 19\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			strb = new StringBuilder(str);
			if (strb.indexOf(".") > 0) {
				strb.delete(strb.indexOf("."),strb.length());
				count = stringWordCount(strb.toString(),false);
				System.out.print("Number of words: ");
				System.out.println(count);
			}
			else System.out.println("Wrong string:");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 20\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			System.out.print("Input letter: ");
			strsec = sc.nextLine();
			letter = strsec.charAt(0);
			count = stringWordCount(str,false);
			if (count > 0) {
				coords = new int[count * 2];
				stringWordCoords(str,coords,false);
				countwords = stringCountBeginWords(str,coords,letter);
				System.out.println("Number of words begins with \'" + letter + "\': " + countwords);
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 21\n");
		do {
			System.out.print("Input string (r,k,t): ");
			str = sc.nextLine();
			int cr = 0, ck = 0, ct = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == 'r')
					cr++;
				else if (str.charAt(i) == 'k')
					ck++;
				else if (str.charAt(i) == 't')
					ct++;
			}
			System.out.println("Number of r: " + cr);
			System.out.println("Number of k: " + ck);
			System.out.println("Number of t: " + ct);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 22\n");
		do {
			System.out.print("Input string (*,;,:): ");
			str = sc.nextLine();
			int cr = 0, ck = 0, ct = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '*')
					cr++;
				else if (str.charAt(i) == ';')
					ck++;
				else if (str.charAt(i) == ':')
					ct++;
			}
			System.out.println("Number of *: " + cr);
			System.out.println("Number of ;: " + ck);
			System.out.println("Number of :: " + ct);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 23\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			count = stringWordCount(str,false);
			int len, max = 0, min = str.length(); 
			if (count > 0) {
				coords = new int[count * 2];
				stringWordCoords(str,coords,false);
				for (int i = 0; i < count; i++) {
					len = (coords[i*2+1] - coords[i*2]);
					if (len < min)
						min = len;
					else if (len > max)
						max = len;
				}
				System.out.println("Minimal size of word is " + min);
				System.out.println("Maximum size of word is " + max);
			}
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 24\n");
		do {
			System.out.print("Input string (:): ");
			str = sc.nextLine();
			count = str.indexOf(':');
			if (count > 0)
				System.out.println("Number of symbols before \':\' is " + count);
			else System.out.println("No symbols \':\' in string");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 25\n");
		do {
			System.out.print("Input string: ");
			str = sc.nextLine();
			strb = new StringBuilder(str);
			if (strb.indexOf(".") > 0) {
				strb.delete(strb.indexOf("."),strb.length());
				strsec = strb.toString(); 
				count = stringWordCount(strsec,false);
				if (count > 0) {
					coords = new int[count * 2];
					stringWordCoords(strsec,coords,false);
					System.out.println("The words of 3 symbols are:");
					for (int i = 0; i < count; i++)
						if ((coords[i*2+1] - coords[i*2]) == 3)
							System.out.print(str.substring(coords[i*2], coords[i*2+1]) + ", ");
					System.out.println();
				}
			}
			else System.out.println("Wrong string:");
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;

		System.out.println("\nTask No. 26\n");
		do {
			System.out.print("Input string (*): ");
			str = sc.nextLine();
			strb = new StringBuilder(str);
			for (int i = 0; i < strb.length(); i++)
				if (strb.charAt(i) == '*') {
					strb.delete(i,i+1);
					i--;
				}
				else {
					strb.insert(i,strb.charAt(i));
					i++;
				}
			System.out.println("Result string:\n" + strb.toString());
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("\nTask No. 27\n");
		do {
			System.out.print("Input string (abc): ");
			//str = sc.nextLine();
			//strb = new StringBuilder(str);
			strb = new StringBuilder(sc.nextLine());
			count = 0;
			countsec = 0;
			do {
				countsec = strb.indexOf("abc",countsec+1);
				if (countsec > 0) count++;
			} while (countsec > 0);
			System.out.println("Number of entries of \'abc\' is " + count);
			repeat = Test1.isRepeat(sc);
			sc.nextLine();
		} while (repeat);
		repeat = false;
		
		System.out.println("The end.");
		sc.close();
	}
}
