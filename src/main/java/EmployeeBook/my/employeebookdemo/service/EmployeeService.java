package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, double salary, int department);

    Employee removeEmployee(String firstName, String lastName, double salary, int department);

    Employee findEmployee(String firstName, String lastName, double salary, int department);

    Map<Integer, Employee> outputAMap();
}
