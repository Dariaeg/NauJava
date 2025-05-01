import java.util.List;
import java.util.OptionalDouble;

class Employee {
    private final String name;
    private final Integer age;
    private final String department;
    private final double salary;

    public Employee(String name, Integer age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age='" + age + "', department='" + department + "', salary=" + salary + "}";
    }

    public static void main(String[] args) {
        //список сотрудников
        List<Employee> employees = List.of(
                new Employee("Иван Иванов", 34,"IT", 99000),
                new Employee("Мария Петрова", 23,"HR", 66000),
                new Employee("Алексей Сушкин", 36,"IT", 110000),
                new Employee("Елена Абрасимова", 26,"HR", 135000),
                new Employee("Дмитрий Кузнецов", 33,"IT", 100000)
        );

        //департамент, по которому ищем среднюю зарплату
        String targetDepartment = "IT";

        //Stream API для вычисления средней зарплаты
        OptionalDouble averageSalary = employees.stream()
                .filter(emp -> emp.getDepartment().equals(targetDepartment))
                .mapToDouble(Employee::getSalary)
                .average();

        //выводим результат
        if (averageSalary.isPresent()) {
            System.out.printf(
                    "Средняя зарплата в департаменте '%s': %.2f руб.\n",
                    targetDepartment,
                    averageSalary.getAsDouble()
            );
        } else {
            System.out.printf("В департаменте '%s' нет сотрудников.\n", targetDepartment);
        }

        //выводим всех сотрудников
        System.out.println("\nСписок всех сотрудников:");
        employees.forEach(System.out::println);
    }
}

