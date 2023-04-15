package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeAlreadyAddedException;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

     Map<Integer, Employee> employees = new HashMap<>(Map.of(
            0, new Employee("Igor", "Filatov", 30000, 1),
            1, new Employee("Alex", "Kozlov", 40000, 1),
            2, new Employee("Max", "Fomin", 37000, 2),
            3, new Employee("Ilya", "Shubin", 42000, 3),
            4, new Employee("Valya", "Petrov", 18000, 2),
            5, new Employee("Sergey", "Vasyukov", 27000, 1),
            6, new Employee("Vlad", "Trushin", 55000, 1)
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName, double salary, int department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        int id = employees.size();
        if (employees.containsValue(e)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в массиве");
        } else {
            employees.put(id, e);
        }
        return e;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, double salary, int department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        if (!employees.containsValue(e)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.values().remove(e);
        return e;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, double salary, int department) {
        Employee e = new Employee(firstName, lastName, salary, department);
        if (!employees.containsValue(e)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return e;
    }

    @Override
    public Map<Integer, Employee> outputAMap() {
        return new HashMap<>(employees);
    }

}
