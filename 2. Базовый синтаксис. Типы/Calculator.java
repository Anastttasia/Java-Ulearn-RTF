public class Calculator {
    public static String calculate(String input) {
        //TODO напишите метод Calculate, который будет получать строку в формате <первый аргумент> <операция> <второй аргумент>
		//разделенными пробелом и отправлять в нужные методы
		
		String[] arg = input.split(" ");
		if (arg.length != 3){
			return "";	
		}

		String a = arg[0];
		String operation = arg[1];
		String b = arg[2];
		//calculate(a,b,operation);

		try {
			double ad = Double.parseDouble(a);
			double bd = Double.parseDouble(b);
			return new Double(calculate(ad, bd, operation)).toString();
		}
		catch (NumberFormatException e) {
			return calculate(a, b, operation);
		}

    }
    
    private static String calculate(String a, String b, String operation) {
        //TODO напишите метод для складывания, либо вычитания строк
		//Важно: для вычитания строки, они должны совпадать по регистру
		switch(operation){
			case("+"):
				return a + b;
			case("-"):
				char[] bukvs = b.toCharArray();
				for (char ch: bukvs) {
					if (a.indexOf(ch) != -1) {
						a = a.replace(String.valueOf(ch), "");
					}
					else {
						throw new IllegalArgumentException();
					}
				}
				return a;
			default:
				throw new IllegalArgumentException();
		}
    }

    private static double calculate(double a, double b, String operation) {
		//TODO напишите метод для работы с числами со следующими операциями: +, -, /, *, %
		double result = 0;
		switch(operation){
			case("+"):
				return a + b;
			case("-"):
				return a - b;
			case("/"):
				return a / b;
			case("*"):
				return a * b;
			case("%"):
				return a % b;
			default:
				throw new IllegalArgumentException();
		}
    }

    public static int getNumbers(int a, int b) {
		//TODO напишите метод принимает два аргумента, складывает их и возращает кол-во четных цифр в сумме
		int sum = a + b;
		int count = 0;
		String sumStr = Integer.toString(sum);
		char[] sumArr = sumStr.toCharArray();
		for (char s: sumArr){
			if (s == '-') {
				continue;
			}
		    int num = Character.getNumericValue(s);
		    if(num % 2 == 0){
		        count +=1;
		    }
		}
		return count;
    }

    public static String getMinimalType(String input) {
		//TODO напишите метод получается число в формате строки и возращает минимальный целочисленный тип, к которому его можно привести, Long, Int, Short или Byte
		try {
			Byte.parseByte(input);
			return "Byte";
		}
		catch (NumberFormatException e) {};
		try {
			Short.parseShort(input);
			return "Short";
		}
		catch (NumberFormatException e) {};
		try {
			Integer.parseInt(input);
			return "Int";
		}
		catch (NumberFormatException e) {};
		try {
			Long.parseLong(input);
			return "Long";
		}
		catch (NumberFormatException e) {};
		throw new IllegalArgumentException();
	}
}