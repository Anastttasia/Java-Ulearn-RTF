public abstract class AbstractLogger
{
	//TODO реализуйте абстрактный класс AbstractLogger с абстрактными методами info, debug, error, warning, toString и необходимыми полями
	public String name;
	public Calendar calendar;

	public abstract String debug(String message);
	public abstract String info(String message);
	public abstract String warning(String message);
	public abstract String error(String message);

	public void setCalendar(TimeZone timeZone, int year, int month, int day, int hours, int minutes, int seconds)
	{
		//TODO метод для установки даты для календаря
		TimeZone.setDefault(timeZone);
		this.calendar = new GregorianCalendar(year, month - 1, day, hours, minutes, seconds);
		//this.calendar.setTimeZone(timeZone);
		//this.calendar = Calendar.getInstance(timeZone);
		//this.calendar.setTime(new Date(year, month, day, hours, minutes, seconds));
	}

}

public class DefaultLogger extends AbstractLogger
{
    //TODO реализуйте класс-наследник DefaultLogger, реализуйте все методы
    //Вызов логгирования должен выводить сообщение в таком формате: [LEVEL] <CALENDAR.DATE> NAME - MESSAGE
	//ToString() в формате: NAME — DefaultLogger
	public String debug(String message)
	{
		
		return String.format("[DEBUG] <%s> %s - %s", this.calendar.getTime().toString(), this.name, message);
	}

	public String info(String message)
	{
		return String.format("[INFO] <%s> %s - %s", this.calendar.getTime().toString(), this.name, message);
	}
	
	public String warning(String message)
	{
		return String.format("[WARNING] <%s> %s - %s", this.calendar.getTime().toString(), this.name, message);
	}
	
	public String error(String message)
	{
		return String.format("[ERROR] <%s> %s - %s", this.calendar.getTime().toString(), this.name, message);
	}

	public DefaultLogger(String name)
	{
		this.name = name;
		this.calendar = new GregorianCalendar();
	}
	
	public String toString()
	{
		return String.format("%s — %s", this.name, "DefaultLogger");
	}
}

public class InputControlLogger extends AbstractLogger
{
    //TODO реализуйте класс-наследник InputControlLogger, реализуйте все методы
    //Примеры вывода различных методов класса:
    //[INFO] <Sat Feb 01 12:15:00 MSK 2020> Вход пользователя: message
    //[DEBUG] <Sat Feb 01 12:15:00 MSK 2020> Обнаружен пользователь: message
    //[ERROR] <Sat Feb 01 12:15:00 MSK 2020> Не удалось найти данные пользователя: message
    //[WARNING] <Sat Feb 01 12:15:00 MSK 2020> Обнаружен неавторизованный доступ в систему: message
	//ToString() в формате: NAME — InputControlLogger
	public InputControlLogger(String name)
	{
		this.name = name;
		this.calendar = new GregorianCalendar();
	}
	
	public String debug(String message)
	{
		
		return String.format("[DEBUG] <%s> Обнаружен пользователь: %s", this.calendar.getTime().toString(), message);
	}
	public String info(String message)
	{
		return String.format("[INFO] <%s> Вход пользователя: %s", this.calendar.getTime().toString(), message);
	}
	
	public String warning(String message)
	{
		return String.format("[WARNING] <%s> Обнаружен неавторизованный доступ в систему: %s", this.calendar.getTime().toString(), message);
	}
	
	public String error(String message)
	{
		return String.format("[ERROR] <%s> Не удалось найти данные пользователя: %s", this.calendar.getTime().toString(), message);
	}
	public String toString()
	{
		return String.format("%s — %s", this.name, "InputControlLogger");
	}
}

public class LogManager
{
    //TODO реализуйте класс LogManager с методами:
    //1) addLogger — добавить логгер в хранилище менеджера
    //2) getLogger — получить логгер из хранилища менеджера по имени, если Логгер не найден, создать новый DefaultLogger
	//3) printLoggers —  распечатать все логгеры путем вызова их метода toString() и разделяя их переносом строки /n
	private ArrayList<AbstractLogger> loggers;
	
	public LogManager()
	{
		this.loggers = new ArrayList<AbstractLogger>();
	}

	
	public void addLogger(AbstractLogger logger)
	{
		loggers.add(logger);
	}

	public AbstractLogger getLogger(String name)
	{
		for (AbstractLogger logger : this.loggers)
		{
			if (logger.name == name)
			{
				return 	logger;
			}
		}
		AbstractLogger logger = new DefaultLogger(name);
		this.addLogger(logger);
		return logger;
	}
	
	public String printLoggers()
	{
		String result = "";

		for (int i = 0; i < loggers.size(); i++) {
			result = result + loggers.get(i).toString();
			result = result + "\n";
		}
		return result;
	}
}