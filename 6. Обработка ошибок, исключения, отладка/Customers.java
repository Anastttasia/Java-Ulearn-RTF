public class Main {
    public static void main(String[] args) {
		//TODO напишите консольное приложение для взаимодействия с продавцами
		CustomerStorage storage = new CustomerStorage();

		Scanner in = new Scanner(System.in);
		while (true) {
			String line = in.nextLine();
			String[] arrOfStr = line.split(" ");

			if ((arrOfStr.length != 5))
				throw new IllegalArgumentException();

			String command = arrOfStr[0];

			if (command == "add") {
				storage.addCustomer(String.format("%s  %s  %s  %s", arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4]));
			}
			else if (command == "list") {
				storage.listCustomers();
			}
			else if (command == "remove") {
				storage.removeCustomer(arrOfStr[1], arrOfStr[2]);
			}
			else if (command == "count") {
				System.out.println(storage.getCount());
			}
			else if (command == "help") {
				System.out.println("command name surname email phone");
			}
		}
	}
}

public class Customer {
	//TODO реализуйте методы и поля класса Customer
	private String name, surname, email, phone;
	
	public Customer(String _name, String _surname, String _email, String _phone) {
		this.name = _name;
		this.surname = _surname;
		this.email = _email;
		this.phone = _phone;	
	}
	
	public String getName() {
		return this.name;	
	}

	public String getSurname() {
		return this.surname;	
	}
	
	public String getPhone() {
		return this.phone;	
	}
	
	public String getEmail() {
		return this.email;	
	}

	public String toString() {
		return String.format("%s — %s — %s — %s", this.name, this.surname, this.email, this.phone);
	}
}

public class CustomerStorage {
	//TODO реализуйте методы и поля класса CustomerStorage
	private ArrayList<Customer> customers;
	
	public CustomerStorage() {
		this.customers = new ArrayList<Customer>();
	}

	public void addCustomer (String _nameSurnameEmailPhone) {
		if ((_nameSurnameEmailPhone == null))
			throw new IllegalArgumentException();

		String[] arrOfStr = _nameSurnameEmailPhone.split(" ");

		if ((arrOfStr.length != 4))
			throw new IllegalArgumentException();
		
		if (!(arrOfStr[2].matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
								+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")))
			throw new IllegalArgumentException();
		
		if (!(arrOfStr[3].matches("^(\\+7)([0-9]{10})$")))
			throw new IllegalArgumentException();

		this.customers.add(new Customer(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3]));
	}
	
	public void listCustomers () {
		for (Customer costumer : this.customers) {
			System.out.println(costumer.toString());
		}
	}
	
	public void removeCustomer (String _name, String _surname) {
		if ((_name == null) || (_surname == null))
				throw new IllegalArgumentException();

		Customer toDelete = this.getCustomer(String.format("%s %s", _name, _surname));
		
		if (toDelete != null) {
			this.customers.remove(toDelete);
		}
	}
	
	public Customer getCustomer (String _nameSurname) {
		if ((_nameSurname == null))
				throw new IllegalArgumentException();

		String[] arrOfStr = _nameSurname.split(" ");

		if ((arrOfStr.length != 2))
				throw new IllegalArgumentException();

		for (Customer costumer : this.customers) {
				if ((costumer.getName().equals(arrOfStr[0])) && (costumer.getSurname().equals(arrOfStr[1]))) {
					return costumer;
				}
			}
		return null;
	}

	public int getCount() {
		return this.customers.size();
	}
}