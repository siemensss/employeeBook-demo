package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMinSalaryInSpecificDepartment(int department);


    Employee getEmployeeWithMaxSalaryInSpecificDepartment(int department);

    List<Employee> outputAllEmployeesInSpecificDepartment(int department);

    Map<Integer, List<Employee>> outputAllEmployeesByDepartment();
}
