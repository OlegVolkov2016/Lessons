package lesson7;

interface E {
	interface G {
		void f();
	}

	// ??? ���������� ���������� public:
	public interface H {
		void f();
	}

	void g();
	// �� ���� ���� private � ������� ����������:
	// ! private interface I {}
}
