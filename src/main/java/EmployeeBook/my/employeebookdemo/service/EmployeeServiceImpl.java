package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeAlreadyAddedException;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<Integer, Employee> employees = new HashMap<>(Map.of(
            0, new Employee("Igor", "Filatov"),
            1, new Employee("Alex", "Kozlov"),
            2, new Employee("Max", "Fomin"),
            3, new Employee("Ilya", "Shubin"),
            4, new Employee("Valya", "Petrov"),
            5, new Employee("Sergey", "Vasyukov"),
            6, new Employee("Vlad", "Trushin")
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        int id = employees.size();
        if (employees.containsValue(e)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в массиве");
        } else {
            employees.put(id, e);
        }
        return e;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (!employees.containsValue(e)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.values().remove(e);
        return e;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
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
