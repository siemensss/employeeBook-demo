package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeAlreadyAddedException;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeNotFoundException;
import EmployeeBook.my.employeebookdemo.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public Employee[] employees = {
            new Employee("Igor", "Filatov"),
            new Employee("Alex", "Kozlov"),
            new Employee("Max", "Fomin"),
            new Employee("Ilya", "Shubin"),
            new Employee("Valya", "Petrov"),
            new Employee("Sergey", "Vasyukov"),
            new Employee("Vlad", "Trushin")

    };

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == e) {
                throw new EmployeeAlreadyAddedException("Сотрудник уже имеется в массиве");
            }
            if (employees[i] == null) {
                employees[i] = e;
                return employees[i];
            } else if (i == employees.length - 1) {
                throw new EmployeeStorageIsFullException("Массив переполнен");
            }
        }
        return null;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee e;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (firstName.equals(employees[i].getFirstName()) && lastName.equals(employees[i].getLastName())) {
                    e = employees[i];
                    employees[i] = null;
                    return e;
                } else if (i == employees.length - 1) {
                    throw new EmployeeNotFoundException("Сотрудник не найден");
                }
            }
        }
        return null;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (firstName.equals(employees[i].getFirstName()) && lastName.equals(employees[i].getLastName())) {
                    return employees[i];
                } else if (i == employees.length - 1) {
                    throw new EmployeeNotFoundException("Сотрудник не найден");
                }
            }
        }
        return null;
    }


}
