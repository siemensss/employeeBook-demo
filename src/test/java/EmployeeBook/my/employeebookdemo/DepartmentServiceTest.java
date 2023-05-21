package EmployeeBook.my.employeebookdemo;

import EmployeeBook.my.employeebookdemo.exceptions.DepartmentNotFoundException;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeNotFoundException;
import EmployeeBook.my.employeebookdemo.service.DepartmentServiceImpl;
import EmployeeBook.my.employeebookdemo.service.EmployeeService;
import EmployeeBook.my.employeebookdemo.service.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    public static Stream<Arguments>minSalaryInSpecificDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 30000),
                Arguments.of(2, 25000),
                Arguments.of(3,18000)
        );
    }

    public static Stream<Arguments> maxSalaryInSpecificDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 40000),
                Arguments.of(2, 37000),
                Arguments.of(3, 18000)
        );
    }

    public static Stream<Arguments> outputAllEmployeesInSpecificDepartmentParams() {
        return Stream.of(
                Arguments.of(1,
                        List.of(
                                new Employee("Alex", "Kozlov", 40000, 1),
                                new Employee("Igor", "Filatov", 30000, 1)
                        )
                ),
                Arguments.of(2,
                        List.of(
                                new Employee("Max", "Fomin", 37000, 2),
                                new Employee("Ilya", "Shubin", 25000, 2)
                        )
                ),
                Arguments.of(3,
                        Collections.singletonList(new Employee("Valya", "Petrov", 18000, 3))

                ),
                Arguments.of(4,
                        Collections.emptyList()
                )
        );
    }

    public static Stream<Arguments> sumSalaryInSpecificDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 70000),
                Arguments.of(2, 62000),
                Arguments.of(3, 33000),
                Arguments.of(4, 0)
        );
    }


    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.outputAList()).thenReturn(
                List.of(
                new Employee("Igor", "Filatov", 30000, 1),
                new Employee("Alex", "Kozlov", 40000, 1),
                new Employee("Max", "Fomin", 37000, 2),
                new Employee("Ilya", "Shubin", 25000, 2),
                new Employee("Valya", "Petrov", 18000, 3)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("minSalaryInSpecificDepartmentTestParams")
    public void minSalaryInSpecificDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentService.minSalaryInSpecificDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void minSalaryInSpecificDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.minSalaryInSpecificDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("maxSalaryInSpecificDepartmentTestParams")
    public void maxSalaryInSpecificDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentService.maxSalaryInSpecificDepartment(department))
                .isEqualTo(expected);
    }

    @Test
    public void maxSalaryInSpecificDepartmentWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(() -> departmentService.maxSalaryInSpecificDepartment(4));
    }
    @ParameterizedTest
    @MethodSource("sumSalaryInSpecificDepartmentTestParams")
    public void sumSalaryInSpecificDepartmentTest(int department, double expected) {
        Assertions.assertThat(departmentService.sumSalaryInSpecificDepartment(department))
                .isEqualTo(expected);
    }


    @ParameterizedTest
    @MethodSource("outputAllEmployeesInSpecificDepartmentParams")
    public void outputAllEmployeesInSpecificDepartmentTest(int department, List<Employee> expected) {
        Assertions.assertThat(departmentService.outputAllEmployeesInSpecificDepartment(department))
                .containsExactlyInAnyOrderElementsOf(expected);
    }
    @Test
    public void outputAllEmployeesByDepartmentTest() {
        Map <Integer, List<Employee>> expected = Map.of(
                1, List.of(
                        new Employee("Alex", "Kozlov", 40000, 1),
                        new Employee("Igor", "Filatov", 30000, 1)
                ),
                2, List.of(
                        new Employee("Max", "Fomin", 37000, 2),
                        new Employee("Ilya", "Shubin", 25000, 2)
                ),
                3, Collections.singletonList(new Employee("Valya", "Petrov", 18000, 3))
        );
        Assertions.assertThat(departmentService.outputAllEmployeesByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }


}
