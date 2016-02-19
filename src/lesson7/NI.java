package lesson7;

public class NI {
	public class BImp implements A.B {
		public void f() {
		}
	}

	class CImp implements A.C {
		public void f() {
		}
	}

	// Private-интерфейс не может быть реализован нигде,
	// кроме как внутри класса, где он был определен:
	// ! class DImp implements A.D {
	// ! public void f() {}
	// ! }
	class EImp implements E {
		public void g() {
		}
	}

	class EGImp implements E.G {
		public void f() {
		}
	}

	class EImp2 implements E {
		public void g() {
		}

		class EG implements E.G {
			public void f() {
			}
		}
	}

	public static void main(String[] args) {
		A a = new A();
		// Нет доступа к A.D:
		// ! A.D ad = a.getD();
		// He возвращает ничего, кроме A.D:
		// ! A.DImp2 di2 = a.getD();
		// Член интерфейса недоступен:
		// ! a.getD().f();
		// Только другой объект класса А может использовать getD():
		A a2 = new A();
		a2.receiveD(a.getD());
	}
}
