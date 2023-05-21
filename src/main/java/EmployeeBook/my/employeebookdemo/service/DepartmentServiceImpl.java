package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;
import EmployeeBook.my.employeebookdemo.exceptions.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;
    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public double minSalaryInSpecificDepartment(int department) {
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(DepartmentNotFoundException::new);
    }
    @Override
    public double maxSalaryInSpecificDepartment(int department) {
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(DepartmentNotFoundException::new);
    }
    @Override
    public double sumSalaryInSpecificDepartment(int department) {
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }


    @Override
    public List<Employee> outputAllEmployeesInSpecificDepartment(int department) {
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer, List<Employee>> outputAllEmployeesByDepartment() {
        return employeeService.employees.values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }


}

