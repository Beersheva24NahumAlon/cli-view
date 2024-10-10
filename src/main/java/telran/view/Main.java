package telran.view;

import java.time.LocalDate;
import java.util.*;

record Employee(long id, String name, String department,
        int salary, LocalDate birthDate) {
}
public class Main {
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = {
            "QA",
            "Audit",
            "Development",
            "Managment"
    };
    final static long MIN_ID = 100000;
    final static long MAX_ID = 999999;
    final static int MIN_AGE = 18;
    final static int MAX_AGE = 70;

    static InputOutput io = new StandardInputOutput();

    public static void main(String[] args) {
        //readEmployeeAsObject();
        readEmployeeBySeparateFields();
    }

    static void readEmployeeAsObject() {
        Employee empl = io.readObject(
                "Enter employee data in the format:" +
                        "<id>#<name>#<departnemt>#<salary>#<yyyy-MM-DD>",
                "Wrong format for employee data",
                str -> {
                    String[] tokens = str.split("#");
                    return new Employee(
                            Long.parseLong(tokens[0]),
                            tokens[1],
                            tokens[2],
                            Integer.parseInt(tokens[3]),
                            LocalDate.parse(tokens[4]));
                });
        io.writeLine(empl);
    }

    static void readEmployeeBySeparateFields() {
        Employee empl = new Employee(
            io.readNumberRange(String.format("Enter id in range (%d..%d)", MIN_ID, MAX_ID), 
                    "Id is wrong", MIN_ID, MAX_ID).longValue(),
            io.readStringPredicate("Enter name (starts in capital letter, minimum 3 characters)", 
                    "Name is wrong", s -> s.matches("[A-Z][a-z]{2,}")),
            io.readStringOptions(String.format("Enter department %s", Arrays.toString(DEPARTMENTS)), 
                    "Department is not in list", new HashSet<>(Arrays.asList(DEPARTMENTS))),
            io.readNumberRange(String.format("Enter salary in range (%d..%d)", MIN_SALARY, MAX_SALARY), 
                    "Salary is wrong", MIN_SALARY, MAX_SALARY).intValue(),
            io.readIsoDateRange(String.format("Enter birthday in format yyyy-MM-DD (age must in range %d..%d)", MIN_AGE, MAX_AGE), 
                    "Birthday is wrong", LocalDate.now().minusYears(MAX_AGE), LocalDate.now().minusYears(MIN_AGE))
        );
        io.writeLine(empl);
    }

}
