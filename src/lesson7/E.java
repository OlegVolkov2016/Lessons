package lesson7;

interface E {
	interface G {
		void f();
	}

	// ??? припустиме оголошення public:
	public interface H {
		void f();
	}

	void g();
	// Не може бути private в середині інтерфейсу:
	// ! private interface I {}
}
