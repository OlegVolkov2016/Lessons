package lesson1;

import java.util.*;

class Course {
	public static void main(String arg[]) {
		// ����������
		byte b = 1;
		short s = 2;
		int i = 3;
		long l = 4;
		// float f = 5.1; �� �������������
		float f = (float) 5.1;
		double d = 6.2;
		char c = 'a';
		boolean o = true;
		System.out.println("b = " + b + "\ns = " + s + "\ni = " + i + "\nl = " + l + "\n\tf = " + f + "\n\td = " + d + "\nc = " + c + "\no = " + o + "\n");
		// ���������
		final int iconst = 7;
		// iconst = i; �� �������������
		System.out.println("iconst = " + iconst + "\n");
		// �������������� �����
		// i = l; �� �������������
		i = (int) l;
		l = b;
		s = (short) c;
		System.out.println("b = " + b + "\ns = " + s + "\ni = " + i + "\nl = " + l + "\n\tf = " + f + "\n\td = " + d + "\nc = " + c + "\no = " + o + "\n");
		// ��������
		s = 2;
		i = 3;
		l = 4;
		System.out.println("��������������:\n" + 
			i + " + " + s + " = " + (i + s) + "\n" + 
			i + " - " + s + " = " + (i - s) + "\n" + 
			i + " * " + s + " = " + (i * s) + "\n" + 
			i + " / " + s + " = " + (i / s) + "\n" + // �������� int
			i + " / " + s + " = " + ( (float) i / s) + "\n" + // �������� float
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
		System.out.println("���������:\n" + 
			i + " == " + s + " = " + (i == s) + "\n" + 
			i + " != " + s + " = " + (i != s) + "\n" + 
			i + " > " + s + " = " + (i > s) + "\n" + 
			i + " < " + s + " = " + (i < s) + "\n" + 
			i + " >= " + s + " = " + (i >= s) + "\n" + 
			i + " <= " + s + " = " + (i <= s) + "\n" + 
			"");
		System.out.println("����������:\n" + 
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
		System.out.println("�������:\n" + 
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
		// ������� � ������
		System.out.println("�������: " + c + ", \\142 - " + '\142' + ", \\u00FA - " + '\u00FA' + ", \', \", \\.");
		String str = "�������� ������.";
		System.out.println("������: " + str + "\n����� ������: " + str.length() + 
		"\n������ � 3: " + str.charAt(3) + "\n���������: " + str.substring(3, 6) + 
		"\n��������� � �������� ������.: " + str.equals("�������� ������.") + ", " + str.equalsIgnoreCase("�������� ������.") + 
		"\n��������� � �������� ������: " + str.compareTo("�������� ������") + ", " + str.compareToIgnoreCase("�������� ������") +  
		"\n�������������� ��������: " + str.toUpperCase() + ", " + str.toLowerCase() + 
		"\n����������: " + str.concat(" ���!") + 
		"\n������: " + str.replace('�', '�') +
		"\n��������� �: " + str.indexOf('�') + ", " + str.lastIndexOf('�') + 
		"\n��������� ��: " + str.indexOf("��") + ", " + str.lastIndexOf("��") + 
		"\n");
		//���������
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
			// System.out.println("12"); �����������
		}
		System.out.println("�������� i ����� ������: " + i);
		// ������
		System.out.println("\n������� ��� 1: " + Func(1) + "\n������� ��� 3: " + Func(3) + "\n");
		System.out.println("\n������� ��� 1: " + Func(1, "������") + "\n������� ��� 3: " + Func(3, "����") + "\n");
		// ������
		FirstClass FC = new FirstClass();
		FC.FCInt1 = 10;
		FC.FCStr1 = "������ �����";
		FC.getInfo();
		SecondClass SC;
		SC = new SecondClass();
		SC.FCInt1 = 20;
		SC.FCStr1 = "������ ��������";
		SC.SCInt1 = 15;
		SC.getInfo();
		SC.getInfo("�����������");
		FC = SC;
		FC.FCStr1 = "������ �������� ������";
		FC.getInfo();
		FC.setNumber(30);
		FC.setString("������ ����������");
		FC.getInfo();
		SC = new SecondClass();
		SC.setNumber(40);
		SC.setString("������ ����������");
		SC.SCInt1 = 20;
		SC.getInfo("������������� ����������");
		ThirdClass TC = new ThirdClass (50, "������ super", 20);
		TC.getInfo("����������� ������������");
		System.out.println("");
		// ����������
		Exc(0);
		Exc(2);
		Exc(10);
		Exc(3);
		Exc(1);
		Exc(4);
		System.out.println("");
		ArithmeticException eA = new ArithmeticException("����������.");
		eA.initCause(new NullPointerException("�������."));
		try {
			throw eA;
		}
		catch (ArithmeticException e) {
			System.out.println(e);
			System.out.println(e.getCause());
		}
		// ���������
		// ArrayList
		System.out.println("");
		System.out.println("��������� ������:");
		ArrayList<String> ALS = new ArrayList<String>();
		System.out.println("��������� ������: " + ALS.size());
		ALS.add("C");
		ALS.add("A");
		ALS.add("E");
		ALS.add("B");
		ALS.add("D");
		ALS.add("F");
		ALS.add(1, "A1");
		System.out.println("������� ������: " + ALS.size());
		System.out.println("����������: " + ALS);
		ALS.remove("F");
		ALS.remove(2);
		System.out.println("������� ������: " + ALS.size());
		System.out.println("����������: " + ALS);
		String AL[] = new String[ALS.size()];
		ALS.toArray(AL);
		System.out.print("���������� �������: ");
		String A = "";
		for (i=0; i < AL.length; i++) {
			System.out.print(AL[i]);
			A += AL[i];
		}
		System.out.println("");
		System.out.println("� ������: " + A);
		// LinkedList
		System.out.println("");
		System.out.println("��������� ������:");
		LinkedList<String> LLS = new LinkedList<String>();
		System.out.println("��������� ������: " + LLS.size());
		LLS.add("C");
		LLS.add("A");
		LLS.add("E");
		LLS.add("B");
		LLS.add("D");
		LLS.add("F");
		LLS.add(1, "A1");
		System.out.println("������� ������: " + LLS.size());
		System.out.println("����������: " + LLS);
		LLS.remove("F");
		LLS.remove(2);
		System.out.println("������� ������: " + LLS.size());
		System.out.println("����������: " + LLS);
		LLS.removeFirst();
		LLS.removeLast();
		System.out.println("������� ������: " + LLS.size());
		System.out.println("����������: " + LLS);
		String L = LLS.get(2);
		LLS.set(2, L + "!");
		System.out.println("����������: " + LLS);
		// HashSet
		System.out.println("");
		System.out.println("���-�������:");
		HashSet<String> HSS = new HashSet<String>();
		HSS.add("����");
		HSS.add("�����");
		HSS.add("���");
		HSS.add("�����");
		HSS.add("�������");
		HSS.add("�����");
		System.out.println("������� ������: " + HSS.size());
		System.out.println("����������: " + HSS);
		// LinkedHashSet
		System.out.println("");
		System.out.println("��������� ���-�������:");
		LinkedHashSet<String> LHSS = new LinkedHashSet<String>();
		LHSS.add("����");
		LHSS.add("�����");
		LHSS.add("���");
		LHSS.add("�����");
		LHSS.add("�������");
		LHSS.add("�����");
		System.out.println("������� ������: " + LHSS.size());
		System.out.println("����������: " + LHSS);		
		// TreeSet
		System.out.println("");
		System.out.println("������:");
		TreeSet<String> TSS = new TreeSet<String>();
		TSS.add("����");
		TSS.add("�����");
		TSS.add("���");
		TSS.add("�����");
		TSS.add("�������");
		TSS.add("�����");
		System.out.println("������� ������: " + TSS.size());
		System.out.println("����������: " + TSS);				
		// PriorityQueue
		System.out.println("");
		System.out.println("������� �� �����������:");		
		PriorityQueue<String> PQS = new PriorityQueue<String>(ALS);
		System.out.println("������� ������: " + PQS.size());
		System.out.println("����������: " + PQS);		
		PQS.offer("G");
		PQS.offer("A");
		PQS.offer("F");
		System.out.println("������� ������: " + PQS.size());
		System.out.println("����������: " + PQS);		
		L = PQS.peek();
		System.out.println("�������: " + L);		
		System.out.println("������� ������: " + PQS.size());
		System.out.println("����������: " + PQS);		
		L = PQS.poll();		
		System.out.println("�������: " + L);		
		System.out.println("������� ������: " + PQS.size());
		System.out.println("����������: " + PQS);		
		// ArrayDeque
		System.out.println("");
		System.out.println("������������ �������:");		
		ArrayDeque<String> ADS = new ArrayDeque<String>(PQS);
		System.out.println("������� ������: " + ADS.size());
		System.out.println("����������: " + ADS);		
		ADS.push("H");
		ADS.push("A");
		ADS.push("I");
		System.out.println("������� ������: " + ADS.size());
		System.out.println("����������: " + ADS);		
		L = ADS.pop();
		System.out.println("�������: " + L);		
		System.out.println("������� ������: " + ADS.size());
		System.out.println("����������: " + ADS);		
		L = ADS.pop();		
		System.out.println("�������: " + L);		
		System.out.println("������� ������: " + ADS.size());
		System.out.println("����������: " + ADS);		
		// HashMap
		System.out.println("");
		System.out.println("���-�����������:");	
		HashMap<String, Integer> HMSI = new HashMap<String, Integer>();
		HMSI.put("����", 21);
		HMSI.put("�����", 18);
		HMSI.put("���", 13);
		HMSI.put("�����", 34);
		HMSI.put("�����", 3);
		HMSI.put("�������", 8);
		HMSI.put("�����", 6);
		HMSI.put("�����", 16);
		Set<Map.Entry<String, Integer>> SHM = HMSI.entrySet();
		System.out.println("������� ������: " + HMSI.size());
		System.out.println("����������: ");		
		for (Map.Entry<String, Integer> me : SHM) System.out.println(me.getKey() + ": " + me.getValue());
		i = HMSI.get("���");
		HMSI.put("���", i * 2);
		System.out.println("����� �������� ��� ���: " + HMSI.get("���"));	
		// TreeMap
		System.out.println("");
		System.out.println("������ �����������:");	
		TreeMap<String, Integer> TMSI = new TreeMap<String, Integer>(HMSI);
		Set<Map.Entry<String, Integer>> STM = TMSI.entrySet();
		System.out.println("������� ������: " + TMSI.size());
		System.out.println("����������: ");		
		for (Map.Entry<String, Integer> me : STM) System.out.println(me.getKey() + ": " + me.getValue());
		// ITERATOR
		System.out.println("");
		System.out.println("�������� � ��������� �������:");		
		ALS.remove(1);
		ALS.add("F");
		ALS.add("A");
		System.out.println("������� ������: " + ALS.size());
		System.out.println("����������: " + ALS);
		Iterator<String> itr = ALS.iterator();
		while (itr.hasNext()) System.out.print(itr.next() + ", ");
		System.out.println();
		ListIterator<String> litr = ALS.listIterator();
		while (litr.hasNext()) {
			String elm = litr.next();
			litr.set(elm + "+");
		}
		itr = ALS.iterator();
		System.out.println("���������� ����������:");
		while (itr.hasNext()) System.out.print(itr.next() + ", ");
		System.out.println();
		System.out.println("���������� ���������� � �������� �������:");
		while (litr.hasPrevious()) System.out.print(litr.previous() + ", ");
		System.out.println();
		System.out.println("������������ for each ����������:");
		for (String elm : ALS) System.out.print(elm + ", ");
		System.out.println();
		// SPLITERATOR
		System.out.println("");
		System.out.println("��������-����������� � ��������� �������:");		
		Spliterator<String> splitr = ALS.spliterator();
		System.out.println("������� ������: " + ALS.size());
		System.out.println("����������: ");
		while (splitr.tryAdvance((n) -> System.out.print(n + ", ")));
		System.out.println();	
		ArrayList<String> ALSD = new ArrayList<String>();
		splitr = ALS.spliterator();
		while (splitr.tryAdvance((n) -> ALSD.add(n + n.substring(0, 1))));
		System.out.println("���������� ������� �������: ");
		splitr = ALSD.spliterator();
		splitr.forEachRemaining((n) -> System.out.print(n + ", "));
		System.out.println();
		// COMPARATOR
		System.out.println("");
		System.out.println("���������� � ������ �����������:");		
		SComp DSComp = new SComp();
		Comparator<String> DTComp = DSComp.thenComparing(new TComp());
		TreeMap<String, Integer> TMSIC = new TreeMap<String, Integer>(DTComp);
		TMSIC.putAll(TMSI);
		Set<Map.Entry<String, Integer>> STMC = TMSIC.entrySet();
		System.out.println("������� ������: " + TMSIC.size());
		System.out.println("����������: ");		
		for (Map.Entry<String, Integer> me : STMC) System.out.println(me.getKey() + ": " + me.getValue());
		// ��������� ���������
		System.out.println("");
		System.out.println("��������� � ������:");		
		LLS.add("C");
		LLS.add("A");
		LLS.add("E");
		LLS.add("B");
		LLS.add("D");
		LLS.add("F");
		System.out.println("������� ������: " + LLS.size());
		System.out.println("����������: " + LLS);				
		Comparator<String> LSComp = Collections.reverseOrder();
		Collections.sort(LLS, LSComp);
		System.out.println("������� ������: " + LLS.size());
		System.out.println("����������: " + LLS);				
		Collections.shuffle(LLS);
		System.out.println("������� ������: " + LLS.size());
		System.out.println("����������: " + LLS);				
		Collections.rotate(LLS, 2);
		System.out.println("������� ������: " + LLS.size());
		System.out.println("����������: " + LLS);				
		System.out.println("�������: " + Collections.min(LLS));				
		System.out.println("��������: " + Collections.max(LLS));
		// ��������� ��������
		System.out.println("");
		System.out.println("��������� � �������:");		
		int DA[] = new int[10];
		for (i = 0; i < DA.length; i++)
			DA[i] = (int) (Math.random() * 100);
		System.out.println("��������� ������:");			
		Display(DA);
		Arrays.sort(DA);
		System.out.println("��������������� ������:");
		Display(DA);
		Arrays.fill(DA, 3, 5, 77);
		System.out.println("����������� ������:");
		Display(DA);
		Arrays.sort(DA);
		System.out.println("�������� ��������������� ������:");
		Display(DA);
		System.out.println("�������� 77 ��������� �� ������� " + Arrays.binarySearch(DA, 77));
	}
	// ������
	static int Func (int j) {
		if (j == 0) return 0;
		else return j+=Func(--j);
	}
	static int Func (int j, String str) {
		System.out.println(str + " ��� j = " + j);
		if (j == 0) return 0;
		else return j+=Func(--j, str);	
	}
	static void Display (int da[]) {
		int i;
		for (i = 0; i < da.length; i++)
			System.out.print(da[i] + ", ");
		System.out.println();
	}
	// ����������
	static void Exc (int s) {
		int i = 5;
		int ia[] = {1, 2, 3, 4, 5};
		try {
			i = i / s;
			i = ia[s];
			System.out.println("��������: " + i);
			if (i == 4) throw new ArithmeticException("��������������� ����������.");
			if (i == 2) Exc1();
			if (i == 5) Exc2();
		}
		catch (ArithmeticException e) {
			System.out.println("������� �� ����.");
			System.out.println("����������: " + e);
			i = 5;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�� ������� �������.");
			System.out.println("����������: " + e);
			i = 5;
		}
		catch (ExcClass e) {
			System.out.println("����� ����������.");
			System.out.println(e);
			i = 5;			
		}
		finally {
			System.out.println("�������������� ����� ���������.");
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
// ���������� ������ � ����������
interface getNumber {
	void setNumber(int CInt);
	void setString(String CStr);
}
abstract class NoneClass implements getNumber {
	abstract void getInfo();
}
// ������
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
		System.out.println("������ ������: " + FCStr1 + " � ������� " + FCInt1);
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
		System.out.println("������ ������ � " + FCStr1 + ": " + this.FCStr1 + " � �������� " + FCInt1 + ", " + SCInt1); // ���������� this
	}
}
class ThirdClass extends SecondClass {
	int SCInt1;
	ThirdClass (int CInt, String CStr, int CInt2) {
		super(CInt, CStr, CInt2);
		SCInt1 = super.SCInt1 * 2;	
	}
	void getInfo (String FCStr1) {
		System.out.println("������ ������ � " + FCStr1 + ": " + this.FCStr1 + " � �������� " + FCInt1 + ", " + super.SCInt1 + ", " + SCInt1); // ���������� this
	}
}
// ����������
class ExcClass extends Exception {
	private int ECInt;
	ExcClass(int i) {
		ECInt = i;
	}
	public String toString() {
		return ("����������: " + ECInt);
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