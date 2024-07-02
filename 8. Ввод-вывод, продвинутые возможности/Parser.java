public class Parser {
    public static void main(String[] args) {
        //TODO напишите консольное приложение
    }
}

public class Operation {
    public String operationDescription;
    public Double income;
    public Double expense;

    public Operation(String operationDescription, Double income, Double expense) {
        this.operationDescription = operationDescription;
        this.income = income;
        this.expense = expense;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public Double getIncome() {
        return income;
    }

    public Double getExpense() {
        return expense;
    }
}


public class Movements{
	
	private ArrayList<Operation> operations;
	public Movements(String path){
		operations = new ArrayList<Operation>();
		
		try {
			File dataFile = new File(path);
            FileReader fr = new FileReader(dataFile);
            BufferedReader br = new BufferedReader(fr);
			String line;
			if (br.readLine() == null) {		
				br.close();
				fr.close();
				return;
			}
		
			while((line = br.readLine()) != null){
				String[] dataLine = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", 8);
				
				if (dataLine.length != 8) 
					continue;

	        	String information = dataLine[5];
				String income = dataLine[6];
				String expense = dataLine[7];
				income = income.replaceAll("\"", "").replaceAll(",", ".");
				expense = expense.replaceAll("\"", "").replaceAll(",", ".");				


				operations.add(new Operation(information,
                Double.parseDouble(income),
                Double.parseDouble(expense)));
			}
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public double getExpenseSum() {
		//TODO метод получения расходов
		double result = 0.0;
		for (Operation oper : operations)
		{
			result += oper.getExpense();
		}
		return result;
    }

    public double getIncomeSum() {
		//TODO метод получения доходов
		double result = 0.0;
		for (Operation oper : operations)
		{
			result += oper.getIncome();
		}
		return result;
    }

    public ArrayList<String> getListOfExpenses() {
        //TODO метод получения расходов по организациям в виде строк
        //Пример строк:
        //      /RU/CARD2CARD ALFA_MOBILE>MOSCOW —> 51000,00 руб.
        //      \643\MOSKVA\Alfa Iss —> 353000,00 руб.
		//      \643\MOSKVA\YANDEX TAXI —> 484,00 руб.
		ArrayList<String> result = new ArrayList<>();
        TreeMap<String, Double> expenses = new TreeMap<>();
        operations.forEach(operation -> {
            Double expense = operation.getExpense();
            if (expense != 0) {
                String organization = operation.getOperationDescription()
                        .split(" +", 2)[1]
					.split(" {3,}")[0];

                if (organization.contains("\\")) organization = organization.substring(organization.indexOf("\\"));
				if (organization.contains("/")) organization = organization.substring(organization.indexOf("/"));

                if (!expenses.containsKey(organization)) {
                    expenses.put(organization, expense);
                } else {
                    expenses.replace(organization, expenses.get(organization) + expense);
				}

            }
        });
        expenses.forEach((organization, amount) -> result.add(String.format("%s —> %.2f руб.", organization, amount)));
        return result;
    }
}