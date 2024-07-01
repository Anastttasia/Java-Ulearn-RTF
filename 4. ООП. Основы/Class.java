public class School {
    //напишите класс School
    ArrayList<Human> peoples;

    public School() {
        this.peoples = new ArrayList<Human>();
    }

    public String getPeoplesInSchool() {
        /* реализуйте метод получения учителей и учеников в школе
           в формате:

           В школе:
           Игорь Николаев Математика 20
           Иван Иванов 2010
         */
        String result = "В школе:\n";
        for (Human human : peoples) {
            if (human.isInSchool()) {
                result = result + human.toString() + '\n';
            }
        }
        return result;
    }

    public void add(Human human){
        this.peoples.add(human);
    }
}

public abstract class Human {
    //напишите класс Human
    //goInSchool() — пришел в школу, outFromSchool() — вышел из школы
    // isInSchool() — статус в школе/не в школе типа boolean
    String name;
    String lastname;
    boolean status;
    public Human(String name, String lastname, boolean status) {
        this.name = name;
        this.lastname = lastname;
        this.status = status;
    }
    public void goInSchool() {
        this.status = true;
        System.out.println("Пришел в школу!");
    }
    public void outFromSchool() {
        this.status = false;
        System.out.println("Ушел из школы");
    }
    public boolean isInSchool() {
        return this.status;
    }
}

public class Pupil extends Human {
    /*напишите класс Pupil
      формат вывода toString(): имя фамилия год поступления
      */
    int yearStartStudy;
    public Pupil(String name, String lastname, int yearStartStudy) {
        super(name, lastname, false);
        this.yearStartStudy = yearStartStudy;
    }
	
	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.lastname;
	}
	
	public int getYear() {
		return this.yearStartStudy;
	}


    public String toString() {
        return String.format("%s %s %d", this.name, this.lastname, this.yearStartStudy);
    }
}

public class Teacher extends Human {
        /*напишите класс Teacher
          формат вывода toString(): имя фамилия предмет стаж работы
          */

    int experience;
    String subject;
    public Teacher(String  name, String lastname, String subject, int experience) {
        super(name, lastname, false);
        this.subject = subject;
        this.experience = experience;
    }

    public String toString() {
        return String.format("%s %s %s %d", this.name, this.lastname, this.subject, this.experience);
    }
}