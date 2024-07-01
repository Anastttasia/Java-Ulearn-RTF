public class Main {
    public static ArrayList<Employee> staff;

    public static void main(String[] args, String path) {
		staff = Employee.loadStaffFromFile(path);
    }

    public static Employee findEmployeeWithHighestSalary(int year) {
		if (year <= 2000) 
			throw new IllegalArgumentException();
        return staff.stream()
                .filter(e -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(e.getWorkStart());
                    return calendar.get(Calendar.YEAR) == year;
                })
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }

    public static ArrayList<Employee> sortEmployee(String column) {
        if (column == null) throw new IllegalArgumentException();
        switch (column) {
            case "name": {
                return staff.stream().sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toCollection(ArrayList::new));
            }

            case "salary": {
                return staff.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toCollection(ArrayList::new));
            }

            case "date": {
                return staff.stream().sorted(Comparator.comparing(Employee::getWorkStart)).collect(Collectors.toCollection(ArrayList::new));
            }
            default:
                throw new IllegalArgumentException();
        }
    }
}

public class Employee {

    private String name;
    private Integer salary;
    private Date workStart;

    public Employee(String name, Integer salary, Date workStart) {
        this.name = name;
        this.salary = salary;
        this.workStart = workStart;
    }

    public static ArrayList<Employee> loadStaffFromFile(String path) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                String dateFormat = "dd.MM.yyyy";
                DateFormat format = new SimpleDateFormat(dateFormat);
                format.setTimeZone(TimeZone.getTimeZone("YEKT"));
                employees.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (format.parse(fragments[2]))
));
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
        return employees;
    }

	public String getName()
	{
		return this.name;	
	}

	public Integer getSalary()
	{
		return this.salary;	
	}
	
	public Date getWorkStart()
	{
		return this.workStart;	
	}

    public String toString() {
		SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
		return String.format("%s — %s — %s", name, salary, formater.format(workStart.toString()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(workStart, employee.workStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, workStart);
    }
}