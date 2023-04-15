package EmployeeBook.my.employeebookdemo.service;

import EmployeeBook.my.employeebookdemo.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeServiceImpl;


    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @Override
    public Employee getEmployeeWithMinSalaryInSpecificDepartment(int department) {
        return employeeServiceImpl.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException("Не найдено сотрудников в отделе"));
    }

    @Override
    public Employee getEmployeeWithMaxSalaryInSpecificDepartment(int department) {
        return employeeServiceImpl.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException("Не найдено сотрудников в отделе"));
    }

    @Override
    public List<Employee> outputAllEmployeesInSpecificDepartment(int department) {
        return employeeServiceImpl.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer, List<Employee>> outputAllEmployeesByDepartment() {
        return employeeServiceImpl.employees.values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }
}

