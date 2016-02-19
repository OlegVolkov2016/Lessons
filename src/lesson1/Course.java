package lesson1;

import java.util.*;

class Course {
	public static void main(String arg[]) {
		// ПЕРЕМЕННЫЕ
		byte b = 1;
		short s = 2;
		int i = 3;
		long l = 4;
		// float f = 5.1; не компилируется
		float f = (float) 5.1;
		double d = 6.2;
		char c = 'a';
		boolean o = true;
		System.out.println("b = " + b + "\ns = " + s + "\ni = " + i + "\nl = " + l + "\n\tf = " + f + "\n\td = " + d + "\nc = " + c + "\no = " + o + "\n");
		// КОНСТАНТЫ
		final int iconst = 7;
		// iconst = i; не компилируется
		System.out.println("iconst = " + iconst + "\n");
		// ПРЕОБРАЗОВАНИЕ ТИПОВ
		// i = l; не компилируется
		i = (int) l;
		l = b;
		s = (short) c;
		System.out.println("b = " + b + "\ns = " + s + "\ni = " + i + "\nl = " + l + "\n\tf = " + f + "\n\td = " + d + "\nc = " + c + "\no = " + o + "\n");
		// ОПЕРАЦИИ
		s = 2;
		i = 3;
		l = 4;
		System.out.println("Арифметические:\n" + 
			i + " + " + s + " = " + (i + s) + "\n" + 
			i + " - " + s + " = " + (i - s) + "\n" + 
			i + " * " + s + " = " + (i * s) + "\n" + 
			i + " / " + s + " = " + (i / s) + "\n" + // Значение int
			i + " / " + s + " = " + ( (float) i / s) + "\n" + // Значение float
			i + " % " + s + " = " + (i % s) + "\n" + 
			"++" + i + " = " + (++i) + "\n" + 
			i + "++" + " = " + (i++) + "\n" + 
			"--" + i + " = " + (--i) + "\n" + 
			i + "--" + " = " + (i--) + "\n" + 
			i + " += " + s + " = " + (i += s) + "\n" + 
			i + " -= " + s + " = " + (i -= s) + "\n" + 
			i + " *= " + s + " = " + (i *= s) + "\n" + 
			i + " /= " + s + " = " + (i /= s) + "\n" + 
			i + " %= " + s + " = " + (i %= s) + "\n" + 
			"");
		System.out.println("Отношения:\n" + 
			i + " == " + s + " = " + (i == s) + "\n" + 
			i + " != " + s + " = " + (i != s) + "\n" + 
			i + " > " + s + " = " + (i > s) + "\n" + 
			i + " < " + s + " = " + (i < s) + "\n" + 
			i + " >= " + s + " = " + (i >= s) + "\n" + 
			i + " <= " + s + " = " + (i <= s) + "\n" + 
			"");
		System.out.println("Логические:\n" + 
			"! " + o + " = " + (!o) + "\n" + 
			o + " & " + !o + " = " + (o & !o) + "\n" + 
			o + " | " + !o + " = " + (o | !o) + "\n" + 
			o + " ^ " + !o + " = " + (o ^ !o) + "\n" + 
			o + " || " + !o + " = " + (o || !o) + "\n" + 
			o + " && " + !o + " = " + (o && !o) + "\n" + 
			o + " &= " + !o + " = " + (o &= !o) + "\n" + 
			o + " |= " + !o + " = " + (o |= !o) + "\n" + 
			o + " ^= " + !o + " = " + (o ^= !o) + "\n" + 
			o + " == " + !o + " = " + (o == !o) + "\n" + 
			o + " != " + !o + " = " + (o != !o) + "\n" + 
			o + " ? 1 : 2  = " + (o ? 1 : 2) + "\n" + 
			!o + " ? 1 : 2  = " + (!o ? 1 : 2) + "\n" + 
			"");	
			s = 4;
			i = 32;
			l = 64;
		System.out.println("Битовые:\n" + 
			"~ " + i + " = " + (~i) + "\n" + 
			i + " & " + s + " = " + (i & s) + "\n" + 
			i + " | " + s + " = " + (i | s) + "\n" + 
			i + " ^ " + s + " = " + (i ^ s) + "\n" + 
			(-i) + " >> " + "2" + " = " + ((-i) >> 2) + "\n" + 
			(-i) + " >>> " + "2" + " = " + ((-i) >>> 2) + "\n" + 
			(-i) + " << " + "2" + " = " + ((-i) << 2) + "\n" + 
			i + " &= " + s + " = " + (i &= s) + "\n" + 
			i + " |= " + s + " = " + (i |= s) + "\n" + 
			i + " ^= " + s + " = " + (i ^= s) + "\n" + 
			l + " >>= " + "2" + " = " + (l >>= 2) + "\n" + 
			l + " >>>= " + "2" + " = " + (l >>>= 2) + "\n" + 
			l + " <<= " + "2" + " = " + (l <<= 2) + "\n" +
			"");	
		// СИМВОЛЫ И СТРОКИ
		System.out.println("Символы: " + c + ", \\142 - " + '\142' + ", \\u00FA - " + '\u00FA' + ", \', \", \\.");
		String str = "Тестовая строка.";
		System.out.println("Строки: " + str + "\nДлина строки: " + str.length() + 
		"\nСимвол № 3: " + str.charAt(3) + "\nПодстрока: " + str.substring(3, 6) + 
		"\nСравнение с ТЕСТОВАЯ СТРОКА.: " + str.equals("ТЕСТОВАЯ СТРОКА.") + ", " + str.equalsIgnoreCase("ТЕСТОВАЯ СТРОКА.") + 
		"\nСравнение с ТЕСТОВАЯ СТРОКА: " + str.compareTo("ТЕСТОВАЯ СТРОКА") + ", " + str.compareToIgnoreCase("ТЕСТОВАЯ СТРОКА") +  
		"\nПреобразование символов: " + str.toUpperCase() + ", " + str.toLowerCase() + 
		"\nСоединение: " + str.concat(" Ура!") + 
		"\nЗамена: " + str.replace('а', 'А') +
		"\nВхождения т: " + str.indexOf('т') + ", " + str.lastIndexOf('т') + 
		"\nВхождения то: " + str.indexOf("то") + ", " + str.lastIndexOf("то") + 
		"\n");
		//ПРОЦЕДУРЫ
		i = 3;
		l = 4;
		o = true;
		if (i > 2) System.out.println("1");
		if (i < 2) System.out.println("1");
		else System.out.println("2");
		if (i == 3) {
			if (i > 2) System.out.println("3");
		}
		if (i * 2 < 5) System.out.println("3");
		else if (i * 2 > 5) System.out.println("4");
		switch (i) {
			case 2: System.out.println("4");
			break;
			case 3: System.out.println("5");
			case 4: System.out.println("6");
			break;
			default: System.out.println("5");
		}
		while (i > 2) {
			System.out.println("7");
			i--;
		}
		do System.out.println("8");
		while (i > 2);
		for (i = 1; i <= 3; i++) System.out.println("9");
		for (i = 1; i <= 3; i++) {
			System.out.println("10");
			break;
		}
		for (i = 1; i <= 4; i++) {
			System.out.println(10 + i);
			continue;
			// System.out.println("12"); недостижимо
		}
		System.out.println("Значение i после циклов: " + i);
		// МЕТОДЫ
		System.out.println("\nФункция для 1: " + Func(1) + "\nФункция для 3: " + Func(3) + "\n");
		System.out.println("\nФункция для 1: " + Func(1, "Привет") + "\nФункция для 3: " + Func(3, "Пока") + "\n");
		// КЛАССЫ
		FirstClass FC = new FirstClass();
		FC.FCInt1 = 10;
		FC.FCStr1 = "Пример класа";
		FC.getInfo();
		SecondClass SC;
		SC = new SecondClass();
		SC.FCInt1 = 20;
		SC.FCStr1 = "Пример подкласа";
		SC.SCInt1 = 15;
		SC.getInfo();
		SC.getInfo("перегрузкой");
		FC = SC;
		FC.FCStr1 = "Пример передачи ссылки";
		FC.getInfo();
		FC.setNumber(30);
		FC.setString("Пример интерфейса");
		FC.getInfo();
		SC = new SecondClass();
		SC.setNumber(40);
		SC.setString("Пример интерфейса");
		SC.SCInt1 = 20;
		SC.getInfo("наследованием интерфейса");
		ThirdClass TC = new ThirdClass (50, "Пример super", 20);
		TC.getInfo("применением конструктора");
		System.out.println("");
		// ИСКЛЮЧЕНИЯ
		Exc(0);
		Exc(2);
		Exc(10);
		Exc(3);
		Exc(1);
		Exc(4);
		System.out.println("");
		ArithmeticException eA = new ArithmeticException("Исключение.");
		eA.initCause(new NullPointerException("Причина."));
		try {
			throw eA;
		}
		catch (ArithmeticException e) {
			System.out.println(e);
			System.out.println(e.getCause());
		}
		// КОЛЛЕКЦИИ
		// ArrayList
		System.out.println("");
		System.out.println("Списочный массив:");
		ArrayList<String> ALS = new ArrayList<String>();
		System.out.println("Начальный размер: " + ALS.size());
		ALS.add("C");
		ALS.add("A");
		ALS.add("E");
		ALS.add("B");
		ALS.add("D");
		ALS.add("F");
		ALS.add(1, "A1");
		System.out.println("Текущий размер: " + ALS.size());
		System.out.println("Содержимое: " + ALS);
		ALS.remove("F");
		ALS.remove(2);
		System.out.println("Текущий размер: " + ALS.size());
		System.out.println("Содержимое: " + ALS);
		String AL[] = new String[ALS.size()];
		ALS.toArray(AL);
		System.out.print("Содержимое массива: ");
		String A = "";
		for (i=0; i < AL.length; i++) {
			System.out.print(AL[i]);
			A += AL[i];
		}
		System.out.println("");
		System.out.println("В строку: " + A);
		// LinkedList
		System.out.println("");
		System.out.println("Связанный список:");
		LinkedList<String> LLS = new LinkedList<String>();
		System.out.println("Начальный размер: " + LLS.size());
		LLS.add("C");
		LLS.add("A");
		LLS.add("E");
		LLS.add("B");
		LLS.add("D");
		LLS.add("F");
		LLS.add(1, "A1");
		System.out.println("Текущий размер: " + LLS.size());
		System.out.println("Содержимое: " + LLS);
		LLS.remove("F");
		LLS.remove(2);
		System.out.println("Текущий размер: " + LLS.size());
		System.out.println("Содержимое: " + LLS);
		LLS.removeFirst();
		LLS.removeLast();
		System.out.println("Текущий размер: " + LLS.size());
		System.out.println("Содержимое: " + LLS);
		String L = LLS.get(2);
		LLS.set(2, L + "!");
		System.out.println("Содержимое: " + LLS);
		// HashSet
		System.out.println("");
		System.out.println("Хеш-таблица:");
		HashSet<String> HSS = new HashSet<String>();
		HSS.add("Бета");
		HSS.add("Альфа");
		HSS.add("Эта");
		HSS.add("Гамма");
		HSS.add("Эпсилон");
		HSS.add("Омега");
		System.out.println("Текущий размер: " + HSS.size());
		System.out.println("Содержимое: " + HSS);
		// LinkedHashSet
		System.out.println("");
		System.out.println("Связанная хеш-таблица:");
		LinkedHashSet<String> LHSS = new LinkedHashSet<String>();
		LHSS.add("Бета");
		LHSS.add("Альфа");
		LHSS.add("Эта");
		LHSS.add("Гамма");
		LHSS.add("Эпсилон");
		LHSS.add("Омега");
		System.out.println("Текущий размер: " + LHSS.size());
		System.out.println("Содержимое: " + LHSS);		
		// TreeSet
		System.out.println("");
		System.out.println("Дерево:");
		TreeSet<String> TSS = new TreeSet<String>();
		TSS.add("Бета");
		TSS.add("Альфа");
		TSS.add("Эта");
		TSS.add("Гамма");
		TSS.add("Эпсилон");
		TSS.add("Омега");
		System.out.println("Текущий размер: " + TSS.size());
		System.out.println("Содержимое: " + TSS);				
		// PriorityQueue
		System.out.println("");
		System.out.println("Очередь по приоритетам:");		
		PriorityQueue<String> PQS = new PriorityQueue<String>(ALS);
		System.out.println("Текущий размер: " + PQS.size());
		System.out.println("Содержимое: " + PQS);		
		PQS.offer("G");
		PQS.offer("A");
		PQS.offer("F");
		System.out.println("Текущий размер: " + PQS.size());
		System.out.println("Содержимое: " + PQS);		
		L = PQS.peek();
		System.out.println("Элемент: " + L);		
		System.out.println("Текущий размер: " + PQS.size());
		System.out.println("Содержимое: " + PQS);		
		L = PQS.poll();		
		System.out.println("Элемент: " + L);		
		System.out.println("Текущий размер: " + PQS.size());
		System.out.println("Содержимое: " + PQS);		
		// ArrayDeque
		System.out.println("");
		System.out.println("Двусторонняя очередь:");		
		ArrayDeque<String> ADS = new ArrayDeque<String>(PQS);
		System.out.println("Текущий размер: " + ADS.size());
		System.out.println("Содержимое: " + ADS);		
		ADS.push("H");
		ADS.push("A");
		ADS.push("I");
		System.out.println("Текущий размер: " + ADS.size());
		System.out.println("Содержимое: " + ADS);		
		L = ADS.pop();
		System.out.println("Элемент: " + L);		
		System.out.println("Текущий размер: " + ADS.size());
		System.out.println("Содержимое: " + ADS);		
		L = ADS.pop();		
		System.out.println("Элемент: " + L);		
		System.out.println("Текущий размер: " + ADS.size());
		System.out.println("Содержимое: " + ADS);		
		// HashMap
		System.out.println("");
		System.out.println("Хеш-отображение:");	
		HashMap<String, Integer> HMSI = new HashMap<String, Integer>();
		HMSI.put("Бета", 21);
		HMSI.put("Альфа", 18);
		HMSI.put("Эта", 13);
		HMSI.put("Гамма", 34);
		HMSI.put("Дамма", 3);
		HMSI.put("Эпсилон", 8);
		HMSI.put("Омега", 6);
		HMSI.put("Амега", 16);
		Set<Map.Entry<String, Integer>> SHM = HMSI.entrySet();
		System.out.println("Текущий размер: " + HMSI.size());
		System.out.println("Содержимое: ");		
		for (Map.Entry<String, Integer> me : SHM) System.out.println(me.getKey() + ": " + me.getValue());
		i = HMSI.get("Эта");
		HMSI.put("Эта", i * 2);
		System.out.println("Новое значение для Эта: " + HMSI.get("Эта"));	
		// TreeMap
		System.out.println("");
		System.out.println("Дерево отображений:");	
		TreeMap<String, Integer> TMSI = new TreeMap<String, Integer>(HMSI);
		Set<Map.Entry<String, Integer>> STM = TMSI.entrySet();
		System.out.println("Текущий размер: " + TMSI.size());
		System.out.println("Содержимое: ");		
		for (Map.Entry<String, Integer> me : STM) System.out.println(me.getKey() + ": " + me.getValue());
		// ITERATOR
		System.out.println("");
		System.out.println("Итератор в списочном массиве:");		
		ALS.remove(1);
		ALS.add("F");
		ALS.add("A");
		System.out.println("Текущий размер: " + ALS.size());
		System.out.println("Содержимое: " + ALS);
		Iterator<String> itr = ALS.iterator();
		while (itr.hasNext()) System.out.print(itr.next() + ", ");
		System.out.println();
		ListIterator<String> litr = ALS.listIterator();
		while (litr.hasNext()) {
			String elm = litr.next();
			litr.set(elm + "+");
		}
		itr = ALS.iterator();
		System.out.println("Измененное содержимое:");
		while (itr.hasNext()) System.out.print(itr.next() + ", ");
		System.out.println();
		System.out.println("Измененное содержимое в обратном порядке:");
		while (litr.hasPrevious()) System.out.print(litr.previous() + ", ");
		System.out.println();
		System.out.println("Альтернатива for each содержимое:");
		for (String elm : ALS) System.out.print(elm + ", ");
		System.out.println();
		// SPLITERATOR
		System.out.println("");
		System.out.println("Итератор-разделитель в списочном массиве:");		
		Spliterator<String> splitr = ALS.spliterator();
		System.out.println("Текущий размер: " + ALS.size());
		System.out.println("Содержимое: ");
		while (splitr.tryAdvance((n) -> System.out.print(n + ", ")));
		System.out.println();	
		ArrayList<String> ALSD = new ArrayList<String>();
		splitr = ALS.spliterator();
		while (splitr.tryAdvance((n) -> ALSD.add(n + n.substring(0, 1))));
		System.out.println("Содержимое второго массива: ");
		splitr = ALSD.spliterator();
		splitr.forEachRemaining((n) -> System.out.print(n + ", "));
		System.out.println();
		// COMPARATOR
		System.out.println("");
		System.out.println("Компаратор в дереве отображений:");		
		SComp DSComp = new SComp();
		Comparator<String> DTComp = DSComp.thenComparing(new TComp());
		TreeMap<String, Integer> TMSIC = new TreeMap<String, Integer>(DTComp);
		TMSIC.putAll(TMSI);
		Set<Map.Entry<String, Integer>> STMC = TMSIC.entrySet();
		System.out.println("Текущий размер: " + TMSIC.size());
		System.out.println("Содержимое: ");		
		for (Map.Entry<String, Integer> me : STMC) System.out.println(me.getKey() + ": " + me.getValue());
		// АЛГОРИТМЫ КОЛЛЕКЦИЙ
		System.out.println("");
		System.out.println("Алгоритмы в списке:");		
		LLS.add("C");
		LLS.add("A");
		LLS.add("E");
		LLS.add("B");
		LLS.add("D");
		LLS.add("F");
		System.out.println("Текущий размер: " + LLS.size());
		System.out.println("Содержимое: " + LLS);				
		Comparator<String> LSComp = Collections.reverseOrder();
		Collections.sort(LLS, LSComp);
		System.out.println("Текущий размер: " + LLS.size());
		System.out.println("Содержимое: " + LLS);				
		Collections.shuffle(LLS);
		System.out.println("Текущий размер: " + LLS.size());
		System.out.println("Содержимое: " + LLS);				
		Collections.rotate(LLS, 2);
		System.out.println("Текущий размер: " + LLS.size());
		System.out.println("Содержимое: " + LLS);				
		System.out.println("Минимум: " + Collections.min(LLS));				
		System.out.println("Максимум: " + Collections.max(LLS));
		// АЛГОРИТМЫ МАССИВОВ
		System.out.println("");
		System.out.println("Алгоритмы в массиве:");		
		int DA[] = new int[10];
		for (i = 0; i < DA.length; i++)
			DA[i] = (int) (Math.random() * 100);
		System.out.println("Начальный массив:");			
		Display(DA);
		Arrays.sort(DA);
		System.out.println("Остортированный массив:");
		Display(DA);
		Arrays.fill(DA, 3, 5, 77);
		System.out.println("Заполненный массив:");
		Display(DA);
		Arrays.sort(DA);
		System.out.println("Повторно остортированный массив:");
		Display(DA);
		System.out.println("Значение 77 находится на позиции " + Arrays.binarySearch(DA, 77));
	}
	// МЕТОДЫ
	static int Func (int j) {
		if (j == 0) return 0;
		else return j+=Func(--j);
	}
	static int Func (int j, String str) {
		System.out.println(str + " для j = " + j);
		if (j == 0) return 0;
		else return j+=Func(--j, str);	
	}
	static void Display (int da[]) {
		int i;
		for (i = 0; i < da.length; i++)
			System.out.print(da[i] + ", ");
		System.out.println();
	}
	// ИСКЛЮЧЕНИЯ
	static void Exc (int s) {
		int i = 5;
		int ia[] = {1, 2, 3, 4, 5};
		try {
			i = i / s;
			i = ia[s];
			System.out.println("Значение: " + i);
			if (i == 4) throw new ArithmeticException("Сгенерированное исключение.");
			if (i == 2) Exc1();
			if (i == 5) Exc2();
		}
		catch (ArithmeticException e) {
			System.out.println("Деление на нуль.");
			System.out.println("исключение: " + e);
			i = 5;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("За пределы массива.");
			System.out.println("исключение: " + e);
			i = 5;
		}
		catch (ExcClass e) {
			System.out.println("Новое исключение.");
			System.out.println(e);
			i = 5;			
		}
		finally {
			System.out.println("Заключительная часть обработки.");
		}
	}
	static void Exc1 () throws ArithmeticException {
		int i = (1 / 0);
		return;
	}
	static void Exc2 () throws ExcClass {
		throw new ExcClass(1);
	}
}
// АБСТРАКТЫЕ КЛАССЫ И ИНТЕРФЕЙСЫ
interface getNumber {
	void setNumber(int CInt);
	void setString(String CStr);
}
abstract class NoneClass implements getNumber {
	abstract void getInfo();
}
// КЛАССЫ
class FirstClass extends NoneClass {
	int FCInt1;
	String FCStr1;
	FirstClass () {
		FCInt1 = 0;
		FCStr1 = "";
	}
	FirstClass (int CInt) {
		FCInt1 = CInt;
		FCStr1 = "";		
	}
	FirstClass (int CInt, String CStr) {
		FCInt1 = CInt;
		FCStr1 = CStr;		
	}
	void getInfo () {
		System.out.println("Данные класса: " + FCStr1 + " с номером " + FCInt1);
	}
	public void setNumber(int CInt) {
		FCInt1 = CInt;
	}
	public void setString(String CStr) {
		FCStr1 = CStr;
	}
}
class SecondClass extends FirstClass {
	int SCInt1;
	SecondClass () {
		super();
		SCInt1 = 0;
	}
	SecondClass (int CInt) {
		super(CInt);
		FCStr1 = "";		
		SCInt1 = 0;
	}
	SecondClass (int CInt, String CStr) {
		super(CInt, CStr);
		SCInt1 = 0;	
	}
	SecondClass (int CInt, String CStr, int CInt2) {
		super(CInt, CStr);
		SCInt1 = CInt2;	
	}
	void getInfo (String FCStr1) {
		System.out.println("Данные класса с " + FCStr1 + ": " + this.FCStr1 + " с номерами " + FCInt1 + ", " + SCInt1); // применение this
	}
}
class ThirdClass extends SecondClass {
	int SCInt1;
	ThirdClass (int CInt, String CStr, int CInt2) {
		super(CInt, CStr, CInt2);
		SCInt1 = super.SCInt1 * 2;	
	}
	void getInfo (String FCStr1) {
		System.out.println("Данные класса с " + FCStr1 + ": " + this.FCStr1 + " с номерами " + FCInt1 + ", " + super.SCInt1 + ", " + SCInt1); // применение this
	}
}
// ИСКЛЮЧЕНИЯ
class ExcClass extends Exception {
	private int ECInt;
	ExcClass(int i) {
		ECInt = i;
	}
	public String toString() {
		return ("Исключение: " + ECInt);
	}
}
// COMPARATOR
class SComp implements Comparator<String> {
	public int compare(String astr, String bstr) {
		String a = astr.substring(1, astr.length() - 1), b = bstr.substring(1, bstr.length() - 1);
		return a.compareTo(b);
	}
}
class TComp implements Comparator<String> {
	public int compare(String astr, String bstr) {
		String a = astr, b = bstr;
		return b.compareTo(a);
	}
}