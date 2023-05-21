package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    double minSalaryInSpecificDepartment(int department);

    double maxSalaryInSpecificDepartment(int department);

    double sumSalaryInSpecificDepartment(int department);

    List<Employee> outputAllEmployeesInSpecificDepartment(int department);

    Map<Integer, List<Employee>> outputAllEmployeesByDepartment();
}
