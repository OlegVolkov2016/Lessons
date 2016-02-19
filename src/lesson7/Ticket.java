package lesson7;

public interface Ticket {
	int pricetram = 5;
	int pricebus = 10;
	double disc = 0.5;

	void buytram(int count, boolean isdisc);
	void buybus(int count, boolean isdisc);
}
