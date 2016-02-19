package electricity;


import java.util.ArrayList;
import java.util.Calendar;

public class Electricity extends Client {
	
	// Main electricity fields
	// discount is price of kWt
	private int clientsAmount, valuesAmount, paymentsAmount, controlsAmount, removesAmount;
	private ArrayList<Client> clients;
	private ArrayList<Value> values, payments;
	private ArrayList<Control> controls;
	private ArrayList<Remove> removes;
	// count is first init count
	private int count = 2;

	public Electricity() {
		super();
		// TODO Auto-generated constructor stub
		clients = new ArrayList<Client>();
		values = new ArrayList<Value>();
		payments = new ArrayList<Value>();
		controls = new ArrayList<Control>();
		removes = new ArrayList<Remove>();
	}

	public Electricity(String name, int discount, boolean isEmpty) {
		this();
		// TODO Auto-generated constructor stub
		setName(name);
		setDiscount(discount);	
		if (!isEmpty)
			firstInit();
	}
	
	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		super.set(obj);
		clientsAmount = ((Electricity) obj).getClientsAmount();
		valuesAmount = ((Electricity) obj).getValuesAmount();
		paymentsAmount = ((Electricity) obj).getPaymentsAmount();
		controlsAmount = ((Electricity) obj).getControlsAmount();
		removesAmount = ((Electricity) obj).getRemovesAmount();
		clients = ((Electricity) obj).getClients();
		values = ((Electricity) obj).getValues();
		payments = ((Electricity) obj).getPayments();
		controls = ((Electricity) obj).getControls();
		removes = ((Electricity) obj).getRemoves();
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		super.firstInit();
		Calendar date = Calendar.getInstance();
		setClientsAmount(count);
		for (int i = 0; i < getClientsAmount(); i++) {
			clients.add(new Client("Client "+ (char) ('A'+i),(i+1)*5));
		}
		setValuesAmount(count*2);
		for (int i = 0; i < getClientsAmount(); i++) {
			date.set(Calendar.DATE,date.get(Calendar.DATE)-150);
			values.add(new Value(clients.get(i).getName(), (i+1)*12, date));
			date.set(Calendar.DATE,date.get(Calendar.DATE)+50);
			values.add(new Value(clients.get(i).getName(), (i+1)*24, date));
		}
		setPaymentsAmount(count*2+1);
		date = Calendar.getInstance();
		for (int i = 0; i < getClientsAmount(); i++) {
			date.set(Calendar.DATE,date.get(Calendar.DATE)-140);
			payments.add(new Value(clients.get(i).getName(), (i+1)*2*getDiscount(), date));
			date.set(Calendar.DATE,date.get(Calendar.DATE)+50);
			payments.add(new Value(clients.get(i).getName(), (i+1)*8*getDiscount(), date));
		}
		date.set(Calendar.DATE,date.get(Calendar.DATE)+10);
		payments.add(new Value(clients.get(getClientsAmount()-1).getName(), 10*getDiscount(), date));
		setControlsAmount(count*2);
		date = Calendar.getInstance();
		for (int i = 0; i < getClientsAmount(); i++) {
			date.set(Calendar.DATE,date.get(Calendar.DATE)-10);
			controls.add(new Control(clients.get(i).getName(), checkDebt(clients.get(i).getName(),date),date,true));
			date.set(Calendar.DATE,date.get(Calendar.DATE)+5);
			controls.add(new Control(clients.get(i).getName(), (i+1)*26, date,false));
		}
		setRemovesAmount(count);
		date = Calendar.getInstance();
		for (int i = 0; i < getRemovesAmount(); i++) {
			date.set(Calendar.DATE,date.get(Calendar.DATE)-1-i);
			if ((i % 2) == 0)
				removes.add(new Remove(clients.get(i).getName(),checkDebt(clients.get(i).getName(),date),date,true,false));
			else
				removes.add(new Remove(clients.get(i).getName(),(i+1)*26,date,false,true));
		}
	}
	
	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.println("Name of "+type+": "+getName()+", price of kWt is "+getDiscount()+"$.");
