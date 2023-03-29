package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeAlreadyAddedException;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Igor", "Filatov"),
            new Employee("Alex", "Kozlov"),
            new Employee("Max", "Fomin"),
            new Employee("Ilya", "Shubin"),
            new Employee("Valya", "Petrov"),
            new Employee("Sergey", "Vasyukov"),
            new Employee("Vlad", "Trushin")
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (employees.contains(e)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в массиве");
        } else {
            employees.add(e);
        }
        return e;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        boolean flag = employees.remove(e);
        if (!flag) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return e;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        int index = employees.indexOf(e);
        if (index == -1) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(index);
    }

    @Override
    public List<Employee> outputAList() {
        return new ArrayList<>(employees);
    }

}
