package telran.view;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

record Employee(long id, String name, String department,
        int salary, LocalDate birthDate) {
}

public class InputOutputTest {
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

    InputOutput io = new StandardInputOutput();

    @Test
    @Disabled
    void readEmployeeAsObject() {
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

    @Test
    @Disabled
    void readEmployeeBySeparateFields() {
        
    }
}