//		System.out.println("List of values: ");
//		for (int i = 0; i < getValuesAmount(); i++) {
//			values.get(i).print("value");
//		}
//		System.out.println("List of payments: ");
//		for (int i = 0; i < getPaymentsAmount(); i++) {
//			payments.get(i).print("payment");
//		}
//		System.out.println("List of controls: ");
//		for (int i = 0; i < getControlsAmount(); i++) {
//			controls.get(i).print("control");
//		}
//		System.out.println("List of removes: ");
//		for (int i = 0; i < getRemovesAmount(); i++) {
//			removes.get(i).print("remove");
//		}		
	}
	
	@Override
	public void edit(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		int discount;
		boolean isChange = false;
		name = inputName(type,isNew);
		discount = inputDiscount(type,isNew);
		if (discount != getDiscount()) isChange = true;
		setName(name);
		setDiscount(discount);
		if (isChange) {
			recalcControls();
			recalcRemoves();
		}
	}
	
	@Override
	public int inputDiscount(String type, boolean isNew) {
		// TODO Auto-generated method stub
		int discount;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Current price of kWt is "+getDiscount()+"$.");
			System.out.print("Enter new price in $: ");
			discount = makeIntChoise(0,Integer.MAX_VALUE);
			if (discount == -1) 
				if (!isNew) discount = getDiscount();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return discount;
	}
	
	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		int choise;
		System.out.println("\n<<<Menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - Clients menu, 2 - Values menu, 3 - Payments menu, 4 - Controls menu, 5 - Removes menu, 6 - View info, 7 - Edit info, 0 - Exit): ");
		choise = makeIntChoise(0,7);
		if (choise == 1) while (clientsMenu(type));
		if (choise == 2) while (valuesMenu(type));
		if (choise == 3) while (paymentsMenu(type));
		if (choise == 4) while (controlsMenu(type));
		if (choise == 5) while (removesMenu(type));
		if (choise == 6) print(type);
		if (choise == 7) edit(type,false);
		if (choise == 0) {
			System.out.println("Thank you. Good bye...");
			return false;
		}
		return true;
	}
	
	// Electricity clients menu
	public boolean clientsMenu(String type) {
		int choise, index;
		String name;
		System.out.println("\n<<<Clients menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of clients, 2 - New client, 3 - Edit client, 4 - Delete client, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) printClients();
		if (choise == 2) {
			clients.add(new Client());
			clients.get(getClientsAmount()).edit("client",true);
			if (searchClients(clients.get(getClientsAmount()).getName()) < 0)
				setClientsAmount(getClientsAmount()+1);
			else {
				System.out.println("Client is already exist.");
				clients.remove(getClientsAmount());
			}
		}
		if (choise == 3) {
			System.out.print("Enter name of client to edit (only letters and .): ");
			name = makeNameChoise();
			if (!name.equals("")) {
				index = searchClients(name);
				if (index >= 0) {
					clients.get(index).edit("client",false);
					if (!clients.get(index).getName().equals(name))
						changeClient(name,clients.get(index).getName(),false);
				}
				else System.out.println("Client is not exist.");
			}
		}
		if (choise == 4) {
			System.out.print("Enter name of client to remove (only letters and .): ");
			name = makeNameChoise();
			if (!name.equals("")) {
				index = searchClients(name);
				if (index >= 0) {
					clients.remove(index);
					setClientsAmount(getClientsAmount()-1);
					changeClient(name,"",true);
				}
				else System.out.println("Client is not exist.");
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// List of clients
	public void printClients() {
		System.out.println("List of clients: ");
		for (int i = 0; i < getClientsAmount(); i++) {
			System.out.print("# "+i+" ");
			clients.get(i).print("");			
		}
	}
	
	// Search clients by name
	public int searchClients(String name) {
		for (int i = 0; i < getClientsAmount(); i++) {
			if (clients.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}
	
	// Change or delete client name from values, payments and controls list
	public void changeClient(String name, String client, boolean isDel) {
		for (int i = 0; i < getValuesAmount(); i++) {
			if (values.get(i).getName().equals(name))
				if (isDel) {
					values.remove(i);
					i--;
					setValuesAmount(getValuesAmount()-1);
				}
				else values.get(i).setName(client);
		}
		for (int i = 0; i < getPaymentsAmount(); i++) {
			if (payments.get(i).getName().equals(name))
				if (isDel) {
					payments.remove(i);
					i--;
					setPaymentsAmount(getPaymentsAmount()-1);
				}
				else values.get(i).setName(client);
		}
		for (int i = 0; i < getControlsAmount(); i++) {
			if (controls.get(i).getName().equals(name))
				if (isDel) {
					controls.remove(i);
					i--;
					setControlsAmount(getControlsAmount()-1);
				}
				else controls.get(i).setName(client);
		}
		for (int i = 0; i < getRemovesAmount(); i++) {
			if (removes.get(i).getName().equals(name))
				if (isDel) {
					removes.remove(i);
					i--;
					setRemovesAmount(getRemovesAmount()-1);
				}
				else removes.get(i).setName(client);
		}
	}
	
	// Electricity values menu
	public boolean valuesMenu(String type) {
		int choise, index;
		String name;
		System.out.println("\n<<<Values menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of values, 2 - New value, 3 - Edit value, 4 - Delete value, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			System.out.print("Enter name of client to list (only letters and .): ");
			name = makeNameChoise();
			printValues(name);
		}
		if (choise == 2) {
			values.add(new Value());
			values.get(getValuesAmount()).edit("value",true);
			if (searchClients(values.get(getValuesAmount()).getName()) >= 0)
				setValuesAmount(getValuesAmount()+1);
			else {
				System.out.println("Client is not exist.");
				values.remove(getValuesAmount());
			}
		}
		if (choise == 3) {
			System.out.print("Enter index of value to edit (0 to "+(getValuesAmount()-1)+"): ");
			index = makeIntChoise(0,getValuesAmount()-1);
			if (index >= 0) {
				name = values.get(index).getName();
				values.get(index).edit("value",false);
				if (searchClients(values.get(index).getName()) < 0) {
					System.out.println("Client is not exist. Return old name.");
					values.get(index).setName(name);
				}
			}
		}
		if (choise == 4) {
			System.out.print("Enter index of value to remove (0 to "+(getValuesAmount()-1)+"): ");
			index = makeIntChoise(0,getValuesAmount()-1);
			if (index >= 0) {
				values.remove(index);
				setValuesAmount(getValuesAmount()-1);
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// Print list of values
	public void printValues(String name) {
		if (name.equals("")) 
			System.out.println("List of all values: ");
		else
			System.out.println("List of values of client "+name+": ");
		for (int i = 0; i < getValuesAmount(); i++) {
			if ((name.equals("")) || (values.get(i).getName().equals(name))) {
				System.out.print("# "+i+" ");
				values.get(i).print("value");
			}
		}
	}
	
	
	// Electricity payments menu
	public boolean paymentsMenu(String type) {
		int choise, index;
		String name;
		System.out.println("\n<<<Payments menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of payments, 2 - New payment, 3 - Edit payment, 4 - Delete payment, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			System.out.print("Enter name of client to list (only letters and .): ");
			name = makeNameChoise();
			printPayments(name);
		}
		if (choise == 2) {
			payments.add(new Value());
			payments.get(getPaymentsAmount()).edit("payment",true);
			if (searchClients(payments.get(getPaymentsAmount()).getName()) >= 0)
				setPaymentsAmount(getPaymentsAmount()+1);
			else {
				System.out.println("Client is not exist.");
				payments.remove(getPaymentsAmount());
			}
		}
		if (choise == 3) {
			System.out.print("Enter index of payment to edit (0 to "+(getPaymentsAmount()-1)+"): ");
			index = makeIntChoise(0,getPaymentsAmount()-1);
			if (index >= 0) {
				name = payments.get(index).getName();
				payments.get(index).edit("payment",false);
				if (searchClients(payments.get(index).getName()) < 0) {
					System.out.println("Client is not exist. Return old name.");
					payments.get(index).setName(name);
				}
			}
		}
		if (choise == 4) {
			System.out.print("Enter index of payment to remove (0 to "+(getPaymentsAmount()-1)+"): ");
			index = makeIntChoise(0,getPaymentsAmount()-1);
			if (index >= 0) {
				payments.remove(index);
				setPaymentsAmount(getPaymentsAmount()-1);
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// Print list of payments
	public void printPayments(String name) {
		if (name.equals("")) 
			System.out.println("List of all payments: ");
		else
			System.out.println("List of payments of client "+name+": ");
		for (int i = 0; i < getPaymentsAmount(); i++) {
			if ((name.equals("")) || (payments.get(i).getName().equals(name))) {
				System.out.print("# "+i+" ");
				payments.get(i).print("payment");
			}
		}
	}
	
	
	// Electricity controls menu
	public boolean controlsMenu(String type) {
		int choise, index;
		String name;
		recalcControls();
		System.out.println("\n<<<Controls menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of controls, 2 - New control, 3 - Edit control, 4 - Delete control, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			System.out.print("Enter name of client to list (only letters and .): ");
			name = makeNameChoise();
			printControls(name);
		}
		if (choise == 2) {
			controls.add(new Control());
			controls.get(getControlsAmount()).edit("control",true);
			if (searchClients(controls.get(getControlsAmount()).getName()) >= 0)
				setControlsAmount(getControlsAmount()+1);
			else {
				System.out.println("Client is not exist.");
				controls.remove(getControlsAmount());
			}
		}
		if (choise == 3) {
			System.out.print("Enter index of control to edit (0 to "+(getControlsAmount()-1)+"): ");
			index = makeIntChoise(0,getControlsAmount()-1);
			if (index >= 0) {
				name = controls.get(index).getName();
				controls.get(index).edit("control",false);
				if (searchClients(controls.get(index).getName()) < 0) {
					System.out.println("Client is not exist. Return old name.");
					controls.get(index).setName(name);
				}
			}
		}
		if (choise == 4) {
			System.out.print("Enter index of control to remove (0 to "+(getControlsAmount()-1)+"): ");
			index = makeIntChoise(0,getControlsAmount()-1);
			if (index >= 0) {
				controls.remove(index);
				setControlsAmount(getControlsAmount()-1);
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// Print list of controls
	public void printControls(String name) {
		if (name.equals("")) 
			System.out.println("List of all controls: ");
		else
			System.out.println("List of controls of client "+name+": ");
		for (int i = 0; i < getControlsAmount(); i++) {
			if ((name.equals("")) || (controls.get(i).getName().equals(name))) {
				System.out.print("# "+i+" ");
				controls.get(i).print("control");
			}
		}
	}
	
	
	// Electricity removes menu
	public boolean removesMenu(String type) {
		int choise, index;
		String name;
		recalcRemoves();
		System.out.println("\n<<<Removes menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of removes, 2 - New remove, 3 - Edit remove, 4 - Delete remove, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			System.out.print("Enter name of client to list (only letters and .): ");
			name = makeNameChoise();
			printRemoves(name);
		}
		if (choise == 2) {
			removes.add(new Remove());
			removes.get(getRemovesAmount()).edit("remove",true);
			if (searchClients(removes.get(getRemovesAmount()).getName()) >= 0)
				setRemovesAmount(getRemovesAmount()+1);
			else {
				System.out.println("Client is not exist.");
				removes.remove(getRemovesAmount());
			}
		}
		if (choise == 3) {
			System.out.print("Enter index of remove to edit (0 to "+(getRemovesAmount()-1)+"): ");
			index = makeIntChoise(0,getRemovesAmount()-1);
			if (index >= 0) {
				name = removes.get(index).getName();
				removes.get(index).edit("remove",false);
				if (searchClients(removes.get(index).getName()) < 0) {
					System.out.println("Client is not exist. Return old name.");
					removes.get(index).setName(name);
				}
			}
		}
		if (choise == 4) {
			System.out.print("Enter index of remove to remove (0 to "+(getRemovesAmount()-1)+"): ");
			index = makeIntChoise(0,getRemovesAmount()-1);
			if (index >= 0) {
				removes.remove(index);
				setRemovesAmount(getRemovesAmount()-1);
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// Print list of removes
	public void printRemoves(String name) {
		if (name.equals("")) 
			System.out.println("List of all removes: ");
		else
			System.out.println("List of removes of client "+name+": ");
		for (int i = 0; i < getRemovesAmount(); i++) {
			if ((name.equals("")) || (removes.get(i).getName().equals(name))) {
				System.out.print("# "+i+" ");
				removes.get(i).print("remove");
			}
		}
	}	
	
	// Recalculate controls debt values
	public void recalcControls() {
		for (int i = 0; i < getControlsAmount(); i++)
			if (controls.get(i).getDebt())
				controls.get(i).setDiscount(checkDebt(controls.get(i).getName(),controls.get(i).getDate()));
	}
	
	// Recalculate removes debt values
	public void recalcRemoves() {
		for (int i = 0; i < getRemovesAmount(); i++)
			if (removes.get(i).getDebt())
				removes.get(i).setDiscount(checkDebt(removes.get(i).getName(),removes.get(i).getDate()));
	}
	
	// Calculate debt on date
	public int checkDebt(String name, Calendar date) {
		return calcValues(name,date) - calcPayments(name,date);
	}
	
	// Calculate electricity values on date
	public int calcValues(String name, Calendar date) {
		int begin = -1, end = -1;
		for (int i = 0; i < getValuesAmount(); i++) {
			if (values.get(i).getName().equals(name)) {
				if (begin == -1) {
					begin = i;
					end = i;
				}
				if (values.get(i).getDate().before((values.get(begin).getDate())))
						begin = i;
				if (values.get(i).getDate().after((values.get(end).getDate())))
					end = i;				
			}
		}
		if (begin == -1) return 0;
		else return (values.get(end).getDiscount()-values.get(begin).getDiscount())*getDiscount()*(1-clients.get(searchClients(name)).getDiscount()/100);
	}
	
	// Calculate electricity payments on date
	public int calcPayments(String name, Calendar date) {
		int res = 0;
		for (int i = 0; i < getPaymentsAmount(); i++)
			if ((payments.get(i).getName().equals(name)) && (payments.get(i).getDate().before(date)))
				res+=payments.get(i).getDiscount();
		return res;
	}
	
	// Getters and setters
	public int getClientsAmount() {
		return clientsAmount;
	}
	
	public void setClientsAmount(int clientsAmount) {
		this.clientsAmount = clientsAmount;
	}
	
	public int getValuesAmount() {
		return valuesAmount;
	}
	
	public void setValuesAmount(int valuesAmount) {
		this.valuesAmount = valuesAmount;
	}
	
	public int getPaymentsAmount() {
		return paymentsAmount;
	}
	
	public void setPaymentsAmount(int paymentsAmount) {
		this.paymentsAmount = paymentsAmount;
	}
	
	public int getControlsAmount() {
		return controlsAmount;
	}
	
	public void setControlsAmount(int controlsAmount) {
		this.controlsAmount = controlsAmount;
	}
	
	public int getRemovesAmount() {
		return removesAmount;
	}
	
	public void setRemovesAmount(int removesAmount) {
		this.removesAmount = removesAmount;
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	public ArrayList<Value> getValues() {
		return values;
	}
	
	public void setValues(ArrayList<Value> values) {
		this.values = values;
	}
	
	public ArrayList<Value> getPayments() {
		return payments;
	}
	
	public void setPayments(ArrayList<Value> payments) {
		this.payments = payments;
	}
	
	public ArrayList<Control> getControls() {
		return controls;
	}
	
	public void setControls(ArrayList<Control> controls) {
		this.controls = controls;
	}
	
	public ArrayList<Remove> getRemoves() {
		return removes;
	}
	
	public void setRemoves(ArrayList<Remove> removes) {
		this.removes = removes;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Electricity electricity = new Electricity("Sun Electric",2,false);
		while (electricity.menu("company"));
	}

}
