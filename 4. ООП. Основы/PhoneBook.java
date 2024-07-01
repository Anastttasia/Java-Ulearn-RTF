public class Main{
    public static void main(String[] args) {
        //TODO напишите консольное приложение для взаимодействия с телефонной книгой
    }
}

public class PhoneBook {

    Map<String, Set<String>> phoneBook;
    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }
    public void addContact(String phone, String name) {
        //TODO проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (this.isCorrect(name, phone)) {
            if (!checkContacts(name)) {
                Set<String> phones = new HashSet<String>();
                phones.add(phone);
                phoneBook.put(name, phones);
            }
            else {
                phoneBook.get(name).add(phone);
            }
        }
    }

    public String getNameByPhone(String phone) {
        //TODO формат одного контакта "Имя - Телефон"
		// если контакт не найдены - вернуть пустую строку
		String result = "";

         for (Map.Entry<String, Set<String>> contact : phoneBook.entrySet()) {
            for (String number : phoneBook.get(contact.getKey())) {
                if (number.equals(phone)) {
                    result = contact.getKey();
                    break;
                }
            }
        }
        return result;
    }

    public Set<String> getPhonesByName(String name) {
        //TODO по имени вернуть список номеров
        if (phoneBook.containsKey(name))
        {
            return phoneBook.get(name);
        }
        return null;
    }

    public StringBuilder getNumbersToString(Set<String> numbers) {
		//TODO вернуть номера в формате строки <номер>, <номер>.
		if (numbers == null) {
			return null;	
		}

        StringBuilder resultString = new StringBuilder();
        int counter = 0;
        for (String number : numbers) {
            resultString.append(number);
            if (counter + 1 < numbers.size()) {
                resultString.append(", ");
            }
            counter++;
        }

        return resultString;
    }

    public Set<String> getAllContacts() {
        //TODO вернуть все контакты в формате <имя> — <номер>, <номер> и тд
        Set<String> result = new HashSet<String>();

        for (Map.Entry<String, Set<String>> entry : phoneBook.entrySet()) {
            String numbers = this.getNumbersToString(entry.getValue()).toString();
            String line = String.format("%s - %s", entry.getKey(), numbers);
            result.add(line);
        }
        return result;
    }

    public boolean checkContacts(String name) {
        for (String contact : this.getAllContacts()) {
            if (contact.contains(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrect(String name, String phone){
		//TODO проверьте корректность номера
		if (name == null || phone == null) {
			return false;
		}

        boolean resultNumber = phone.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");

        if (resultNumber) {
            return true;
        }
        else {
            return false;
        }
    }
}