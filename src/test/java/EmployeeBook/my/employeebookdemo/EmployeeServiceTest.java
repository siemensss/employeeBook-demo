package EmployeeBook.my.employeebookdemo;

import EmployeeBook.my.employeebookdemo.exceptions.EmployeeAlreadyAddedException;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeNotFoundException;
import EmployeeBook.my.employeebookdemo.exceptions.IncorrectFirstNameException;
import EmployeeBook.my.employeebookdemo.exceptions.IncorrectLastNameException;
import EmployeeBook.my.employeebookdemo.service.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeeServiceTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();


    public static Stream<Arguments> addWithIncorrectFirstNameTestParams() {
        return Stream.of(
                Arguments.of("Ivan1"),
                Arguments.of("Ivan!"),
                Arguments.of("Ivan@")
        );
    }

    public static Stream<Arguments> addWithIncorrectLastNameTestParams() {
        return Stream.of(
                Arguments.of("Ivanov1"),
                Arguments.of("Ivanov!"),
                Arguments.of("Ivanov@")
        );
    }

    @Test
    public void addTest() {
        int beforeCount = employeeService.outputAList().size();
        Employee expected = new Employee("Ivan", "Ivanov", 25000, 1);
        Assertions.assertThat(employeeService.addEmployee("Ivan", "Ivanov", 25000, 1))
                .isEqualTo(expected)
                .isIn(employeeService.outputAList());
        Assertions.assertThat(employeeService.outputAList()).hasSize(beforeCount + 1);
        Assertions.assertThat(employeeService.findEmployee("Ivan", "Ivanov", 25000, 1)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectFirstNameTestParams")
    public void addWithIncorrectFirstNameTest(String incorrectFirstName) {
        Assertions.assertThatExceptionOfType(IncorrectFirstNameException.class)
                .isThrownBy(() -> employeeService.addEmployee(incorrectFirstName, "Ivanov", 25000, 1));
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectLastNameTestParams")
    public void addWithIncorrectLastNameTest(String incorrectLastName) {
        Assertions.assertThatExceptionOfType(IncorrectLastNameException.class)
                .isThrownBy(() -> employeeService.addEmployee("Ivan", incorrectLastName, 25000, 1));
    }

    @Test
    public void addWhenAlreadyAddedTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee("Ilya", "Shubin", 25000, 1));
    }

    @Test
    public void removeTest() {
        int beforeCount = employeeService.outputAList().size();
        Employee expected = new Employee("Ilya", "Shubin", 25000, 1);
        Assertions.assertThat(employeeService.removeEmployee("Ilya", "Shubin", 25000, 1))
                .isEqualTo(expected)
                .isNotIn(employeeService.outputAList());
        Assertions.assertThat(employeeService.outputAList()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Ilya", "Shubin", 25000, 1));
    }

    @Test
    public void removeWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Valentin", "Alexeev", 111111, 3));
    }

    @Test
    public void findTest() {
        int beforeCount = employeeService.outputAList().size();
        Employee expected = new Employee("Ilya", "Shubin", 25000, 1);
        Assertions.assertThat(employeeService.findEmployee("Ilya", "Shubin", 25000, 1))
                .isEqualTo(expected)
                .isIn(employeeService.outputAList());
        Assertions.assertThat(employeeService.outputAList()).hasSize(beforeCount);
    }

    @Test
    public void findWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Valentin", "Alexeev", 111111, 3));
    }

    @Test
    public void outputAList() {
        Assertions.assertThat(employeeService.outputAList())
                .hasSize(7)
                .containsExactlyInAnyOrder(
                        new Employee("Igor", "Filatov", 30000, 1),
                        new Employee("Alex", "Kozlov", 40000, 1),
                        new Employee("Max", "Fomin", 37000, 2),
                        new Employee("Ilya", "Shubin", 25000, 1),
                        new Employee("Valya", "Petrov", 18000, 2),
                        new Employee("Sergey", "Vasyukov", 27000, 1),
                        new Employee("Vlad", "Trushin", 55000, 1)
                );

    }


}
